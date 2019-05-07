package com.active.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.active.service.SearchService;

/*
 * @author: ¹¢·É
 */
@Controller
public class SearchController {

	@Resource
	private SearchService ss;

	@ResponseBody
	@RequestMapping("/searchVideo")
	public String searchVideo(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "kw", required = false) String keyword) {
		return ss.searchVideo(callback, keyword);
	}
	
	@ResponseBody
	@RequestMapping("/searchDoc")
	public String searchDoc(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "kw", required = false) String keyword) {
		return ss.searchDoc(callback, keyword);
	}
	
	@ResponseBody
	@RequestMapping("/searchFlash")
	public String searchFlash(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "kw", required = false) String keyword) {
		return ss.searchFlash(callback, keyword);
	}
	
	@ResponseBody
	@RequestMapping("/searchTraining")
	public String searchTraining(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "kw", required = false) String keyword) {
		return ss.searchTraining(callback, keyword);
	}
	
	@ResponseBody
	@RequestMapping("/searchExam")
	public String searchExam(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "kw", required = false) String keyword) {
		return ss.searchExam(callback, keyword);
	}
	
}
