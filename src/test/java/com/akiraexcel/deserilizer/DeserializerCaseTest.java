package com.akiraexcel.deserilizer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.akiraexcel.model.Employee;
import com.github.boyundefeated.akiraexcel.bind.AkiraExcel;
import com.github.boyundefeated.akiraexcel.utils.AkiraExcelType;
import com.github.boyundefeated.akiraexcel.utils.FileHelper;

@RunWith(Parameterized.class)
public class DeserializerCaseTest {

    private String path;

    public DeserializerCaseTest(String path) {
        this.path = path;
    }

    @Parameterized.Parameters(name = "{index}: ({0})={1}")
    public static Iterable<Object[]> queries() {
        return Arrays.asList(new Object[][]{
                {"src/test/resources/employees.xlsx"},
                {"src/test/resources/employees.xls"},
        });
    }

    @Test
    public void shouldMapExcelToJava() {
        List<Employee> employees = AkiraExcel.fromExcel(new File(path), Employee.class);
		assertThat(employees, notNullValue());
		assertThat(employees.size(), not(0));
		assertThat(employees.size(), is(3));
    }
    
    @Test
    public void shouldMapExcelToInheritObjectJava() {
        try (InputStream stream = new FileInputStream(new File(path))) {
        	AkiraExcelType excelType = FileHelper.getType(path);
            List<Employee> employees = AkiraExcel.fromExcel(stream, excelType, Employee.class);
            assertThat(employees.size(), is(3));
            assertThat(employees.get(0).getWeight(), notNullValue());
            assertThat(employees.get(0).getHeight(), notNullValue());
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }
}
