package com.github.boyundefeated.akiraexcel.config;

import com.github.boyundefeated.akiraexcel.utils.AkiraExcelOptions;

/**
 * A casting interface to build a custom AkiraExcel configuration.
 *
 */
public interface Casting {
    Object castValue(Class<?> fieldType, String value, AkiraExcelOptions options);
}
