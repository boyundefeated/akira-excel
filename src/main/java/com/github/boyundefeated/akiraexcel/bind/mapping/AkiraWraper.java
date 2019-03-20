package com.github.boyundefeated.akiraexcel.bind.mapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.github.boyundefeated.akiraexcel.annotation.ExcelColumnDefaultValue;
import com.github.boyundefeated.akiraexcel.annotation.ExcelColumnIndex;
import com.github.boyundefeated.akiraexcel.annotation.ExcelColumnNotBlank;
import com.github.boyundefeated.akiraexcel.bind.AkiraExcelHandler;
import com.github.boyundefeated.akiraexcel.exception.AkiraExcelException;
import com.github.boyundefeated.akiraexcel.utils.AkiraExcelOptions;
import com.github.boyundefeated.akiraexcel.utils.AkiraExcelType;

public class AkiraWraper<T> {

	private Class<T> type;
	private InputStream inputStream;
	private Map<String, Integer> titles;
	private AkiraExcelHandler<T> handler;
	private DataFormatter dataFormatter;
	private AkiraExcelType excelType;
	private List<Field> fields = new ArrayList<>();

	public AkiraWraper(final File excelFile,  final AkiraExcelType excelType, final Class<T> type) {
		this(type);
		this.excelType = excelType;
		try {
			FileInputStream inputStream = new FileInputStream(excelFile);
			this.inputStream = inputStream;
		} catch (FileNotFoundException e) {
			throw new AkiraExcelException("File not found", e);
		}

	}

	public AkiraWraper(final InputStream inputStream, final AkiraExcelType excelType,  final Class<T> type) {
		this(type);
		this.inputStream = inputStream;
		this.excelType = excelType;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private AkiraWraper(final Class<T> type) {
		this.titles = new HashMap<>();
		this.type = type;
		this.handler = new AkiraExcelHandler(type, AkiraExcelOptions.AkiraExcelOptionsBuilder.settings().build(),
				titles);
		this.dataFormatter = new DataFormatter();
		Class<?> superClass = type;
		while(superClass != null) {
			Field[] fieldArray = superClass.getDeclaredFields();
			for (Field f : fieldArray) {
				fields.add(f);
			}
			superClass = superClass.getSuperclass();
		}
	}

	@SuppressWarnings({ "resource" })
	public List<T> deserialize() {
		try {
			Workbook workbook;
			if(AkiraExcelType.XLSX.equals(excelType)) {
				workbook = new XSSFWorkbook(this.inputStream);
			}else {
				workbook = new HSSFWorkbook(this.inputStream);
			}
			// default only read sheet 0
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();

			List<T> resultList = new ArrayList<>();
			while (iterator.hasNext()) {
				Row currentRow = iterator.next();
				// display row number in the console.
				Iterator<Cell> cellIterator = currentRow.iterator();
				if (currentRow.getRowNum() == 0) {
					// update title of column
					while (cellIterator.hasNext()) {
						Cell currentCell = cellIterator.next();
						int collumIndex = currentCell.getColumnIndex();
						String content = dataFormatter.formatCellValue(currentCell);
						this.titles.put(content, collumIndex);
					}
					continue; // just skip the rows if row number is 0
				} else {
					T instance = handler.newInstanceOf(type);
//					Field[] fields = type.getDeclaredFields();
					while (cellIterator.hasNext()) {
						Cell currentCell = cellIterator.next();
						int collumIndex = currentCell.getColumnIndex();
						String content = dataFormatter.formatCellValue(currentCell);
						for (Field field : this.fields) {
							if (handler.setValue(field, collumIndex, content, instance)) {
								break;
							}
						}
					}
					// check if field can not be null
					boolean checkCanBeNull = true;
					for (Field field : this.fields) {
						field.setAccessible(true);
						Object o = getValueOfField(field, instance);
						
						if(o == null) {
							// check if has default value
							ExcelColumnDefaultValue defaultValue = field.getAnnotation(ExcelColumnDefaultValue.class);
							if(defaultValue != null) {
								this.handler.setValueDirectly(field, defaultValue.value(), instance);
							}
						}
						
						// check if field is null
						ExcelColumnNotBlank columnNotBlank = field.getAnnotation(ExcelColumnNotBlank.class);
						if(columnNotBlank != null) {
							o = getValueOfField(field, instance);
							if(o == null || String.valueOf(o).equals("")) {
								throw new AkiraExcelException("Field " + field.getName() + " can not be blank at row " + currentRow.getRowNum());
							}
						}
					}
					if(checkCanBeNull) {
						resultList.add(instance);
					}
				}

			}
//			System.out.println("Titles");
//			this.titles.keySet().stream().forEach(key -> System.out.println(key + " -- " + this.titles.get(key)));
//			resultList.stream().forEach(e -> System.out.println(e.toString()));

			return resultList;

		} catch (FileNotFoundException e) {
			throw new AkiraExcelException("File not found", e);
		} catch (IOException e) {
			throw new AkiraExcelException("IOException", e);
		}
	}

	private Object getValueOfField(Field field, Object instance) {
		Object o = null;
		try {
			o = field.get(instance);
		} catch (IllegalArgumentException | IllegalAccessException e) {
		}
		return o;
	}
}
