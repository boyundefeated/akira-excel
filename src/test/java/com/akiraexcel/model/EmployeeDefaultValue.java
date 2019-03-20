package com.akiraexcel.model;

import java.util.Date;

import com.github.boyundefeated.akiraexcel.annotation.ExcelColumnDefaultValue;
import com.github.boyundefeated.akiraexcel.annotation.ExcelColumnTitle;

public class EmployeeDefaultValue extends Person {

//    @ExcelColumnIndex(0
//	@ExcelColumnNotBlank
	@ExcelColumnDefaultValue("200")
	@ExcelColumnTitle("ID")
	protected Long employeeId;

//    @ExcelColumnIndex(1)
	@ExcelColumnTitle("NAME")
	protected String name = "";

//    @ExcelColumnIndex(2)
	@ExcelColumnTitle("SURNAME")
	protected String surname;

//    @ExcelColumnIndex(3)
	@ExcelColumnTitle("AGE")
	protected Integer age;

//    @ExcelColumnIndex(4)
	@ExcelColumnTitle("SINGLE")
	protected boolean single;

//    @ExcelColumnIndex(5)
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
