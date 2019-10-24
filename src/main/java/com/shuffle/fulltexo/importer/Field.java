package com.shuffle.fulltexo.importer;

public class Field {

	private int position;

	private String name;

	private java.lang.reflect.Field field;

	public Field(int position, java.lang.reflect.Field field) {
		super();
		this.position = position;
		this.field = field;
	}

	public Field(String name, java.lang.reflect.Field field) {
		super();
		this.position = -1;
		this.name = name;
		this.field = field;
	}

	public Field(int position, String name, java.lang.reflect.Field field) {
		super();
		this.position = position;
		this.name = name;
		this.field = field;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public java.lang.reflect.Field getField() {
		return field;
	}

	public void setField(java.lang.reflect.Field field) {
		this.field = field;
	}

	@Override
	public String toString() {
		return "Field [position=" + position + ", name=" + name + ", field=" + field + "]";
	}
}
