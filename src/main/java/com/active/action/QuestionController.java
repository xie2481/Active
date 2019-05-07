package com.active.action;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.active.entity.Examresult;
import com.active.entity.Examsheet;
import com.active.entity.Testsheet;
import com.active.service.QuestionService;

/**
 * @author: ¹¢·É
 */
@Controller
public class QuestionController {

	@Resource
	private QuestionService qs;

	@ResponseBody
	@RequestMapping("/getTrainingList")
	public String getTrainingList(
			@RequestParam(value = "json_callback", required = false) String callback) {
		return qs.getTrainingList(callback);
	}

	@ResponseBody
	@RequestMapping("/getTrainingListByTime")
	public String getTrainingListByTime(
			@RequestParam(value = "json_callback", required = false) String callback) {
		return qs.getTrainingListByTime(callback);
	}

	@ResponseBody
	@RequestMapping("/getTrainingListByChaNo")
	public String getTrainingListByChaNo(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "chaNo", required = true) int chaNo) {
		return qs.getTrainingListByChaNo(callback, chaNo);
	}

	@ResponseBody
	@RequestMapping("/getHomeWorkList")
	public String getHomeWorkList(
			@RequestParam(value = "json_callback", required = false) String callback) {
		return qs.getHomeWorkList(callback);
	}

	@ResponseBody
	@RequestMapping("/getHomeWorkListByTime")
	public String getHomeWorkListByTime(
			@RequestParam(value = "json_callback", required = false) String callback) {
		return qs.getHomeWorkListByTime(callback);
	}

	@ResponseBody
	@RequestMapping("/getHomeWorkListByChaNo")
	public String getHomeWorkListByChaNo(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "chaNo", required = true) int chaNo) {
		return qs.getHomeWorkListByChaNo(callback, chaNo);
	}

	@ResponseBody
	@RequestMapping("/getTrainingAndHomeWorkListByChaNo")
	public String getTrainingAndHomeWorkListByChaNo(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "chaNo", required = true) int chaNo) {
		return qs.getOjProblemListByChaNo(callback, chaNo);
	}

	@ResponseBody
	@RequestMapping("/getTrainingListByTrainNo")
	public String getTrainingListByTrainNo(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "trainNo", required = true) int trainNo) {
		return qs.getTrainingListByTrainNo(callback, trainNo);
	}

	@ResponseBody
	@RequestMapping("/getQuestionListByTrainNo")
	public String getQuestionListByChaNo(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "trainNo", required = true) int trainNo) {
		return qs.getQuestionListByTrainNo(callback, trainNo);
	}

	@ResponseBody
	@RequestMapping("/getQuestionListByTrainNoQType")
	public String getQuestionListByTrainNoQType(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "trainNo", required = true) int trainNo,
			@RequestParam(value = "qType", required = true) int qType) {
		return qs.getQuestionListByTrainNoQType(callback, trainNo, qType);
	}

	@ResponseBody
	@RequestMapping("/getQuestionByQNo")
	public String getQuestionByQNo(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "qNo", required = true) int qNo) {
		return qs.getQuestionByQNo(callback, qNo);
	}

	@ResponseBody
	@RequestMapping("/getQuestionByQNo2")
	public String getQuestionByQNo2(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "qNo", required = true) int qNo) {
		return qs.getQuestionByQNo2(callback, qNo);
	}

	@ResponseBody
	@RequestMapping("/getOptionListByQNo")
	public String getOptionListByQNo(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "qNo", required = true) int qNo) {
		return qs.getOptionListByQNo(callback, qNo);
	}

	@ResponseBody
	@RequestMapping("/getTestsheetChcheList")
	public String getTestsheetChcheList(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "sNo", required = true) String sNo,
			@RequestParam(value = "trainNo", required = true) int trainNo) {
		Testsheet ts = new Testsheet();
		ts.setsNo(sNo);
		ts.setTrainNo(trainNo);
		return qs.getTestsheetChcheList(callback, ts);
	}

	@ResponseBody
	@RequestMapping("/getTestsheetChche")
	public String getTestsheetChche(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "sNo", required = true) String sNo,
			@RequestParam(value = "qNo", required = true) int qNo,
			@RequestParam(value = "trainNo", required = true) int trainNo) {
		Testsheet ts = new Testsheet();
		ts.setsNo(sNo);
		ts.setqNo(qNo);
		ts.setTrainNo(trainNo);
		return qs.getTestsheetChche(callback, ts);
	}

	@ResponseBody
	@RequestMapping("/getTestsheetList")
	public String getTestsheetList(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "sNo", required = true) String sNo,
			@RequestParam(value = "trainNo", required = true) int trainNo) {
		Testsheet ts = new Testsheet();
		ts.setsNo(sNo);
		ts.setTrainNo(trainNo);
		return qs.getTestsheetList(callback, ts);
	}

	@ResponseBody
	@RequestMapping("/getTestsheet")
	public String getTestsheet(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "sNo", required = true) String sNo,
			@RequestParam(value = "qNo", required = true) int qNo,
			@RequestParam(value = "trainNo", required = true) int trainNo) {
		Testsheet ts = new Testsheet();
		ts.setsNo(sNo);
		ts.setqNo(qNo);
		ts.setTrainNo(trainNo);
		return qs.getTestsheet(callback, ts);
	}

	@ResponseBody
	@RequestMapping("/addTestsheetCache")
	public void addTestsheetCache(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "sNo", required = true) String sNo,
			@RequestParam(value = "qNo", required = true) int qNo,
			@RequestParam(value = "trainNo", required = true) int trainNo,
			@RequestParam(value = "qAnswer", required = true) String qAnswer,
			@RequestParam(value = "tsIsRight", required = true) int tsIsRight) {
		Testsheet ts = new Testsheet(sNo, qNo, trainNo, qAnswer, tsIsRight);
		qs.addTestsheetCache(ts);
	}

	@ResponseBody
	@RequestMapping("/addTestsheet")
	public void addTestsheet(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "sNo", required = true) String sNo,
			@RequestParam(value = "trainNo", required = true) int trainNo) {
		Testsheet ts = new Testsheet();
		ts.setsNo(sNo);
		ts.setTrainNo(trainNo);
		qs.addTestsheet(ts);
	}

	@ResponseBody
	@RequestMapping("/getExamListByTime")
	public String getExamListByTime(
			@RequestParam(value = "json_callback", required = false) String callback) {
		return qs.getExamListByTime(callback);
	}

	@ResponseBody
	@RequestMapping("/getExamListByENo")
	public String getExamListByENo(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "eNo", required = true) String eNo) {
		return qs.getExamListByENo(callback, eNo);
	}

	@ResponseBody
	@RequestMapping("/getQuestionListByENoQType")
	public String getQuestionListByENoQType(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "eNo", required = true) String eNo,
			@RequestParam(value = "qType", required = true) int qType) {
		return qs.getQuestionListByENoQType(callback, eNo, qType);
	}

	@ResponseBody
	@RequestMapping("/getQuestionListByENo")
	public String getQuestionListByENo(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "eNo", required = true) String eNo) {
		return qs.getQuestionListByENo(callback, eNo);
	}

	@ResponseBody
	@RequestMapping("/getExamsheetList")
	public String getExamsheetList(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "sNo", required = true) String sNo,
			@RequestParam(value = "eNo", required = true) String eNo) {
		Examsheet es = new Examsheet();
		es.setsNo(sNo);
		es.seteNo(eNo);
		return qs.getExamsheetList(callback, es);
	}

	@ResponseBody
	@RequestMapping("/getExamsheet")
	public String getExamsheet(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "sNo", required = true) String sNo,
			@RequestParam(value = "qNo", required = true) int qNo,
			@RequestParam(value = "eNo", required = true) String eNo) {
		Examsheet es = new Examsheet();
		es.setsNo(sNo);
		es.setqNo(qNo);
		es.seteNo(eNo);
		return qs.getExamsheet(callback, es);
	}

	@ResponseBody
	@RequestMapping("/getExamsheetCacheList")
	public String getExamsheetCacheList(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "sNo", required = true) String sNo,
			@RequestParam(value = "eNo", required = true) String eNo) {
		Examsheet es = new Examsheet();
		es.setsNo(sNo);
		es.seteNo(eNo);
		return qs.getExamsheetCacheList(callback, es);
	}

	@ResponseBody
	@RequestMapping("/getExamsheetCache")
	public String getExamsheetCache(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "sNo", required = true) String sNo,
			@RequestParam(value = "qNo", required = true) int qNo,
			@RequestParam(value = "eNo", required = true) String eNo) {
		Examsheet es = new Examsheet();
		es.setsNo(sNo);
		es.setqNo(qNo);
		es.seteNo(eNo);
		return qs.getExamsheetCache(callback, es);
	}

	@ResponseBody
	@RequestMapping("/addExamsheetCache")
	public void addExamsheetCache(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "sNo", required = true) String sNo,
			@RequestParam(value = "qNo", required = true) int qNo,
			@RequestParam(value = "eNo", required = true) String eNo,
			@RequestParam(value = "qAnswer", required = true) String qAnswer,
			@RequestParam(value = "tsIsRight", required = true) int tsIsRight) {
		Examsheet es = new Examsheet(eNo, sNo, qNo, qAnswer, tsIsRight);
		qs.addExamsheetCache(es);
	}

	@ResponseBody
	@RequestMapping("/addExamsheet")
	public void addExamsheet(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "sNo", required = true) String sNo,
			@RequestParam(value = "eNo", required = true) String eNo) {
		Examsheet es = new Examsheet();
		es.setsNo(sNo);
		es.seteNo(eNo);
		qs.addExamsheet(es);
	}

	@ResponseBody
	@RequestMapping("/getExamresult")
	public String getExamresult(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "sNo", required = true) String sNo,
			@RequestParam(value = "eNo", required = true) String eNo) {
		Examresult er = new Examresult();
		er.setsNo(sNo);
		er.seteNo(eNo);
		return qs.getExamresult(callback, er);
	}

	@ResponseBody
	@RequestMapping("/addTestTime")
	public void addTestTime(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "sNo", required = true) String sNo,
			@RequestParam(value = "eNo", required = true) String eNo) {
		qs.addTestTime(sNo, eNo);
	}

	@ResponseBody
	@RequestMapping("/getTestTime")
	public String getTestTime(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "sNo", required = true) String sNo,
			@RequestParam(value = "eNo", required = true) String eNo) {
		return qs.getTestTime(callback, sNo, eNo);
	}

	@ResponseBody
	@RequestMapping("/getTestTime2")
	public String getTestTime2(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "sNo", required = true) String sNo,
			@RequestParam(value = "eNo", required = true) String eNo) {
		return qs.getTestTime2(callback, sNo, eNo);
	}

	@ResponseBody
	@RequestMapping("/getExamresultBySno")
	public String getExamresultBySno(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "sNo", required = true) String sNo) {
		return qs.getExamresultBySno(callback, sNo);
	}
	
	@ResponseBody
	@RequestMapping("/getOjProblemListByChaNo")
	public String getOjProblemListByChaNo(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "chaNo",required = true) int chaNo) {
		return qs.getOjProblemListByChaNo(callback, chaNo);
	}
	
	@ResponseBody
	@RequestMapping("/getOJQuestion")
	public String getOJQuestion(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "title",required = true) String title) {
			return qs.getOJQuestion(title);
	}
	
	@ResponseBody
	@RequestMapping("/getOJInterface")
	public String getOJInterface(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "title",required = true) String title,
			@RequestParam(value = "codeType",required = true) String codeType) {
		return qs.getOJInterface(title,codeType);
	}
	
	@ResponseBody
	@RequestMapping("/submitOJAnswer")
	public String submitOJAnswer(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "title",required = true) String title,
			@RequestParam(value = "answer",required = true) String answer,
			@RequestParam(value = "codeType",required = true) String codeType) {
		return qs.getOJResult(title,answer,codeType);
	}
	
	@ResponseBody
	@RequestMapping("/addRecordAccepted")
	public void addRecordAccepted(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "SNo",required = true) String SNo,
			@RequestParam(value = "trainNo",required = true) int trainNo) {
		qs.addRecordAccepted(trainNo, SNo);
	}
	
	@ResponseBody
	@RequestMapping("/getClassFeedbackQuestionare")
	public String getClassFeedbackQuestionare(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "claNo",required = true) String claNo,
			@RequestParam(value = "qType",required = true) int qType) {
		return qs.getClassFeedbackQuestionare(callback, claNo,qType);
	}
	
	@ResponseBody
	@RequestMapping("/updateFeedbackScore")
	public void updateFeedbackScore(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "jsondata",required = true) String jsondata) {
		qs.updateFeedbackScore(callback, jsondata);
	}
	
	@ResponseBody
	@RequestMapping("/addFeedbacksheetCache")
	public void addFeedbacksheetCache(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "sNo",required = true) String sNo,
			@RequestParam(value = "qNo",required = true) int qno,
			@RequestParam(value = "qAnswer",required = true) String qAnswer,
			@RequestParam(value = "hash",required = true) int hash) {
		qs.addFeedbacksheetCache(callback, sNo, qno, qAnswer,hash);
	}
	
	@ResponseBody
	@RequestMapping("/getFeedbackCacheList")
	public String getFeedbackCacheList(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "sNo",required = true) String sNo,
			@RequestParam(value = "hash",required = true) int hash) {
		return qs.getFeedbackCacheList(callback, sNo, hash);
	}
	
	@ResponseBody
	@RequestMapping("/addFeedbacksheet")
	public void addFeedbacksheet(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "sNo",required = true) String sNo,
			@RequestParam(value = "hash",required = true) int hash) {
		qs.addFeedbacksheet(sNo, hash);
	}
	
	@ResponseBody
	@RequestMapping("/checkFeedQuestionState")
	public String checkFeedQuestionState(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "sNo",required = true) String sNo,
			@RequestParam(value = "claNo",required = true) String claNo) {
		return qs.checkFeedQuestionState(callback,sNo,claNo);
	}
}
