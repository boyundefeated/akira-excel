package com.github.boyundefeated.akiraexcel.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by duynh5 on 20/03/2019
 * Specifies the default value
 * Not supported primitive type
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface ExcelColumnDefaultValue {
	/**
     * @return cell default value
    */
	String value();
}
