package com.github.boyundefeated.akiraexcel.exception;

import java.util.List;

import com.github.boyundefeated.akiraexcel.model.RowFail;

/**
 * Created by duynh5 on 11/04/2019
 */
public class ExcelRowReaderErrorException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private List<RowFail> listFail;

	public ExcelRowReaderErrorException(List<RowFail> listFail, String message) {
		super(message);
		this.listFail = listFail;
	}

	public ExcelRowReaderErrorException(List<RowFail> listFail, String message, Throwable cause) {
		super(message, cause);
		this.listFail = listFail;

	}

	public List<RowFail> getListFail() {
		return listFail;
	}

}
