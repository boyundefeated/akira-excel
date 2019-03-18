package com.github.boyundefeated.akiraexcel.utils;

import java.time.format.DateTimeFormatter;

public final class AkiraExcelConstants {

    public static final String DEFAULT_DATE_PATTERN = "dd/MM/yyyy";
    public static final DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final String XLS_EXTENSION = ".xls";
    public static final String XLSX_EXTENSION = ".xlsx";

    private AkiraExcelConstants() {
    }

}
