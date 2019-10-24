package com.shuffle.fulltexo.importer;

public class FieldValue {

	private Field field;

	private Object value;

	public FieldValue(Field field, Object value) {
		super();
		this.field = field;
		this.value = value;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "FieldValue [field=" + field + ", value=" + value + "]";
	}
}
