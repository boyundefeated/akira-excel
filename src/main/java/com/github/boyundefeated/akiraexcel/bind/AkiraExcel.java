package com.github.boyundefeated.akiraexcel.bind;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.github.boyundefeated.akiraexcel.bind.mapping.AkiraSheetWriter;
import com.github.boyundefeated.akiraexcel.bind.mapping.AkiraWraper;
import com.github.boyundefeated.akiraexcel.exception.AkiraExcelException;
import com.github.boyundefeated.akiraexcel.utils.AkiraExcelType;
import com.github.boyundefeated.akiraexcel.utils.AkiraWriterFormatOptions;
import com.github.boyundefeated.akiraexcel.utils.FileHelper;

/**
 * The entry point of the mapping process.
 * <p>
 * Example:
 * 
 * <pre>
 * List<Employee> employees = AkiraExcel.fromExcel(new File("employees.xls"),
 * Employee.class); employees.size(); // 3
 * 
 * NOTE @ExcelColumnIndex is higher priority than @ExcelColumnTitle
 * 
 */
public final class AkiraExcel {

	private AkiraExcel() {
	}

	/**
	 * @author duynh5
	 * 
	 */
	public static synchronized <T> List<T> fromExcel(final File file, final Class<T> type) {
		AkiraExcelType excelType = FileHelper.getType(file.getName());
		AkiraWraper<T> wraper = new AkiraWraper<T>(file, excelType, type);
		List<T> list = wraper.deserialize();
		return list;
	}

	public static synchronized <T> List<T> fromExcel(final InputStream inputStream, AkiraExcelType excelType,
			final Class<T> type) {
		AkiraWraper<T> wraper = new AkiraWraper<T>(inputStream, excelType, type);
		List<T> list = wraper.deserialize();
		return list;
	}

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

	public static synchronized <T> Workbook toExcel(List<T> data, Class<?> type) {
		return toExcel(data, type, new AkiraWriterFormatOptions.Builder().build());
	}

	public static synchronized <T> Workbook toExcel(List<T> data, AkiraWriterFormatOptions options) {
		Class<? extends Object> type = null;
		try {
			type = data.get(0).getClass();
		} catch (Exception e) {
			throw new AkiraExcelException("Can not found Class for data list");
		}
		return toExcel(data, type, options);
	}

	public static synchronized <T> Workbook toExcel(List<T> data) {
		try {
			Class<? extends Object> type = data.get(0).getClass();
			return toExcel(data, type);
		} catch (Exception e) {
			throw new AkiraExcelException("Can not found Class for data list");
		}
	}
}
