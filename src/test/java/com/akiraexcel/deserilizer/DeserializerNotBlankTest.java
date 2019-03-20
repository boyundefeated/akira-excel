package com.akiraexcel.deserilizer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.akiraexcel.model.EmployeeDefaultValue;
import com.github.boyundefeated.akiraexcel.bind.AkiraExcel;

@RunWith(Parameterized.class)
public class DeserializerNotBlankTest {

	private String path;

	public DeserializerNotBlankTest(String path) {
		this.path = path;
	}

	@Parameterized.Parameters(name = "{index}: ({0})={1}")
	public static Iterable<Object[]> queries() {
		return Arrays.asList(new Object[][] { { "src/test/resources/employees_field_blank.xlsx" } });
	}

	@Test
	public void shouldMapExcelToJava() {
		List<EmployeeDefaultValue> employees = AkiraExcel.fromExcel(new File(path), EmployeeDefaultValue.class);
		employees.stream().forEach(e -> System.out.println(e.toString()));
		assertThat(employees, notNullValue());
		assertThat(employees.size(), is(4));
		assertThat(employees.get(3).getEmployeeId(), is(200L));
	}
}
