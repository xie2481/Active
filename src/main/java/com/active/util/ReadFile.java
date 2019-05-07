package com.active.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class ReadFile {

	public static String readMDFile(String title) {
		return readFile(getMDPath(title));
	}
	
	public static String readInterface(String title,String codeType) {
		return readFile(getInterfacePath(title,codeType));
	}
	@SuppressWarnings("resource")
	public static String readFile(String path) {
		File file = new  File(path);
		try {
			FileInputStream in = new FileInputStream(file);
			BufferedReader reader;
			String line = "";
			String res = "";
			try {
				reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
				try {
					while((line = reader.readLine()) != null) {
						res += line;
						res += '\n';//¼ÓÉÏ»»ÐÐ·û
					}
					return res;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	private static String getMDPath(String title) {
		return CurrentPath.getCurrentPath() 
				+ "/res/Problem/" + title + "/" + title + ".md";
	}
	
	private static String getInterfacePath(String title,String codeType) {
		return CurrentPath.getCurrentPath()
				+ "/res/Problem/" + title + "/" + codeType + "/interface.java";
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(ReadMDFile.readMDFile(1));
	}

}
