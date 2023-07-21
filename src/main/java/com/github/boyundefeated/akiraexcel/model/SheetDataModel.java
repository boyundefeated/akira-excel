package com.github.boyundefeated.akiraexcel.model;

import com.github.boyundefeated.akiraexcel.utils.AkiraWriterFormatOptions;

import java.util.List;

public class SheetDataModel {
    private String sheetName;
    private  Class<?> clazz;
    private List<?> data;
    private AkiraWriterFormatOptions options;

    public SheetDataModel() {
    }

    public SheetDataModel(String sheetName, Class<?> clazz, List<?> data, AkiraWriterFormatOptions options) {
        this.sheetName = sheetName;
        this.clazz = clazz;
        this.data = data;
        this.options = options;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public AkiraWriterFormatOptions getOptions() {
        return options;
    }

    public void setOptions(AkiraWriterFormatOptions options) {
        this.options = options;
    }
}
