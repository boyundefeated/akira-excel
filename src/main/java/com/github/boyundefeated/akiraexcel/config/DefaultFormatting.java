package com.github.boyundefeated.akiraexcel.config;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.github.boyundefeated.akiraexcel.utils.AkiraWriterFormatOptions;

/**
 * Created by duynh5 on 14/03/2019.
 */
public final class DefaultFormatting implements Formatting {

	@SuppressWarnings("rawtypes")
	@Override
	public String formatValue(Object value, Class<?> fieldType, AkiraWriterFormatOptions formatOptions) {
		if (value == null)
			return "";
		if (fieldType.getName().equals("java.util.Date")) {
			final SimpleDateFormat sdf = new SimpleDateFormat(formatOptions.getDatePattern());
			return sdf.format(value);
		} else if (fieldType.getName().equals("java.time.LocalDate")) {
			final DateTimeFormatter fmt = formatOptions.getDateTimeFormatter();
			return ((LocalDate) value).format(fmt);
		} else if (fieldType.isEnum()) {
			return ((Enum) value).name();
		} else {
			return value == null ? "" : value.toString();
		}
	}
}
