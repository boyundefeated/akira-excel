package com.github.boyundefeated.akiraexcel.config;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;

public class DefaultCellStyle implements AkiraCellStyle {
	protected Workbook workbook;

	public DefaultCellStyle(Workbook workbook) {
		super();
		this.workbook = workbook;
	}

	@Override
	public CellStyle getTitleCellStyle() {
		CellStyle style = workbook.createCellStyle();

		style.setBorderTop(BorderStyle.DOUBLE);
		style.setBorderBottom(BorderStyle.DOUBLE);
		style.setBorderLeft(BorderStyle.DOUBLE);
		style.setBorderRight(BorderStyle.DOUBLE);

		style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		// We also define the font that we are going to use for displaying the
		// data of the cell. We set the font to ARIAL with 20pt in size and
		// make it BOLD and give blue as the color.

		Font font = workbook.createFont();
		font.setFontName("Arial");
		font.setFontHeightInPoints((short) 12);
		font.setBold(true);
		font.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
		style.setFont(font);
		return style;
	}

	@Override
	public CellStyle getContentStyle() {
		CellStyle style = workbook.createCellStyle();

		style.setBorderTop(BorderStyle. THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
//		style.setFillBackgroundColor(HSSFColor.BLUE.index);

		Font font = workbook.createFont();
		font.setFontName("Arial");
		font.setFontHeightInPoints((short) 11);
//		font.setColor(HSSFColor.WHITE.index);
		style.setFont(font);
		return style;
	}

}
