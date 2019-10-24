package com.shuffle.fulltexo.importer.adapters;

public class BooleanValue implements CustomValueAdapter<Boolean> {

	@Override
	public Boolean getValue(Object value) {
		return value instanceof String && value.toString().contains("y");
	}

}
