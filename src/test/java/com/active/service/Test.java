package com.active.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


public class Test {


	public static void main(String[] args) {
		try {
			Process process = Runtime.getRuntime().exec("cmd.exe /c ipconfig");
			InputStream errorStream = process.getErrorStream();
			InputStream inputStream = process.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
