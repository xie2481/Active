package com.active.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.active.dao.SearchDao;
import com.active.util.UtilTool;


@Service
public class SearchService {

	@Resource
	private SearchDao sd;
	private UtilTool ut = new UtilTool();

	public String searchVideo(String callback, String keyword) {
		List<?> list = sd.searchVideo(keyword);
		return ut.ListToJson(list, callback);
	}

	public String searchDoc(String callback, String keyword) {
		List<?> list = sd.searchDoc(keyword);
		return ut.ListToJson(list, callback);
	}

	public String searchFlash(String callback, String keyword) {
		List<?> list = sd.searchFlash(keyword);
		return ut.ListToJson(list, callback);
	}

	public String searchTraining(String callback, String keyword) {
		List<?> list = sd.searchTraining(keyword);
		return ut.ListToJson(list, callback);
	}

	public String searchExam(String callback, String keyword) {
		List<?> list = sd.searchExam(keyword);
		return ut.ListToJson(list, callback);
	}

}
