package com.github.boyundefeated.akiraexcel.config;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import com.github.boyundefeated.akiraexcel.annotation.ExcelDateFormatExport;
import com.github.boyundefeated.akiraexcel.annotation.ExcelDateFormatImport;
import com.github.boyundefeated.akiraexcel.utils.AkiraWriterFormatOptions;

/**
 * Created by duynh5 on 14/03/2019.
 */
public final class DefaultFormatting implements Formatting {

	@SuppressWarnings("rawtypes")
	@Override
	public String formatValue(Object value, Field field, AkiraWriterFormatOptions formatOptions) {
		Class<?> fieldType = field.getType();
		String datePattern = formatOptions.getDatePattern();
    	ExcelDateFormatExport excelDateFormat = field.getAnnotation(ExcelDateFormatExport.class);
    	TimeZone zone = null;
    	if(excelDateFormat != null) {
    		datePattern = excelDateFormat.pattern();
    		zone = TimeZone.getTimeZone(excelDateFormat.timeZone());
    	}
		if (value == null)
			return "";
		if (fieldType.getName().equals("java.util.Date")) {
			final SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
			if(zone != null) {
				sdf.setTimeZone(zone);
			}
			return sdf.format(value);
		} else if (fieldType.getName().equals("java.time.LocalDate")) {
			final DateTimeFormatter fmt = DateTimeFormatter.ofPattern(datePattern);
			return ((LocalDate) value).format(fmt);
		} else if (fieldType.isEnum()) {
			return ((Enum) value).name();
		} else {
			return value == null ? "" : value.toString();
		}
	}
}
