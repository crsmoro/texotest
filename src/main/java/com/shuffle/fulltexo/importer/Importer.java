package com.shuffle.fulltexo.importer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shuffle.fulltexo.Application;
import com.shuffle.fulltexo.entity.GenericEntity;
import com.shuffle.fulltexo.importer.annotations.ImportField;
import com.shuffle.fulltexo.importer.annotations.ImportFieldCustomValue;
import com.shuffle.fulltexo.importer.annotations.ImportFieldKey;
import com.shuffle.fulltexo.repository.GenericRepository;

public class Importer<E extends GenericEntity> {

	private static final transient Log log = LogFactory.getLog(Importer.class);

	private Class<E> clazz;

	private List<RowValue> rowValues = new ArrayList<RowValue>();

	public Importer(Class<E> clazz) {
		this(clazz, null);
	}

	public Importer(Class<E> clazz, List<RowValue> rowValues) {
		super();
		this.clazz = clazz;
		this.rowValues = rowValues;
	}

	public Class<E> getClazz() {
		return clazz;
	}

	public void setClazz(Class<E> clazz) {
		this.clazz = clazz;
	}

	public List<RowValue> getRowValues() {
		return rowValues;
	}

	public void setRowValues(List<RowValue> rowValues) {
		this.rowValues = rowValues;
	}

	private Object getCustomValueFromField(ImportFieldCustomValue importField, Object value) {
		if (importField != null && importField.customValue() != null) {
			try {
				value = importField.customValue().newInstance().getValue(value);
			} catch (InstantiationException | IllegalAccessException e) {
				log.warn("Error getting value from customValue of value [" + value + "]", e);
			}
		}
		return value;
	}

	private void setValue(FieldValue fieldValue, E instance) {
		setValue(fieldValue.getField().getField(), fieldValue.getValue(), instance);
	}

	private void setValueCustom(ImportFieldCustomValue importFieldCustomValue, java.lang.reflect.Field field, Object value, E instance) {
		try {
			field.set(instance, getCustomValueFromField(importFieldCustomValue, value));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			log.error("Error setting value with Custom of field [" + field.getName() + "]", e);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setValueCollection(ImportField importField, java.lang.reflect.Field field, Object value, E instance) {
		if (importField != null && importField.list()) {
			try {
				Class<? extends GenericEntity> collectionType = (Class<? extends GenericEntity>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
				java.lang.reflect.Field fieldKeyToSearch = Stream.of(collectionType.getDeclaredFields()).filter(f -> f.isAnnotationPresent(ImportFieldKey.class)).findFirst().orElse(null);
				fieldKeyToSearch.setAccessible(true);

				Collection<Object> valuesList = new HashSet<Object>();

				String[] values = value.toString().split(importField.separatorList(), -1);
				for (String valueItem : values) {

					//FIXME shitty generic - no time, bro
					GenericRepository genericRepository = (GenericRepository) Application.applicationContext.getBean(collectionType.getSimpleName().toLowerCase() + "Repository");
					GenericEntity child = (GenericEntity) genericRepository.findOne((entity, cq, cb) -> cb.equal(entity.get(fieldKeyToSearch.getName()), valueItem.trim())).orElse(null);
					try {
						if (child == null) {
							child = collectionType.newInstance();
						}
						fieldKeyToSearch.set(child, valueItem.trim());
						genericRepository.save(child);
					} catch (InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException e) {
						throw new IllegalArgumentException(e);
					}
					valuesList.add(child);
				}
				field.set(instance, valuesList);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				log.error("Error setting value with valueCollection of field [" + field.getName() + "]", e);
			}
		}
	}

	private void setValueValueOf(java.lang.reflect.Field field, Object value, E instance) {
		try {
			if (field.getType().isAssignableFrom(String.class)) {
				field.set(instance, value);
			} else {
				Method valueOfMethod = field.getType().getDeclaredMethod("valueOf", String.class);
				field.set(instance, valueOfMethod.invoke(field, value));

			}
		} catch (NoSuchMethodException | SecurityException | IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			log.error("Error setting value with valueOf of field [" + field.getName() + "]", e);
		}
	}

	private void setValue(java.lang.reflect.Field field, Object value, E instance) {
		ImportFieldCustomValue importFieldCustomValue = field.getAnnotation(ImportFieldCustomValue.class);
		ImportField importField = field.getAnnotation(ImportField.class);
		if (importFieldCustomValue != null && importFieldCustomValue.customValue() != null) {
			setValueCustom(importFieldCustomValue, field, value, instance);
		} else if (Collection.class.isAssignableFrom(field.getType())) {
			setValueCollection(importField, field, value, instance);
		} else if (!field.getType().isAssignableFrom(GenericEntity.class) && !field.getType().isAssignableFrom(Object.class)) {
			setValueValueOf(field, value, instance);
		} else {
			log.warn("Unhandled type [" + value + "]");
		}
	}

	private E getNewInstance() {
		try {
			return this.clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			log.error("Error during new instance", e);
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<E> doImport() {
		List<E> results = new ArrayList<>();
		for (RowValue rowValue : rowValues) {
			E instance = getNewInstance();
			for (FieldValue fieldValue : rowValue.getFieldValues()) {
				setValue(fieldValue, instance);
			}
			// FIXME shitty generic strikes again =D
			GenericRepository genericRepository = (GenericRepository) Application.applicationContext.getBean(this.clazz.getSimpleName().toLowerCase() + "Repository");
			genericRepository.save(instance);
			results.add(instance);
		}
		return results;
	}
}
