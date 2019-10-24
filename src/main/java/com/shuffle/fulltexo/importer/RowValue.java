package com.shuffle.fulltexo.importer;

import java.util.ArrayList;
import java.util.List;

public class RowValue {

	private List<FieldValue> fieldValues = new ArrayList<FieldValue>();

	public RowValue() {
		super();
	}

	public RowValue(List<FieldValue> fieldValues) {
		super();
		this.fieldValues = fieldValues;
	}

	public List<FieldValue> getFieldValues() {
		return fieldValues;
	}

	public void setFieldValues(List<FieldValue> fieldValues) {
		this.fieldValues = fieldValues;
	}

	@Override
	public String toString() {
		return "RowValue [fieldValues=" + fieldValues + "]";
	}	

}
