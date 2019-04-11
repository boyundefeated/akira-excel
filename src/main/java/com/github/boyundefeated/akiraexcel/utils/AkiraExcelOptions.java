package com.github.boyundefeated.akiraexcel.utils;

import static com.github.boyundefeated.akiraexcel.utils.AkiraExcelConstants.DEFAULT_DATE_PATTERN;

import java.util.Objects;

import com.github.boyundefeated.akiraexcel.config.Casting;
import com.github.boyundefeated.akiraexcel.config.DefaultCasting;
import com.github.boyundefeated.akiraexcel.exception.AkiraExcelException;

public final class AkiraExcelOptions {

	private int dataRowStartAt;
	private int skipRowAfterHeader;
	private int sheetIndex;
	private boolean dateLenient;
	private boolean trimCellValue;
	private boolean preferNullOverDefault;
	private String datePattern;
	private Casting casting;
	private int headerRow;
	private boolean hasHeaderRow;
	private boolean usingObjectValidator;

	private AkiraExcelOptions() {
		super();
	}

	public AkiraExcelOptions setDataRowStartAt(int dataRowStartAt) {
		this.dataRowStartAt = dataRowStartAt;
		return this;
	}

	public AkiraExcelOptions setSkipRowAfterHeader(int skipRowAfterHeader) {
		this.skipRowAfterHeader = skipRowAfterHeader;
		return this;
	}

	public AkiraExcelOptions setSheetIndex(int sheetIndex) {
		this.sheetIndex = sheetIndex;
		return this;
	}

	public AkiraExcelOptions setDateLenient(boolean dateLenient) {
		this.dateLenient = dateLenient;
		return this;
	}

	public AkiraExcelOptions setTrimCellValue(boolean trimCellValue) {
		this.trimCellValue = trimCellValue;
		return this;
	}

	public AkiraExcelOptions setPreferNullOverDefault(boolean preferNullOverDefault) {
		this.preferNullOverDefault = preferNullOverDefault;
		return this;
	}

	public AkiraExcelOptions setDatePattern(String datePattern) {
		this.datePattern = datePattern;
		return this;
	}

	public AkiraExcelOptions setCasting(Casting casting) {
		this.casting = casting;
		return this;
	}

	public AkiraExcelOptions setHeaderRow(int headerRow) {
		this.headerRow = headerRow;
		return this;
	}

	public AkiraExcelOptions setHasHeaderRow(boolean hasHeaderRow) {
		this.hasHeaderRow = hasHeaderRow;
		return this;
	}

	public AkiraExcelOptions setUsingObjectValidator(boolean usingObjectValidator) {
		this.usingObjectValidator = usingObjectValidator;
		return this;
	}

	public int getDataRowStartAt() {
		return dataRowStartAt;
	}

	public int getSkipRowAfterHeader() {
		return skipRowAfterHeader;
	}

	public int getSheetIndex() {
		return sheetIndex;
	}

	public boolean isDateLenient() {
		return dateLenient;
	}

	public boolean isTrimCellValue() {
		return trimCellValue;
	}

	public boolean isPreferNullOverDefault() {
		return preferNullOverDefault;
	}

	public String getDatePattern() {
		return datePattern;
	}

	public Casting getCasting() {
		return casting;
	}

	public int getHeaderRow() {
		return headerRow;
	}

	public boolean isHasHeaderRow() {
		return hasHeaderRow;
	}

	public boolean isUsingObjectValidator() {
		return usingObjectValidator;
	}

	public static class Builder {
		private int dataRowStartAt = 0;
		private int skipRowAfterHeader = 0;
		private int sheetIndex;
		private boolean dateLenient = false;
		private boolean trimCellValue = true;
		private boolean preferNullOverDefault = true;
		private String datePattern = DEFAULT_DATE_PATTERN;
		private Casting casting = new DefaultCasting();
		private int headerRow = 0;
		private boolean hasHeaderRow = true;
		private boolean usingObjectValidator = false;

		/**
		 * Skip a number of rows after the header row. The header row is not counted.
		 *
		 * @param skipRowAfterHeader ignored row number after the header row
		 * @return builder itself
		 */
		public Builder skipRowAfterHeader(int skipRowAfterHeader) {
			if (skipRowAfterHeader < 0) {
				throw new AkiraExcelException("Skip index must be greater than or equal to 0");
			}
			this.skipRowAfterHeader = skipRowAfterHeader;
			return this;
		}

		/**
		 * Set the start row contains data Note: row is counted from 0
		 *
		 * @param dataRowStartAt the start data row
		 * @return builder itself
		 */
		public Builder dataRowStartAt(int dataRowStartAt) {
			if (dataRowStartAt < 0) {
				throw new AkiraExcelException("Skip index must be greater than or equal to 0");
			}
			this.dataRowStartAt = dataRowStartAt;
			return this;
		}

		/**
		 * set date pattern, default date format is "dd/MM/yyyy" for java.util.Date
		 *
		 * @param datePattern date time formatter
		 * @return this
		 */
		public Builder datePattern(String datePattern) {
			this.datePattern = datePattern;
			return this;
		}

		/**
		 * set whether or not to use null instead of default values for Integer, Double,
		 * Float, Long, String and java.util.Date types.
		 *
		 * @param preferNullOverDefault boolean
		 * @return this
		 */
		public Builder preferNullOverDefault(boolean preferNullOverDefault) {
			this.preferNullOverDefault = preferNullOverDefault;
			return this;
		}

		/**
		 * set sheet index, default is 0 Note: counted from 0
		 * 
		 * @param sheetIndex number
		 * @return this
		 */
		public Builder sheetIndex(int sheetIndex) {
			if (sheetIndex < 0) {
				throw new AkiraExcelException("Sheet index must be greater than or equal to 0");
			}
			this.sheetIndex = sheetIndex;
			return this;
		}

		/**
		 * Trim cell value or not Default trimCellValue is true
		 *
		 * @param trimCellValue trim the cell value before processing work book.
		 * @return this
		 */
		public Builder trimCellValue(boolean trimCellValue) {
			this.trimCellValue = trimCellValue;
			return this;
		}

		/**
		 * If the simple date format is lenient, use to set how strict the date
		 * formatting must be, defaults to lenient false. It works only for
		 * java.util.Date. Default value is false
		 *
		 * @param dateLenient true or false
		 * @return this
		 */
		public Builder dateLenient(boolean dateLenient) {
			this.dateLenient = dateLenient;
			return this;
		}

		/**
		 * Use a custom casting implementation
		 *
		 * @param casting custom casting implementation
		 * @return this
		 */
		public Builder withCasting(Casting casting) {
			Objects.requireNonNull(casting);
			this.casting = casting;
			return this;
		}

		/**
		 * The row which the AkiraWrapper will use to start reading header titles, in
		 * case the header is not in row 0. AkiraWraper will use read the first row for
		 * header by default
		 *
		 * @param hasHeaderRow true or false
		 * @return this
		 */
		public Builder hasHeaderRow(boolean hasHeaderRow) {
			this.hasHeaderRow = hasHeaderRow;
			return this;
		}

		/**
		 * Whether Excel file has header row or not Default is True Note: row is counted
		 * from 0
		 *
		 * @param headerRow an index number of the excel header to start reading header
		 * @return this
		 */
		public Builder headerRow(int headerRow) {
			if (headerRow < 0) {
				throw new AkiraExcelException("Header row index must be greater than or equal to 0");
			}
			this.headerRow = headerRow;
			return this;
		}

		/**
		 * After parsing value, AkiraWrapper will use Java Validation to validate object
		 * and throws ExcelRowReaderErrorException if any constraint id not valid Default
		 * value is False
		 *
		 * @param usingObjectValidator true or false
		 * @return this
		 */
		public Builder usingObjectValidator(boolean usingObjectValidator) {
			this.usingObjectValidator = usingObjectValidator;
			return this;
		}

		public AkiraExcelOptions build() {
			// @formatter:off
			return new AkiraExcelOptions()
					.setDataRowStartAt(dataRowStartAt)
					.setSkipRowAfterHeader(skipRowAfterHeader)
					.setSheetIndex(sheetIndex)
					.setDateLenient(dateLenient)
					.setTrimCellValue(trimCellValue)
					.setPreferNullOverDefault(preferNullOverDefault)
					.setDatePattern(datePattern)
					.setCasting(casting)
					.setHeaderRow(headerRow)
					.setHasHeaderRow(hasHeaderRow)
					.setUsingObjectValidator(usingObjectValidator);
			// @formatter:on
		}

	}

}
