package com.github.boyundefeated.akiraexcel.utils;

import static com.github.boyundefeated.akiraexcel.utils.AkiraExcelConstants.DEFAULT_DATE_PATTERN;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.poi.ss.usermodel.Workbook;

import com.github.boyundefeated.akiraexcel.config.AkiraCellStyle;
import com.github.boyundefeated.akiraexcel.config.DefaultCellStyle;
import com.github.boyundefeated.akiraexcel.config.DefaultFormatting;
import com.github.boyundefeated.akiraexcel.config.Formatting;
import com.github.boyundefeated.akiraexcel.exception.AkiraExcelException;

public class AkiraWriterFormatOptions {
	private String datePattern;
	private Formatting formatting;
	private AkiraCellStyle akiraCellStyle;
	private Class<?> cellStyleClazz;

	private AkiraWriterFormatOptions() {
		super();
	}

	public String getDatePattern() {
		return datePattern;
	}

	public AkiraWriterFormatOptions setDatePattern(String datePattern) {
		this.datePattern = datePattern;
		return this;
	}

	public Formatting getFormatting() {
		return formatting;
	}

	public AkiraWriterFormatOptions setFormatting(Formatting formatting) {
		this.formatting = formatting;
		return this;
	}

	public AkiraCellStyle getAkiraCellStyle() {
		return akiraCellStyle;
	}

	public AkiraWriterFormatOptions setAkiraCellStyle(AkiraCellStyle akiraCellStyle) {
		this.akiraCellStyle = akiraCellStyle;
		return this;
	}

	public Class<?> getCellStyleClazz() {
		return cellStyleClazz;
	}

	public AkiraWriterFormatOptions setCellStyleClazz(Class<?> cellStyleClazz) {
		this.cellStyleClazz = cellStyleClazz;
		return this;
	}

	public void validate(Workbook workbook) {
		akiraCellStyle = new DefaultCellStyle(workbook);
		Constructor<?> cons;
		try {
			cons = this.cellStyleClazz.getConstructor(Workbook.class);
			this.akiraCellStyle = (AkiraCellStyle) cons.newInstance(workbook);
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			throw new AkiraExcelException("Can not construct class " + this.cellStyleClazz.getName());
		}
	}

	public static class Builder {

		private String datePattern = DEFAULT_DATE_PATTERN;
		private Formatting formatting = new DefaultFormatting();
		private Class<?> cellStyleClazz = DefaultCellStyle.class;

		public Builder datePattern(String datePattern) {
			this.datePattern = datePattern;
			return this;
		}

		public Builder formatting(Formatting formatting) {
			this.formatting = formatting;
			return this;
		}

		public Builder cellStyle(Class<?> clazz) {
			cellStyleClazz = clazz;

			return this;
		}

		public AkiraWriterFormatOptions build() {
			return new AkiraWriterFormatOptions().setDatePattern(datePattern).setFormatting(formatting)
					.setCellStyleClazz(cellStyleClazz);
		}

	}
}
