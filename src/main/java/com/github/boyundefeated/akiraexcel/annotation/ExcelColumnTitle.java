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
public @interface ExcelColumnTitle {

    /**
     * Specifies the column title where the corresponding value is mapped from the excel data
     *
     * @return column title
     */
    String value();
}
