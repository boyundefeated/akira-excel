package com.github.boyundefeated.akiraexcel.utils;

public class AkiraHelper {
	
	private AkiraHelper() {
	}

	public static String getFileExtension(String fileName) {
        int i = fileName.lastIndexOf('.');
        if (i >= 0) {
            return fileName.substring(i);
        }
        return "";
    }
}
