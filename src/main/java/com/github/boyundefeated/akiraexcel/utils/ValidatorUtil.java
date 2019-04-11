package com.github.boyundefeated.akiraexcel.utils;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidatorUtil {
	private static Validator validator;

	private ValidatorUtil() {
	}

	public static Validator getInstance() {
		if (validator == null) {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			validator = factory.getValidator();
		}
		return validator;
	}
}
