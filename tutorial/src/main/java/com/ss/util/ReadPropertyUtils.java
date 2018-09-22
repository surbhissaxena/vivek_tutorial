package com.ss.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertyUtils {
	public static Properties Readproperty(String fileName) {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(fileName+".Properties");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return prop;

	}
	public static void main(String[] args) {
		Readproperty("jdbc");
	}
}
