package com.github.boyundefeated.akiraexcel.config;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import com.github.boyundefeated.akiraexcel.annotation.ExcelDateFormatImport;
import com.github.boyundefeated.akiraexcel.utils.AkiraExcelOptions;

/**
 * Created by duynh5 on 14/03/2019.
 */
public final class DefaultCasting implements Casting {

	private int primitiveIntegerValue(String value) {
		try {
			return new Integer(value);
		} catch (NumberFormatException nfe) {
			return 0;
		}
	}

	private Integer integerValue(String value, AkiraExcelOptions options) {
		try {
			return new Integer(value);
		} catch (NumberFormatException nfe) {
			if (Boolean.TRUE.equals(options.isPreferNullOverDefault())) {
				return null;
			}
			return 0;
		}
	}

	private long primitiveLongValue(String value) {
		try {
			return new Long(value);
		} catch (NumberFormatException nfe) {
			return 0L;
		}
	}

	private Long longValue(String value, AkiraExcelOptions options) {
		try {
			return new Long(value);
		} catch (NumberFormatException nfe) {
			if (Boolean.TRUE.equals(options.isPreferNullOverDefault())) {
				return null;
			}
			return 0L;
		}
	}

	private double primitiveDoubleValue(String value) {
		try {
			return new Double(value);
		} catch (NumberFormatException nfe) {
			return 0d;
		}
	}

	private Double doubleValue(String value, AkiraExcelOptions options) {
		try {
			return new Double(value);
		} catch (NumberFormatException nfe) {
			if (Boolean.TRUE.equals(options.isPreferNullOverDefault())) {
				return null;
			}
			return 0d;
		}
	}

	private float primitiveFloatValue(String value) {
		try {
			return new Float(value);
		} catch (NumberFormatException nfe) {
			return 0f;
		}
	}

	private Float floatValue(String value, AkiraExcelOptions options) {
		try {
			return new Float(value);
		} catch (NumberFormatException nfe) {
			if (Boolean.TRUE.equals(options.isPreferNullOverDefault())) {
				return null;
			}
			return 0f;
		}
	}

	private Date defaultDate(AkiraExcelOptions options) {
		if (Boolean.TRUE.equals(options.isPreferNullOverDefault())) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}

	private LocalDate defaultLocalDate(AkiraExcelOptions options) {
		if (Boolean.TRUE.equals(options.isPreferNullOverDefault())) {
			return null;
		}
		return LocalDate.now();
	}

	private BigDecimal bigDecimalValue(String value, AkiraExcelOptions options) {
		try {
			String clean = value != null ? value.replace(",", ".") : "";
			return new BigDecimal(clean);
		} catch (NumberFormatException nfe) {
			if (Boolean.TRUE.equals(options.isPreferNullOverDefault())) {
				return null;
			}
			return BigDecimal.ZERO;
		}
	}

	private Date dateValue(String value, AkiraExcelOptions options, Field field) {

		String datePattern = options.getDatePattern();
		ExcelDateFormatImport excelDateFormat = field.getAnnotation(ExcelDateFormatImport.class);
		if (excelDateFormat != null) {
			datePattern = excelDateFormat.pattern();
		}
		// if a date regex has been specified then it wont be null
		// so then make sure the string matches the pattern
		// if it doesn't, fall back to default
		// else continue to turn string into java date

		// the reason for this is sometime Java will manage to parse a string to a date
		// object
		// without any exceptions but since the string was not an exact match you get a
		// very strange date
//		if (options.getDateRegex() != null && !value.matches(options.getDateRegex())) {
		try {
			final SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
			sdf.setLenient(options.isDateLenient());
			return sdf.parse(value);
		} catch (ParseException e) {
			return defaultDate(options);
		}
	}

	private LocalDate localDateValue(String value, AkiraExcelOptions options, Field field) {
		String datePattern = options.getDatePattern();
		ExcelDateFormatImport excelDateFormat = field.getAnnotation(ExcelDateFormatImport.class);
		if (excelDateFormat != null) {
			datePattern = excelDateFormat.pattern();
		}
		// ISSUE #57
		// if a date regex has been specified then it wont be null
		// so then make sure the string matches the pattern
		// if it doesn't, fall back to default
		// else continue to turn string into java date

		// the reason for this is sometime java will manage to parse a string to a date
		// object
		// without any exceptions but since the string was not an exact match you get a
		// very strange date
//		if (options.getDateRegex() != null && !value.matches(options.getDateRegex())) {
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(datePattern);
			return LocalDate.parse(value, dtf);
		} catch (DateTimeParseException e) {
			return defaultLocalDate(options);
		}
	}

	private Object enumValue(String value, Class type) {
		return Arrays.stream(type.getEnumConstants()).filter(o -> ((Enum) o).name().equals(value)).findFirst()
				.orElse(null);
	}

	public Object castValue(Field field, String value, AkiraExcelOptions options) {

		if (options.isTrimCellValue()) {
			value = value.trim();
		}

		Object o = value;
		Class<?> fieldType = field.getType();
		if (fieldType.getName().equals("int")) {
			o = primitiveIntegerValue(value);

		} else if (fieldType.getName().equals("java.lang.Integer")) {
			o = integerValue(value, options);

		} else if (fieldType.getName().equals("java.math.BigDecimal")) {
			o = bigDecimalValue(value, options);

		} else if (fieldType.getName().equals("long")) {
			o = primitiveLongValue(value);

		} else if (fieldType.getName().equals("java.lang.Long")) {
			o = longValue(value, options);

		} else if (fieldType.getName().equals("double")) {
			o = primitiveDoubleValue(value);

		} else if (fieldType.getName().equals("java.lang.Double")) {
			o = doubleValue(value, options);

		} else if (fieldType.getName().equals("float")) {
			o = primitiveFloatValue(value);

		} else if (fieldType.getName().equals("java.lang.Float")) {
			o = floatValue(value, options);

		} else if (fieldType.getName().equals("boolean") || fieldType.getName().equals("java.lang.Boolean")) {
			o = Boolean.valueOf(value);

		} else if (fieldType.getName().equals("java.util.Date")) {
			o = dateValue(value, options, field);

		} else if (fieldType.getName().equals("java.time.LocalDate")) {
			o = localDateValue(value, options, field);

		} else if (fieldType.isEnum()) {
			o = enumValue(value, fieldType);

		} else {
			if (value.isEmpty()) {
				if (Boolean.TRUE.equals(options.isPreferNullOverDefault())) {
					o = null;
				} else {
					o = value;
				}
			} else {
				o = value;
			}
		}
		return o;
	}
}
