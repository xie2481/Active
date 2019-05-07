package com.active.util;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UtilTool {

	/**
	 * 结果集转化成集合
	 */
	public List<Object> ResultSetToList(ResultSet rs) throws SQLException {
		if (rs == null)
			return Collections.emptyList();
		ResultSetMetaData md = rs.getMetaData(); // 得到结果集(rs)的结构信息，比如字段数、字段名等
		int columnCount = md.getColumnCount(); // 返回此 ResultSet 对象中的列数
		List<Object> list = new ArrayList<Object>();
		Map<Object, Object> rowData = new HashMap<Object, Object>();
		while (rs.next()) {
			rowData = new HashMap<Object, Object>(columnCount);
			for (int i = 1; i <= columnCount; i++) {
				rowData.put(md.getColumnName(i), rs.getObject(i));
			}
			list.add(rowData);
		}
		return list;
	}

	/**
	 * list转化为json
	 */
	public String ListToJson(List<?> list) {
		JSONObject jo = new JSONObject();
		JSONArray ja = JSONArray.fromObject(list);
		jo.put("data", ja);
		return jo.toString();
	}

	/**
	 * list转化为json，处理跨域
	 */
	public String ListToJson(List<?> list, String callback) {
		String s = ListToJson(list);
		System.out.println(callback);
		if (callback != null) {
			s = callback + "(" + s + ")";
		}
		return s;
	}

	/**
	 * map转化为json
	 */
	public String MapToJson(Map<?, ?> map) {
		JSONObject jo = new JSONObject();
		JSONArray ja = JSONArray.fromObject(map);
		jo.put("data", ja);
		return jo.toString();
	}

	/**
	 * 中文乱码
	 */
	public String encodeStr(String str) {
		try {
			return new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * method 将字符串类型的日期转换为一个timestamp（时间戳记java.sql.Timestamp） dateString
	 * 需要转换为timestamp的字符串 dataTime timestamp
	 */
	public Timestamp stringToTimestamp(String dateString) throws java.text.ParseException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm", Locale.ENGLISH);
		dateFormat.setLenient(false);
		java.util.Date timeDate = dateFormat.parse(dateString);
		java.sql.Timestamp dateTime = new java.sql.Timestamp(timeDate.getTime());
		return dateTime;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,String> jsonToMap(String json){
		return (Map)JSON.parse(json);
	}
}
