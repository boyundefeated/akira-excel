package com.github.boyundefeated.akiraexcel.config;

import java.lang.reflect.Field;

import com.github.boyundefeated.akiraexcel.utils.AkiraWriterFormatOptions;

public interface Formatting {
	String formatValue(Object value, Field field, AkiraWriterFormatOptions formatOptions);
}
