package com.active.action;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.active.service.ActiveService;


@Controller
public class ActiveController {
	@Resource
	private ActiveService as;

	@RequestMapping("/asd")
	public String getAsd() {
		return "login";
	}

	@RequestMapping("/adm")
	public String getAdm() {
		return "index";
	}

	@SuppressWarnings("static-access")
	@ResponseBody
	@RequestMapping("/setTime")
	public String setTime() {
		try {
			Thread.currentThread().sleep(1500);// ∫¡√Î
		} catch (Exception e) {
		}
		return "yes";
	}

	@ResponseBody
	@RequestMapping("/nullAjax")
	public void nullAjax() {
	}

	@ResponseBody
	@RequestMapping("/getAString")
	public String getAString() {
		return "keyi";
	}

	@ResponseBody
	@RequestMapping("/addFeedback")
	public void addFeedback(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "feedTitle", required = true) String feedTitle,
			@RequestParam(value = "feedContent", required = true) String feedContent,
			@RequestParam(value = "sNo", required = true) String sNo) {
		as.addFeedback(callback, feedTitle, feedContent, sNo);
	}

	@ResponseBody
	@RequestMapping("/loginBySnoSpwd")
	public String loginBySnoSpwd(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "sNo", required = true) String sNo,
			@RequestParam(value = "sPwd", required = true) String sPwd) {
		setTime();
		return as.loginBySnoSpwd(callback, sNo, sPwd);
	}

	@ResponseBody
	@RequestMapping("/getStudentBySno")
	public String getStudentBySno(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "sNo", required = true) String sNo) {
		return as.getStudentBySno(callback, sNo);
	}

	@ResponseBody
	@RequestMapping("/ChangePwd")
	public void ChangePwd(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "sNo", required = true) String sNo,
			@RequestParam(value = "sPwd", required = true) String sPwd) {
		as.ChangePwd(callback, sNo, sPwd);
	}

	@ResponseBody
	@RequestMapping("/getChapterListByChaNoLast")
	public String getChapterListByChaNoLast(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "chaNoLast", required = true) int chaNoLast) {
		return as.getChapterListByChaNoLast(callback, chaNoLast);
	}

	@ResponseBody
	@RequestMapping("/getVideoList")
	public String getVideoList(
			@RequestParam(value = "json_callback", required = false) String callback) {
		return as.getVideoList(callback);
	}

	@ResponseBody
	@RequestMapping("/getVideoListByChaNo")
	public String getVideoListByChaNo(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "chaNo", required = true) int chaNo) {
		return as.getVideoListByChaNo(callback, chaNo);
	}

	@ResponseBody
	@RequestMapping("/getVideoListByVNo")
	public String getVideoListByVNo(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "vNo", required = true) String vNo) {
		return as.getVideoListByVNo(callback, vNo);
	}

	@ResponseBody
	@RequestMapping("/getVideoListByTime")
	public String getVideoListByTime(
			@RequestParam(value = "json_callback", required = false) String callback) {
		return as.getVideoListByTime(callback);
	}

	@ResponseBody
	@RequestMapping("/getVideoListByHot")
	public String getVideoListByHot(
			@RequestParam(value = "json_callback", required = false) String callback) {
		return as.getVideoListByHot(callback);
	}

	@ResponseBody
	@RequestMapping("/updateVideoCount")
	public String updateVideoCount(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "vcount", required = true) int vcount,
			@RequestParam(value = "vno", required = true) String vno) {
		return as.updateVideoCount(callback, vcount, vno);
	}

	@ResponseBody
	@RequestMapping("/getDocList")
	public String getDocList(
			@RequestParam(value = "json_callback", required = false) String callback) {
		return as.getDocList(callback);
	}

	@ResponseBody
	@RequestMapping("/getDocListByTime")
	public String getDocListByTime(
			@RequestParam(value = "json_callback", required = false) String callback) {
		return as.getDocListByTime(callback);
	}

	@ResponseBody
	@RequestMapping("/getDocListByChaNo")
	public String getDocListByChaNo(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "chaNo", required = true) int chaNo) {
		return as.getDocListByChaNo(callback, chaNo);
	}

	@ResponseBody
	@RequestMapping("/getFlashList")
	public String getFlashList(
			@RequestParam(value = "json_callback", required = false) String callback) {
		return as.getFlashList(callback);
	}

	@ResponseBody
	@RequestMapping("/getFlashListByChaNo")
	public String getFlashListByChaNo(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "chaNo", required = true) int chaNo) {
		return as.getFlashListByChaNo(callback, chaNo);
	}

	@ResponseBody
	@RequestMapping("/getFlashListByTime")
	public String getFlashListByTime(
			@RequestParam(value = "json_callback", required = false) String callback) {
		return as.getFlashListByTime(callback);
	}

	@ResponseBody
	@RequestMapping("/getFlashListByHot")
	public String getFlashListByHot(
			@RequestParam(value = "json_callback", required = false) String callback) {
		return as.getFlashListByHot(callback);
	}

	@ResponseBody
	@RequestMapping("/getPreviewRequireBySNo")
	public String getPreviewRequireBySNo(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "SNo",required = true) String SNo,
			@RequestParam(value = "claNo",required = true)String claNo) {
			return as.getPreviewRequireBySNo(callback, SNo,claNo);
	}
	
	@ResponseBody
	@RequestMapping("/getPreviewAskByClaNo")
	public String getPrieviewAskByClaNo(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "ClaNo",required = true) String ClaNo) {
		return as.getPreviewAskByClaNo(callback, ClaNo);
	}
	
	@ResponseBody
	@RequestMapping("/getPreviewQuestionare")
	public String getPreviewQuestionare(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "claNo",required = true)String ClaNo) {
		return as.getPreviewAskByClaNo(callback, ClaNo);
	}
	
	@ResponseBody
	@RequestMapping("/updatePreviewScore")
	public void updatePreviewKnowledgeScore(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "sNo",required = true) String sNo,
			@RequestParam(value = "claNo",required = true) String claNo,
			@RequestParam(value = "jsondata",required = true) String jsondata) {
		as.updatePreviewKnowledgeScore(callback, sNo,claNo,jsondata);
	}
	
	@ResponseBody
	@RequestMapping("/selectOJState")
	public String selectOJState(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "SNo",required = true) String SNo,
			@RequestParam(value = "id",required = true) int id) {
		return as.selectOJState(callback, SNo,id);
	}
	
	@ResponseBody
	@RequestMapping("/checkState")
	public String checkState(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "sNo",required = true) String sNo,
			@RequestParam(value = "claNo",required = true) String claNo) {
		return as.checkState(callback, sNo,claNo);
	}
	
	@ResponseBody
	@RequestMapping("/getPreviewTotolGraph")
	public String getPreviewTotalGraph(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "chaNo",required = true) String chaNo) {
		return as.getPreviewTotolGraph(callback, chaNo);
	}
	@ResponseBody
	@RequestMapping("/getPreviewTotolPersonGraph")
	public String getPreviewTotolPersonGraph(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "chaNo",required = true) String chaNo,
			@RequestParam(value = "sno",required = true) String sno) {
		return as.getPreviewTotolPersonGraph(callback, chaNo, sno);
	}
	
	@ResponseBody
	@RequestMapping("/getClassTotolGraph")
	public String getClassTotalGraph(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "chaNo",required = true) String chaNo) {
		return as.getClassTotolGraph(callback, chaNo);
	}
	
	@ResponseBody
	@RequestMapping("/getClassTotolPersonGraph")
	public String getClassTotolPersonGraph(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "chaNo",required = true) String chaNo,
			@RequestParam(value = "sno",required = true) String sno) {
		return as.getClassTotolPersonGraph(callback, chaNo, sno);
	}
	
	@ResponseBody
	@RequestMapping("/updateStudentPreviewState")
	public void updateStudentPreviewState(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "id",required = true) int id) {
		as.updateStudentPreviewState(callback, id);
	}
	
	@ResponseBody
	@RequestMapping("/addVideoFinish")
	public void addVideoFinish(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "sno",required = true) String sno,
			@RequestParam(value = "vno",required = true) int vno) {
		as.addVideoFinish(callback, sno, vno);
	}
	
	@ResponseBody
	@RequestMapping("/selectVideoState")
	public String selectVideoState(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "sno",required = true) String sno,
			@RequestParam(value = "id",required = true) int id) {
		return as.selectVideoState(callback, sno, id);
	}
	
	@ResponseBody
	@RequestMapping("/getOJById")
	public String getOJById(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "id",required = true) int id) {
		return as.getOJById(callback,id);
	}
}
