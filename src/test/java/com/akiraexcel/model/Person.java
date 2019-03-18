package com.akiraexcel.model;

import com.github.boyundefeated.akiraexcel.annotation.ExcelColumnTitle;

public class Person {

//    @ExcelColumnIndex(0)
	@ExcelColumnTitle("WEIGHT")
	protected String weight;

	@ExcelColumnTitle("HEIGHT")
	protected String height;

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

}
