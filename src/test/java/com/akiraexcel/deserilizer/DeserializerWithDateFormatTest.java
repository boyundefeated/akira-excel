package com.akiraexcel.deserilizer;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.akiraexcel.model.EmployeeWithDateFormat;
import com.github.boyundefeated.akiraexcel.bind.AkiraExcel;

@RunWith(Parameterized.class)
public class DeserializerWithDateFormatTest {

	private String path;

	public DeserializerWithDateFormatTest(String path) {
		this.path = path;
	}

	@Parameterized.Parameters(name = "{index}: ({0})={1}")
	public static Iterable<Object[]> queries() {
		return Arrays.asList(new Object[][] { { "src/test/resources/employees_format_date.xlsx" } });
	}

	@Test
	public void shouldMapExcelToJava() {
		List<EmployeeWithDateFormat> employees = AkiraExcel.fromExcel(new File(path), EmployeeWithDateFormat.class);
//		employees.stream().forEach(e -> System.out.println(e.toString()));
		assertThat(employees, notNullValue());
		employees.stream().forEach(e -> assertThat(e.getBirthday(), notNullValue()));
		
	}
}
