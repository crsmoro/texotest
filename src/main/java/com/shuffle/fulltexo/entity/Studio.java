package com.shuffle.fulltexo.entity;

import javax.persistence.Entity;

import com.shuffle.fulltexo.importer.annotations.ImportFieldKey;

@Entity
public class Studio extends GenericEntity {

	@ImportFieldKey
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Studio [name=" + name + ", id=" + id + "]";
	}

}
