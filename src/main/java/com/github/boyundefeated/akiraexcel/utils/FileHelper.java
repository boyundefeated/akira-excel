package com.github.boyundefeated.akiraexcel.utils;

import com.github.boyundefeated.akiraexcel.exception.AkiraExcelException;

/**
 * Created by duyh5 on 18/03/2019.
 */
public final class FileHelper {

	private FileHelper() {
	}

	public static AkiraExcelType getType(String fileName) {
		String extension = getExtension(fileName);
		if (".xls".equals(extension)) {
			return AkiraExcelType.XLS;
		} else if (".xlsx".equals(extension)) {
			return AkiraExcelType.XLSX;
		}
		throw new AkiraExcelException("File type not supported, file type must be .xls or .xlsx");
	}

	public static String getExtension(String fileName) {
		int i = fileName.lastIndexOf('.');
		if (i >= 0) {
			return fileName.substring(i);
		}
		return "";
	}
}
