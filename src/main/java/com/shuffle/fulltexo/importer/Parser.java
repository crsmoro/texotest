package com.shuffle.fulltexo.importer;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser<E> {

	private Class<E> clazz;

	private List<Field> fields = new ArrayList<>();

	private InputStream dataInput;

	private String separator;

	public Parser(Class<E> clazz) {
		this(clazz, null);
	}

	public Parser(Class<E> clazz, InputStream dataInput) {
		this.clazz = clazz;
		this.separator = ";";
		this.dataInput = dataInput;
	}

	public Class<E> getClazz() {
		return clazz;
	}

	public void setClazz(Class<E> clazz) {
		this.clazz = clazz;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public InputStream getDataInput() {
		return dataInput;
	}

	public void setDataInput(InputStream dataInput) {
		this.dataInput = dataInput;
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	private void loadAllFieldsFromClass() {
		for (java.lang.reflect.Field field : this.clazz.getDeclaredFields()) {
			this.fields.add(new Field(field.getName(), field));
		}
	}

	private void findPositionColumns(String header) {
		String[] headerColumns = header.split(this.separator, -1);
		for (Field field : this.fields) {
			if (field.getPosition() == -1) {
				for (int i = 0; i < headerColumns.length; i++) {
					String headerColumn = headerColumns[i];
					if (field.getName().contentEquals(headerColumn.trim())) {
						field.setPosition(i);
					}
				}
			}
		}
	}
	
	public List<RowValue> doParse() {
		if (this.fields.isEmpty()) {
			this.loadAllFieldsFromClass();
		}

		List<RowValue> rowValues = new ArrayList<RowValue>();
		Scanner scanner = new Scanner(this.dataInput);
		String header = scanner.nextLine();
		this.findPositionColumns(header);

		while (scanner.hasNextLine()) {
			List<FieldValue> fieldValues = new ArrayList<FieldValue>();
			String[] columnsValues = scanner.nextLine().split(this.separator, -1);
			for (Field field : this.fields) {
				field.getField().setAccessible(true);
				Object value = columnsValues[field.getPosition()];
				fieldValues.add(new FieldValue(field, value));
			}
			rowValues.add(new RowValue(fieldValues));
		}

		scanner.close();

		return rowValues;
	}
}
