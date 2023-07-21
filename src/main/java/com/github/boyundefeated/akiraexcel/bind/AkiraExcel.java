package com.github.boyundefeated.akiraexcel.bind;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import com.github.boyundefeated.akiraexcel.model.SheetDataModel;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.github.boyundefeated.akiraexcel.bind.mapping.AkiraSheetWriter;
import com.github.boyundefeated.akiraexcel.bind.mapping.AkiraWraper;
import com.github.boyundefeated.akiraexcel.exception.AkiraExcelException;
import com.github.boyundefeated.akiraexcel.utils.AkiraExcelOptions;
import com.github.boyundefeated.akiraexcel.utils.AkiraExcelType;
import com.github.boyundefeated.akiraexcel.utils.AkiraWriterFormatOptions;
import com.github.boyundefeated.akiraexcel.utils.FileHelper;

/**
 * The entry point of the mapping process.
 *
 * Example:
 *
 * List{Employee} employees = AkiraExcel.fromExcel(new File("employees.xls"), Employee.class);
 * employees.size(); // 3
 *
 * Workbook wb = AkiraExcel.toExcel(employees);
 *
 * NOTE @ExcelColumnIndex is higher priority than @ExcelColumnTitle
 *
 */
public final class AkiraExcel {

	private AkiraExcel() {
	}

	/**
	 * @author duynh5
	 * @param <T> The expected class of the value.
	 * @param file Excel file to import
	 * @param type The type of Class corresponding with a row in excel
	 * @param options Options for parsing excel
	 * @return A list of object in java
	 */
	public static synchronized <T> List<T> fromExcel(final File file, final Class<T> type, AkiraExcelOptions options) {
		AkiraExcelType excelType = FileHelper.getType(file.getName());
		AkiraWraper<T> wraper = new AkiraWraper<T>(file, excelType, type, options);
		List<T> list = wraper.deserialize();
		return list;
	}

	/**
	 * @author duynh5
	 * @param <T> The expected class of the value.
	 * @param inputStream The input stream of excel file
	 * @param excelType Type of excel, must be XLS or XLSX
	 * @param type The type of Class corresponding with a row in excel
	 * @param options Options for parsing excel
	 * @return A list of object in java
	 */
	public static synchronized <T> List<T> fromExcel(final InputStream inputStream, AkiraExcelType excelType,
			final Class<T> type, AkiraExcelOptions options) {
		AkiraWraper<T> wraper = new AkiraWraper<T>(inputStream, excelType, type, options);
		List<T> list = wraper.deserialize();
		return list;
	}

	/**
	 * @author duynh5
	 * @param <T> The expected class of the value.
	 * @param file Excel file to import
	 * @param type The type of Class corresponding with a row in excel
	 * @return A list of object in java
	 */
	public static synchronized <T> List<T> fromExcel(final File file, final Class<T> type) {
		AkiraExcelType excelType = FileHelper.getType(file.getName());
		AkiraWraper<T> wraper = new AkiraWraper<T>(file, excelType, type);
		List<T> list = wraper.deserialize();
		return list;
	}

	/**
	 * @author duynh5
	 * @param <T> The expected class of the value.
	 * @param inputStream The input stream of excel file
	 * @param excelType Type of excel, must be XLS or XLSX
	 * @param type The type of Class corresponding with a row in excel
	 * @return A list of object in java
	 */
	public static synchronized <T> List<T> fromExcel(final InputStream inputStream, AkiraExcelType excelType,
			final Class<T> type) {
		AkiraWraper<T> wraper = new AkiraWraper<T>(inputStream, excelType, type);
		List<T> list = wraper.deserialize();
		return list;
	}

	/**
	 * @author duynh5
	 * @param <T> The expected class of the value.
	 * @param data List data to be exported
	 * @param type The class type of data
	 * @param options The options
	 * @return A excel workbook
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static synchronized <T> Workbook toExcel(List<T> data, Class<?> type, AkiraWriterFormatOptions options) {
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("Data");
		options.validate(wb); // important
		try {
			AkiraSheetWriter<T> sheetWriter = new AkiraSheetWriter(sheet, data, type, options);
			sheetWriter.write();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return wb;
	}

	/**
	 * @author quangbs
	 * @param sheetDataModels object data to be exported
	 * @return A excel workbook
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static synchronized Workbook toExcelMultipleSheet(List<SheetDataModel> sheetDataModels) {
		XSSFWorkbook wb = new XSSFWorkbook();
		sheetDataModels.forEach(sheetDataModel -> {
			XSSFSheet sheet = wb.createSheet(sheetDataModel.getSheetName());
			sheetDataModel.getOptions().validate(wb); // important
			try {
				AkiraSheetWriter<?> sheetWriter = new AkiraSheetWriter<>(sheet, sheetDataModel.getData(), sheetDataModel.getClazz(), sheetDataModel.getOptions());
				sheetWriter.write();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		});
		return wb;
	}

	/**
	 * @author duynh5
	 * @param <T> The expected class of the value.
	 * @param data List data to be exported
	 * @param type The class type of data
	 * @return A excel workbook
	 */
	public static synchronized <T> Workbook toExcel(List<T> data, Class<?> type) {
		return toExcel(data, type, new AkiraWriterFormatOptions.Builder().build());
	}

	/**
	 * @author duynh5
	 * @param <T> The expected class of the value.
	 * @param data List data to be exported
	 * @param options The options
	 * @return A excel workbook
	 */
	public static synchronized <T> Workbook toExcel(List<T> data, AkiraWriterFormatOptions options) {
		Class<? extends Object> type = null;
		try {
			type = data.get(0).getClass();
		} catch (Exception e) {
			throw new AkiraExcelException("Can not found Class for data list");
		}
		return toExcel(data, type, options);
	}

	/**
	 * @author duynh5
	 * @param <T> The expected class of the value.
	 * @param data List data to be exported
	 * @return A excel workbook
	 */
	public static synchronized <T> Workbook toExcel(List<T> data) {
		try {
			Class<? extends Object> type = data.get(0).getClass();
			return toExcel(data, type);
		} catch (Exception e) {
			throw new AkiraExcelException("Can not found Class for data list");
		}
	}
}
