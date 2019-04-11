package com.github.boyundefeated.akiraexcel.model;

public class FailDetail {
	private String cellAddress;
	private Integer colNo;
	private String fieldName;
	private String message;
	private String invalidValue;

	public Integer getColNo() {
		return colNo;
	}

	public void setColNo(Integer colNo) {
		this.colNo = colNo;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getInvalidValue() {
		return invalidValue;
	}

	public void setInvalidValue(String invalidValue) {
		this.invalidValue = invalidValue;
	}

	public String getCellAddress() {
		return cellAddress;
	}

	public void setCellAddress(String cellAddress) {
		this.cellAddress = cellAddress;
	}

	@Override
	public String toString() {
		return "FailDetail [cellAddress=" + cellAddress + ", colNo=" + colNo + ", fieldName=" + fieldName + ", message="
				+ message + ", invalidValue=" + invalidValue + "]";
	}

}
