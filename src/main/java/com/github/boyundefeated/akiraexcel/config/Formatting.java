package com.github.boyundefeated.akiraexcel.config;

import com.github.boyundefeated.akiraexcel.utils.AkiraWriterFormatOptions;

public interface Formatting {
	 String formatValue(Object value, Class<?> fieldType, AkiraWriterFormatOptions formatOptions);
}
