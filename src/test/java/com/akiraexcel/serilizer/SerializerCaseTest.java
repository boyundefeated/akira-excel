package com.akiraexcel.serilizer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.akiraexcel.model.Person;
import com.github.boyundefeated.akiraexcel.model.SheetDataModel;
import org.apache.poi.ss.usermodel.Workbook;
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
//		File file = new File(FILENAME);
//		if (file.exists()) {
//			file.delete();
//		}
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

	@Test
	public void shouldMapJavaToExcelWith10kRowsData() throws IOException {
		List<Employee> e3 = Data.fakeEmployes(); // just 3 rows
		List<Employee> employees = new ArrayList<>();
		for (int i = 0; i <= 10000/3; i++) {
			employees.addAll(e3);
		}
		System.out.println("Size of employees = " + employees.size());
		FileOutputStream fileOut = new FileOutputStream(FILENAME);
		long startTime = System.currentTimeMillis();
		Workbook wb = AkiraExcel.toExcel(employees, new AkiraWriterFormatOptions.Builder().build());
		// write this workbook to an output stream.
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
		long endTime = System.currentTimeMillis();
		System.out.println("Take time: " + (endTime - startTime) + "ms");
		assertNotNull(wb);
	}

	@Test
	public void shouldMapJavaToExcelWith50kRowsData() throws IOException {
		List<Employee> e3 = Data.fakeEmployes(); // just 3 rows
		List<Employee> employees = new ArrayList<>();
		for (int i = 0; i <= 100000/3; i++) {
			employees.addAll(e3);
		}
		System.out.println("Size of employees = " + employees.size());
		FileOutputStream fileOut = new FileOutputStream(FILENAME);
		long startTime = System.currentTimeMillis();
		Workbook wb = AkiraExcel.toExcel(employees, new AkiraWriterFormatOptions.Builder().build());
		// write this workbook to an output stream.
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
		long endTime = System.currentTimeMillis();
		System.out.println("Take time: " + (endTime - startTime) + "ms");
		assertNotNull(wb);
	}

	@Test
	public void shouldMapJavaToExcelWithMultipleSheet() throws IOException {
		List<Employee> employees = Data.fakeEmployes();
		List<Person> personList = Data.fakePerson();

		FileOutputStream fileOut = new FileOutputStream(FILENAME);

		SheetDataModel sheetDataModel = new SheetDataModel("quangbs_test", Employee.class, employees, new AkiraWriterFormatOptions.Builder().build());
		SheetDataModel sheetDataModelPerson = new SheetDataModel("Person", Person.class, personList, new AkiraWriterFormatOptions.Builder().build());

		List<SheetDataModel> sheetDataModels = new ArrayList<>();
		sheetDataModels.add(sheetDataModel);
		sheetDataModels.add(sheetDataModelPerson);

		Workbook wb = AkiraExcel.toExcelMultipleSheet(sheetDataModels);
		// write this workbook to an Outputstream.
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();

		assertNotNull(wb);
	}
}
