package com.active.util;

import java.util.Date;
import java.util.Calendar;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

class Test {
	/**
	 * method ���ַ������͵�����ת��Ϊһ��timestamp��ʱ�����java.sql.Timestamp�� dateString
	 * ��Ҫת��Ϊtimestamp���ַ��� dataTime timestamp
	 */
	public final static java.sql.Timestamp string2Time(String dateString)
			throws java.text.ParseException {
		DateFormat dateFormat;
		// dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss.SSS",
		// Locale.ENGLISH);// �趨��ʽ
		dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm", Locale.ENGLISH);// �趨��ʽ
		// dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss",
		// Locale.ENGLISH);
		dateFormat.setLenient(false);
		java.util.Date timeDate = dateFormat.parse(dateString);// util����
		java.sql.Timestamp dateTime = new java.sql.Timestamp(timeDate.getTime());// Timestamp����,timeDate.getTime()����һ��long��
		return dateTime;
	}

	/**
	 * method ���ַ������͵�����ת��Ϊһ��Date��java.sql.Date�� dateString ��Ҫת��ΪDate���ַ���
	 * dataTime Date
	 */
	public final static java.sql.Date string2Date(String dateString)
			throws java.lang.Exception {
		DateFormat dateFormat;
		dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		dateFormat.setLenient(false);
		java.util.Date timeDate = dateFormat.parse(dateString);// util����
		java.sql.Date dateTime = new java.sql.Date(timeDate.getTime());// sql����
		return dateTime;
	}

	public static void main(String[] args) {
		int knowledgeIndex = 0;
		System.out.println("<select name = 'chano1-" + knowledgeIndex + "' class = 'chapter1'" +
				 " onclick = \"getNext(this.value,\'new-know-" + knowledgeIndex +"\',2);\"></select>");
	}
}