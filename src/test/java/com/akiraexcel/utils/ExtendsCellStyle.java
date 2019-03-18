package com.akiraexcel.utils;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;

import com.github.boyundefeated.akiraexcel.config.DefaultCellStyle;

public class ExtendsCellStyle extends DefaultCellStyle {

	public ExtendsCellStyle(Workbook workbook) {
		super(workbook);
	}

	@Override
	public CellStyle getTitleCellStyle() {
		CellStyle style = workbook.createCellStyle();

		style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.YELLOW.getIndex());
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

}
