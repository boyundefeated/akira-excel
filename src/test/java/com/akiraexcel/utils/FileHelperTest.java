package com.akiraexcel.utils;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.boyundefeated.akiraexcel.exception.AkiraExcelException;
import com.github.boyundefeated.akiraexcel.utils.AkiraExcelType;
import com.github.boyundefeated.akiraexcel.utils.FileHelper;

public class FileHelperTest {
	@Test
	public void getFileType() {
		assertThat(FileHelper.getType("cars.xls"), is(AkiraExcelType.XLS));
		assertThat(FileHelper.getType("cars.xlsx"), is(AkiraExcelType.XLSX));

	}

	@Test(expected = AkiraExcelException.class)
	public void getFileTypeError() {
		FileHelper.getType("cars.xl");
	}
}
