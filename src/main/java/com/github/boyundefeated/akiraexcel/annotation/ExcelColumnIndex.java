package com.github.boyundefeated.akiraexcel.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by duynh5 on 14/03/2019
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface ExcelColumnIndex {

    /**
     * Specifies the column index where the corresponding value is mapped from the excel data
     * Index starts with 0
     *
     * @return column index
     */
    int value();
}
