package com.active.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ParseJson {

	public static JsonObject parseJson(String j) {
		JsonParser parse = new JsonParser();
		JsonElement object = parse.parse(j);
		if(object.isJsonObject())
			return (JsonObject) object;
		else
			return null;
	}
	
	public static JsonArray parseJsonArray(String j) {
		JsonParser parse = new JsonParser();
		JsonElement object = parse.parse(j);
		if(object.isJsonArray())
			return (JsonArray) object;
		else
			return null;
	}
}
