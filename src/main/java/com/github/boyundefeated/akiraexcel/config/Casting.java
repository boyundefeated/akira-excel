package com.github.boyundefeated.akiraexcel.config;

import java.lang.reflect.Field;

import com.github.boyundefeated.akiraexcel.utils.AkiraExcelOptions;

/**
 * A casting interface to build a custom AkiraExcel configuration.
 *
 */
public interface Casting {
    Object castValue(Field field, String value, AkiraExcelOptions options);
}
