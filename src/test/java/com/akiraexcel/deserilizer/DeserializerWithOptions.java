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

import com.akiraexcel.model.EmployeeWithOption;
import com.akiraexcel.model.EmployeeWithOptionOnlyIndex;
import com.github.boyundefeated.akiraexcel.bind.AkiraExcel;
import com.github.boyundefeated.akiraexcel.utils.AkiraExcelOptions;

@RunWith(Parameterized.class)
public class DeserializerWithOptions {

	private String path;

	public DeserializerWithOptions(String path) {
		this.path = path;
	}

	@Parameterized.Parameters(name = "{index}: ({0})={1}")
	public static Iterable<Object[]> queries() {
		return Arrays.asList(new Object[][] { { "src/test/resources/employees_with_options.xlsx" } });
	}

	@Test
	public void optionHeaderRowAndSkipAfterHeader() {
		// @formatter:off
		AkiraExcelOptions options = new AkiraExcelOptions.Builder()
				.hasHeaderRow(true)
				.headerRow(3)
				.skipRowAfterHeader(1)
				.build();
		List<EmployeeWithOption> employees = AkiraExcel.fromExcel(new File(path), EmployeeWithOption.class, options);
//		employees.stream().forEach(e -> System.out.println(e.toString()));

		assertThat(employees, notNullValue());
		assertThat(employees.size(), is(2));
		assertThat(employees.get(0).getEmployeeId(), is(123123L));
		assertThat(employees.get(0).getWeight(), notNullValue());
		// @formatter:on
	}

	@Test
	public void optionNoHeaderAndDataRowStartAt() {
		// @formatter:off
		AkiraExcelOptions options = new AkiraExcelOptions.Builder()
				.hasHeaderRow(true)
				.dataRowStartAt(4)
				.build();
		List<EmployeeWithOptionOnlyIndex> employees = AkiraExcel.fromExcel(new File(path), EmployeeWithOptionOnlyIndex.class, options);
//		employees.stream().forEach(e -> System.out.println(e.toString()));
		assertThat(employees, notNullValue());
		assertThat(employees.size(), is(3));
		assertThat(employees.get(0).getEmployeeId(), is(123923L));
		// @formatter:on
	}
	
	@Test
	public void testWithSheetIndex() {
		// @formatter:off
		AkiraExcelOptions options = new AkiraExcelOptions.Builder()
				.sheetIndex(1)
				.build();
		List<EmployeeWithOptionOnlyIndex> employees = AkiraExcel.fromExcel(new File(path), EmployeeWithOptionOnlyIndex.class, options);
//		employees.stream().forEach(e -> System.out.println(e.toString()));
		assertThat(employees, notNullValue());
		assertThat(employees.size(), is(3));
		assertThat(employees.get(0).getEmployeeId(), is(1L));
		// @formatter:on
	}
}
