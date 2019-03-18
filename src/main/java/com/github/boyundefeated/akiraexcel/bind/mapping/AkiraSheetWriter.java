package com.github.boyundefeated.akiraexcel.bind.mapping;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.github.boyundefeated.akiraexcel.annotation.ExcelColumnIndex;
import com.github.boyundefeated.akiraexcel.annotation.ExcelColumnTitle;
import com.github.boyundefeated.akiraexcel.config.AkiraCellStyle;
import com.github.boyundefeated.akiraexcel.config.Formatting;
import com.github.boyundefeated.akiraexcel.utils.AkiraWriterFormatOptions;

public class AkiraSheetWriter<T> {
	private Sheet sheet;
	private List<T> data;
	private Class<?> type;
	private List<String> titles;
	private Map<String, Field> fieldMap;
	private Formatting formatting;
	private AkiraWriterFormatOptions options;
	private AkiraCellStyle akiraCellStyle;

	public AkiraSheetWriter(Sheet sheet, final List<T> data, final Class<?> type, AkiraWriterFormatOptions options)
			throws ClassNotFoundException {
		super();
		this.sheet = sheet;
		this.data = data;
		this.type = type;
		this.titles = new ArrayList<>();
		this.fieldMap = new HashMap<>();
		this.formatting = options.getFormatting();
		this.options = options;
		this.akiraCellStyle =  options.getAkiraCellStyle();
		
		List<Field> lsField = new ArrayList<>();
		Class<?> superClass = type;
		while(superClass != null) {
			Field[] fields = superClass.getDeclaredFields();
			for (Field f : fields) {
				lsField.add(f);
			}
			superClass = superClass.getSuperclass();
		}
//		lsField.stream().forEach(f -> System.out.println(f.getName()));
//		Field[] fields = this.type.getDeclaredFields();
		for (Field field : lsField) {
			ExcelColumnIndex index = field.getAnnotation(ExcelColumnIndex.class);
			if (index != null) {
				// default title is Field name
				titles.add(index.value(), field.getName());
				fieldMap.put(field.getName(), field);
			} else {
				ExcelColumnTitle columnTitle = field.getAnnotation(ExcelColumnTitle.class);
				if (columnTitle != null) {
					titles.add(columnTitle.value());
					fieldMap.put(columnTitle.value(), field);
				}
			}

		}
	}

	public void write() {
		Objects.requireNonNull(this.sheet);
		Objects.requireNonNull(this.data);
		// write title
		Row row0 = sheet.createRow(0);
		for (int i = 0; i < titles.size(); i++) {
			Cell cell = row0.createCell(i);
			cell.setCellValue(titles.get(i));
			cell.setCellStyle(akiraCellStyle.getTitleCellStyle());
		}
		// write data
		for (int rowIndex = 1; rowIndex <= data.size(); rowIndex++) {
			// iterating r number of rows
			Row currentRow = sheet.createRow(rowIndex);
			T rowData = data.get(rowIndex - 1);
			for (int columnIndex = 0; columnIndex < titles.size(); columnIndex++) {
				String columnTitle = titles.get(columnIndex);
				Field f = this.fieldMap.get(columnTitle);
				f.setAccessible(true);
				Cell cell = currentRow.createCell(columnIndex);
				cell.setCellStyle(akiraCellStyle.getContentStyle());

				try {
					String value = formatting.formatValue(f.get(rowData), f.getType(), this.options);
					cell.setCellValue(value);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		// auto sizing column
		for (int i = 0; i <= titles.size(); i++) {
			sheet.autoSizeColumn(i);
		}
		
	}

}
