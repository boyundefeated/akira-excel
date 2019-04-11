package com.akiraexcel.model;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.github.boyundefeated.akiraexcel.annotation.ExcelColumnIndex;
import com.github.boyundefeated.akiraexcel.annotation.ExcelColumnTitle;
import com.github.boyundefeated.akiraexcel.annotation.ExcelDateFormatExport;

public class EmployeeWithValidation extends Person {

//    @ExcelColumnIndex(0)
	@ExcelColumnTitle("ID")
	protected long employeeId;

//    @ExcelColumnIndex(1)
	@NotBlank
	@ExcelColumnTitle("NAME")
	protected String name = "";

//    @ExcelColumnIndex(2)
	@ExcelColumnTitle("SURNAME")
	protected String surname;

//    @ExcelColumnIndex(3)
	@Min(5)
	@Max(50)
	@ExcelColumnTitle("AGE")
	protected Integer age;

//    @ExcelColumnIndex(4)
	@ExcelColumnTitle("SINGLE")
	protected boolean single;

	@ExcelDateFormatExport(pattern = "dd-MM-YYYY HH:mm")
	@ExcelColumnIndex(2)
	@ExcelColumnTitle("BIRTHDAY")
	protected Date birthday;

	/*
	 * We normally don't need getters and setters to map excel cells to fields
	 */

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isSingle() {
		return single;
	}

	public void setSingle(boolean single) {
		this.single = single;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String toString() {
		return "Employee{" + "employeeId=" + employeeId + ", name='" + name + '\'' + ", surname='" + surname + '\''
				+ ", age=" + (age == null ? 0 : age) + ", single=" + single + ", birthday='" + birthday + '\''
				+ ", weight=" + weight + ", height=" + height + '}';
	}
}
