package com.akiraexcel.deserilizer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.akiraexcel.model.EmployeeNotBlank;
import com.github.boyundefeated.akiraexcel.bind.AkiraExcel;
import com.github.boyundefeated.akiraexcel.exception.AkiraExcelException;

@RunWith(Parameterized.class)
public class DeserializerDefaultValueTest {

	private String path;

	public DeserializerDefaultValueTest(String path) {
		this.path = path;
	}

	@Parameterized.Parameters(name = "{index}: ({0})={1}")
	public static Iterable<Object[]> queries() {
		return Arrays.asList(new Object[][] { { "src/test/resources/employees_field_blank.xlsx" } });
	}

	@Test(expected = AkiraExcelException.class)
	public void shouldMapExcelToJava() {
		// AkiraExcelException: Field employeeId can not be blank at row 4
		List<EmployeeNotBlank> employees = AkiraExcel.fromExcel(new File(path), EmployeeNotBlank.class);
	}
}
