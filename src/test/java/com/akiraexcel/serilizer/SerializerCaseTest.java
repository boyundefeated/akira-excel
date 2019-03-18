package com.akiraexcel.serilizer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.akiraexcel.model.Employee;
import com.akiraexcel.utils.Data;
import com.github.boyundefeated.akiraexcel.bind.AkiraExcel;
import com.github.boyundefeated.akiraexcel.utils.AkiraWriterFormatOptions;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SerializerCaseTest {

	private static final String FILENAME = "output.xlsx";

	@AfterClass
	public static void removeExcelFile() {
		File file = new File(FILENAME);
		if (file.exists()) {
			file.delete();
		}
	}

	@Test
	public void shouldMapJavaToExcel1() throws IOException {
		List<Employee> employees = Data.fakeEmployes();
		FileOutputStream fileOut = new FileOutputStream(FILENAME);
		Workbook wb = AkiraExcel.toExcel(employees, new AkiraWriterFormatOptions.Builder().build());
		// write this workbook to an Outputstream.
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();

		assertNotNull(wb);
	}

	@Test
	public void shouldMapJavaToExcel2() {
		File file = new File(FILENAME);
		assertThat(file.exists(), is(true));
	}

	@Test
	public void shouldMapJavaToExcel3() {
		File file = new File(FILENAME);
		List<Employee> employees = AkiraExcel.fromExcel(file, Employee.class);
		assertThat(employees, notNullValue());
		assertThat(employees.size(), is(3));
		assertThat(employees.get(0).getWeight(), notNullValue());
		assertThat(employees.get(0).getHeight(), notNullValue());
	}
}
