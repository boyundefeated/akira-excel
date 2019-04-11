package com.github.boyundefeated.akiraexcel.model;

import java.util.List;

public class RowFail {
	private Integer rowNo;
	private List<FailDetail> listFailDetail;

	public Integer getRowNo() {
		return rowNo;
	}

	public void setRowNo(Integer rowNo) {
		this.rowNo = rowNo;
	}

	public List<FailDetail> getListFailDetail() {
		return listFailDetail;
	}

	public void setListFailDetail(List<FailDetail> listFailDetail) {
		this.listFailDetail = listFailDetail;
	}

	@Override
	public String toString() {
		return "RowFail [rowNo=" + rowNo + ", listFailDetail=" + listFailDetail + "]";
	}

}
