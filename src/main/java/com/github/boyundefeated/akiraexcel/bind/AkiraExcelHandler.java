package com.github.boyundefeated.akiraexcel.bind;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.github.boyundefeated.akiraexcel.annotation.ExcelColumnIndex;
import com.github.boyundefeated.akiraexcel.annotation.ExcelColumnTitle;
import com.github.boyundefeated.akiraexcel.config.Casting;
import com.github.boyundefeated.akiraexcel.exception.AkiraExcelException;
import com.github.boyundefeated.akiraexcel.utils.AkiraExcelOptions;

public class AkiraExcelHandler<T> {

	private AkiraExcelOptions options;
	private final Casting casting;

	private Map<String, Integer> titles;

	public AkiraExcelHandler(Class<T> type, AkiraExcelOptions options, Map<String, Integer> titles) {
		this.options = options;
		this.titles = titles;
		casting = options.getCasting();

		titles = new HashMap<String, Integer>();
	}

	public T newInstanceOf(Class<T> type) {
		T newInstance;
		try {
			newInstance = type.getDeclaredConstructor().newInstance();
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException
				| InstantiationException e) {
			throw new AkiraExcelException("Cannot create a new instance of " + type.getName());
		}

		return newInstance;
	}

	public boolean setValue(Field field, int column, String content, Object ins) {
		ExcelColumnIndex index = field.getAnnotation(ExcelColumnIndex.class);
		if (index != null) {
			if (column == index.value()) {
				Object o = casting.castValue(field, content, options);
				setFieldData(field, o, ins);
				return true;
			}
		} else {
			ExcelColumnTitle excelColumnTitle = field.getAnnotation(ExcelColumnTitle.class);
			if (excelColumnTitle != null) {
				Integer titleColumn = titles.get(excelColumnTitle.value());
				// Fix both columns mapped to name passing this condition below
				if (titleColumn != null && titleColumn == column) {
					Object o = casting.castValue(field, content, options);
					setFieldData(field, o, ins);
					return true;
				}
			}
		}
		return false;

	}

	public void setValueDirectly(Field field, String defaultValue, Object instance) {
		try {
			field.setAccessible(true);
			Object oDefault = casting.castValue(field, defaultValue, options);
			field.set(instance, oDefault);
		} catch (IllegalAccessException e) {
			throw new AkiraExcelException("Unexpected cast {" + defaultValue + "} of field" + field.getName());
		}
	}


	public void setFieldData(Field field, Object o, Object instance) {
		try {
			field.setAccessible(true);
			field.set(instance, o);
		} catch (IllegalAccessException e) {
			throw new AkiraExcelException("Unexpected cast type {" + o + "} of field" + field.getName());
		}
	}

}
