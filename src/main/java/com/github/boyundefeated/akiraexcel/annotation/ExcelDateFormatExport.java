package com.github.boyundefeated.akiraexcel.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by duynh5 on 27/03/2019
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface ExcelDateFormatExport {

    /**
     * Format for date
     *
     * @return pattern for DateFormat
     */
    String pattern();
    
    String timeZone() default "Asia/Bangkok";
}
