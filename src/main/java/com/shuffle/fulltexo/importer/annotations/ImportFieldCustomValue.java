package com.shuffle.fulltexo.importer.annotations;

import java.lang.annotation.ElementType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.shuffle.fulltexo.importer.adapters.CustomValueAdapter;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ImportFieldCustomValue {

	Class<? extends CustomValueAdapter<?>> customValue();
}
