package com.active.util;

public class CurrentPath {
	public static String getCurrentPath() {
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		return path.substring(6,path.indexOf("/WEB-INF"));
	}
}
