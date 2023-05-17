package com.akiraexcel.deserilizer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.akiraexcel.model.EmployeeWithValidation;
import com.github.boyundefeated.akiraexcel.bind.AkiraExcel;
import com.github.boyundefeated.akiraexcel.exception.ExcelRowReaderErrorException;
import com.github.boyundefeated.akiraexcel.model.RowFail;
import com.github.boyundefeated.akiraexcel.utils.AkiraExcelOptions;

@RunWith(Parameterized.class)
public class DeserializerWithValidator {

	private String path;

	public DeserializerWithValidator(String path) {
		this.path = path;
	}

	@Parameterized.Parameters(name = "{index}: ({0})={1}")
	public static Iterable<Object[]> queries() {
		return Arrays.asList(new Object[][] { { "src/test/resources/employees_with_validator.xlsx" } });
	}

	@Test
	public void deserializerWithValidatiorTest() {
		// @formatter:off
		AkiraExcelOptions options = new AkiraExcelOptions.Builder()
				.hasHeaderRow(true)
				.headerRow(3)
				.usingObjectValidator(true)
				.build();
		try {
			AkiraExcel.fromExcel(new File(path), EmployeeWithValidation.class, options);
		}catch (ExcelRowReaderErrorException e) {
			List<RowFail>  lsRowFail = e.getListFail();
			assertThat(lsRowFail.size(), is(1));
			assertThat(lsRowFail.get(0).getRowNo(), is(5));
			assertThat(lsRowFail.get(0).getListFailDetail().get(0).getCellAddress(), is("B5"));
			assertThat(lsRowFail.get(0).getListFailDetail().get(0).getFieldName(), is("name"));
			assertThat(lsRowFail.get(0).getListFailDetail().get(0).getMessage(), is("must not be blank"));
			assertThat(lsRowFail.get(0).getListFailDetail().get(0).getInvalidValue(), is("null"));
		}			
		// @formatter:on

	}

}
