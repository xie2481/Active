package com.active.action;

import java.text.ParseException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.active.entity.Document;
import com.active.entity.Exam;
import com.active.entity.ExamQ;
import com.active.entity.Examsheet;
import com.active.entity.OJProblem;
import com.active.entity.Question;
import com.active.entity.Video;
import com.active.service.AdminService;
import com.active.util.UtilTool;

import jxl.Workbook;


@SuppressWarnings("restriction")
@Controller
public class AdminController {

	@Resource
	private AdminService as;

	@ResponseBody
	@RequestMapping("/getTeacherByTnoTpwd")
	public String getTeacherByTnoTpwd(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "tNo", required = true) String tNo,
			@RequestParam(value = "tPwd", required = true) String tPwd) {
		return as.getTeacherByTnoTpwd(callback, tNo, tPwd);
	}

	@ResponseBody
	@RequestMapping("/getAllChapter")
	public String getAllChapter(
			@RequestParam(value = "json_callback", required = false) String callback) {
		return as.getAllChapter(callback);
	}

	@ResponseBody
	@RequestMapping("/getVideosByTno")
	public String getVideosByTno(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "tNo", required = true) String tNo) {
		return as.getVideosByTno(callback, tNo);
	}

	@ResponseBody
	@RequestMapping("/getFlashsByTno")
	public String getFlashsByTno(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "tNo", required = true) String tNo) {
		return as.getFlashsByTno(callback, tNo);
	}

	@ResponseBody
	@RequestMapping("/addVideo")
	public void addVideo(HttpServletRequest request) {
		Video video = (Video) request.getAttribute("video");
		as.addVideo(video);
	}

	@ResponseBody
	@RequestMapping("/delVideo")
	public void delVideo(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "vNo", required = true) int vNo) {
		as.delVideo(vNo);
	}

	@ResponseBody
	@RequestMapping("/addQuestion")
	public String addQuestion(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "qTitle", required = true) String qTitle,
			@RequestParam(value = "qknowId",required = true) int qknowId,
			@RequestParam(value = "qType", required = true) int qType,
			@RequestParam(value = "chaNo", required = true) int chaNo,
			@RequestParam(value = "qDifficulty", required = true) int qDifficulty,
			@RequestParam(value = "qAnswer", required = true) String qAnswer) {
		Question q = new Question();
		q.setqTitle(qTitle);
		q.setqType(qType);
		q.setChaNo(chaNo);
		q.setqDifficulty(qDifficulty);
		q.setqAnswer(qAnswer);
		q.setqKnowledge(qknowId);
		return String.valueOf(as.addQuestion(q));
	}

	@ResponseBody
	@RequestMapping("/addOptions")
	public void addOptions(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "qNo", required = true) int qNo,
			HttpServletRequest request) {
		String[] options = request.getParameterValues("options[]");
		as.addOptions(qNo, options);
	}

	@ResponseBody
	@RequestMapping("/getQuestions")
	public String getQuestions(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "qType", required = false) String qType,
			@RequestParam(value = "chaNo", required = false) String chaNo,
			@RequestParam(value = "qDifficulty", required = false) String qDifficulty) {
		qType = qType.equals("-1") ? null : qType;
		chaNo = chaNo.equals("-1") ? null : chaNo;
		qDifficulty = qDifficulty.equals("-1") ? null : qDifficulty;
		return as.getQuestions(callback, qType, qDifficulty, chaNo);
	}

	@ResponseBody
	@RequestMapping("/delQuestion")
	public void delQuestion(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "qNo", required = true) int qNo) {
		as.delQuestion(qNo);
	}

	@ResponseBody
	@RequestMapping("/getDocsByTno")
	public String getDocsByTno(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "tNo", required = true) String tNo) {
		return as.getDocsByTno(callback, tNo);
	}

	@ResponseBody
	@RequestMapping("/addDoc")
	public void addDoc(HttpServletRequest request) {
		Document doc = (Document) request.getAttribute("doc");
		as.addDoc(doc);
	}

	@ResponseBody
	@RequestMapping("/getTrainings")
	public String getTrainings(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "trainType", required = false) String trainType,
			@RequestParam(value = "chaNo", required = false) String chaNo) {
		trainType = trainType.equals("-1") ? null : trainType;
		chaNo = chaNo.equals("-1") ? null : chaNo;
		return as.getTrainings(callback, trainType, chaNo);
	}

	@ResponseBody
	@RequestMapping("/getExams")
	public String getExams(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "tNo", required = false) String tNo) {
		return as.getExams(callback, tNo);
	}

	@ResponseBody
	@RequestMapping("/getExamsNeedCorrect")
	public String getExamsNeedCorrect(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "tNo", required = false) String tNo) {
		return as.getExamsNeedCorrect(callback, tNo);
	}

	@ResponseBody
	@RequestMapping("/getStuDoExamNum")
	public String getStuDoExamNum(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "eNo", required = false) String eNo) {
		return as.getStuDoExamNum(callback, eNo);
	}

	@ResponseBody
	@RequestMapping("/getStuNum")
	public String getStuDoExamNum(
			@RequestParam(value = "json_callback", required = false) String callback) {
		return as.getStuNum(callback);
	}

	@ResponseBody
	@RequestMapping("/getToCorrectNum")
	public String getToCorrectNum(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "eNo", required = false) String eNo) {
		return as.getToCorrectNum(callback, eNo);
	}

	@ResponseBody
	@RequestMapping("/getFirstSno")
	public String getFirstSno(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "eNo", required = false) String eNo) {
		return as.getFirstSno(callback, eNo);
	}

	@ResponseBody
	@RequestMapping("/addExam")
	public void addExam(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "eNo", required = true) String eNo,
			@RequestParam(value = "eName", required = true) String eName,
			@RequestParam(value = "tNo", required = true) String tNo,
			@RequestParam(value = "eBeginTime", required = true) String eBeginTime,
			@RequestParam(value = "eEndTime", required = true) String eEndTime,
			@RequestParam(value = "eDuration", required = true) int eDuration,
			@RequestParam(value = "eMark", required = true) int eMark)
			throws ParseException {
		Exam exam = new Exam();
		UtilTool ut = new UtilTool();
		exam.seteNo(eNo);
		exam.seteName(eName);
		exam.settNo(tNo);
		exam.setClaNo("666");
		exam.seteBeginTime(ut.stringToTimestamp(eBeginTime.replace("T", " ")));
		exam.seteEndTime(ut.stringToTimestamp(eEndTime.replace("T", " ")));
		exam.seteDuration(eDuration);
		exam.seteMark(eMark);
		as.addExam(exam);
	}

	@ResponseBody
	@RequestMapping("/addExamQ")
	public void addExamQ(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "eNo", required = true) String eNo,
			@RequestParam(value = "qNo", required = true) int qNo,
			@RequestParam(value = "qMark", required = true) int qMark) {
		ExamQ examQ = new ExamQ();
		examQ.seteNo(eNo);
		examQ.setqNo(qNo);
		examQ.setqMark(qMark);
		as.addExamQ(examQ);
	}

	@ResponseBody
	@RequestMapping("/delExam")
	public void delExam(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "eNo", required = true) String eNo) {
		as.delExam(eNo);
	}

	@ResponseBody
	@RequestMapping("/getTeacherByTno")
	public String getTeacherByTno(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "tNo", required = true) String tNo) {
		return as.getTeacherByTno(callback, tNo);
	}

	@ResponseBody
	@RequestMapping("/correctExam")
	public void correctExam(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "sNo", required = true) String sNo,
			@RequestParam(value = "eNo", required = true) String eNo,
			@RequestParam(value = "qNo", required = true) int qNo,
			@RequestParam(value = "tsIsRight", required = true) int tsIsRight) {
		Examsheet examsheet = new Examsheet();
		examsheet.setsNo(sNo);
		examsheet.seteNo(eNo);
		examsheet.setqNo(qNo);
		examsheet.setTsIsRight(tsIsRight);
		as.correctExam(examsheet);
	}

	@ResponseBody
	@RequestMapping("/getExamStudents")
	public String getExamStudents(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "eNo", required = true) String eNo) {
		return as.getExamStudents(callback, eNo);
	}

	@ResponseBody
	@RequestMapping("/getStudents")
	public String getStudents(
			@RequestParam(value = "json_callback", required = false) String callback) {
		return as.getStudents(callback);
	}

	@ResponseBody
	@RequestMapping("/testImportant")
	public void testImportant(HttpServletRequest request) {
		Video video = (Video) request.getAttribute("video");
		System.out.println(video.getVno());
	}

	@ResponseBody
	@RequestMapping("/getInfoList")
	public String getInfoList(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "etype",required = true) int etype) {
			return as.getInfoList(callback, etype);
	}
	
	@ResponseBody
	@RequestMapping("/addNewPreview")
	public void addNewPreview(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "json",required = true) String json,
			@RequestParam(value = "json_knowledge",required = true) String json_knowledge,
			@RequestParam(value = "tno",required = true) String tno,
			@RequestParam(value = "claNo",required = true) String claNo) {
		as.addNewPreview(callback, json,json_knowledge, tno, claNo);
	}
	
	@ResponseBody
	@RequestMapping("/getKnowledgeBychaNo")
	public String getKnowledgeBychaNo(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "chaNo",required = true) int chaNo) {
		return as.getKnowledgeBychaNo(callback, chaNo);
	}
	
	@ResponseBody
	@RequestMapping("/addOJ")
	public void addOJ(HttpServletRequest request) {
		OJProblem oj = (OJProblem) request.getAttribute("oj");
		as.addOjProblem(oj);
	}
	
	@ResponseBody
	@RequestMapping("getOJs")
	public String getOJs(
			@RequestParam(value = "json_callback", required = false) String callback,
			@RequestParam(value = "chaNo", required = true) String chaNo,
			@RequestParam(value = "qDifficulty", required = true) String qDifficulty) {
		chaNo = chaNo.equals("-1") ? null : chaNo;
		qDifficulty = qDifficulty.equals("-1") ? null : qDifficulty;
		return as.getOJs(callback, chaNo, qDifficulty);
	}
	
	@ResponseBody
	@RequestMapping("/delOJQuestion")
	public void delOJQuestion(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "trainNo",required = true) int trainNo) {
		as.delOJQuestion(callback,trainNo);
	}
	
	@ResponseBody
	@RequestMapping("/getStudentByclaNo")
	public String getStudentByclaNo(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "claNo",required = true) String claNo) {
		return as.getStudentByclaNo(callback, claNo);
	}
	
	@ResponseBody
	@RequestMapping("/deleteDcoBydocNo")
	public void deleteDcoBydocNo(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "docNo",required = true) String docNo) {
		as.deleteDcoBydocNo(callback, docNo);
	}
	
	@ResponseBody
	@RequestMapping("/getClassByTno")
	public String getClassByTno(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "tno",required = true) String tno) {
		return as.getClassByTno(callback, tno);
	}
	
	@ResponseBody
	@RequestMapping("/insertClass")
	public void insertClass(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "tno",required = true) String tno,
			@RequestParam(value = "name",required = true) String name) {
		as.insertClass(callback, tno, name);
	}
	
	@ResponseBody
	@RequestMapping("/addStudentInfo")
	public void addStudentInfo(HttpServletRequest request) {
		Workbook workbook = (Workbook) request.getAttribute("workbook");
		as.addStudentInfo(workbook);
	}
	
	@ResponseBody
	@RequestMapping("/getAllStudent")
	public String getAllStudent(
			@RequestParam(value = "json_callback",required = false) String callback) {
		return as.getAllStudent(callback);
	}
	
	@ResponseBody
	@RequestMapping("/outputAllData")
	public void outputAllData(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "chaNo",required = true) int chaNo) {
		as.outputAllData(callback,chaNo);
	}
	
	@ResponseBody
	@RequestMapping("/outputPersonData")
	public void outputPersonData(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "sno",required = true) String sno,
			@RequestParam(value = "chaNo",required = true) int chaNo) {
		as.outputPersonData(callback,sno,chaNo);
	}
	
	@ResponseBody
	@RequestMapping("/addFirstChapter")
	public void addFirstChapter(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "chpName",required = true) String chpName) {
		as.addFirstChapter(callback, chpName);
	}
	
	@ResponseBody
	@RequestMapping("/addSecondChapter")
	public void addSecondChapter(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "chaNoLast",required = true) int chaNoLast,
			@RequestParam(value = "chpName",required = true) String chpName) {
		as.addNotFirstChapter(callback, chaNoLast, chpName);
	}
	
	@ResponseBody
	@RequestMapping("/addThirdChapter")
	public void addThirdChapter(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "chaNoLast",required = true) int chaNoLast,
			@RequestParam(value = "chpName",required = true) String chpName) {
		as.addNotFirstChapter(callback, chaNoLast, chpName);
	}
	
	@ResponseBody
	@RequestMapping("/addKnowledge")
	public void addKnowledge(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "knowledge",required = true) String knowledge,
			@RequestParam(value = "chpNo",required = true) int chpNo) {
		as.addKnowledge(callback, knowledge, chpNo);
	}
	
	@ResponseBody
	@RequestMapping("/getUsualScoreBySno")
	public int getUsualScoreBySno(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "sno",required = true) String sno) {
		return as.getUsualScoreBySno(callback, sno);
	}
	
	@ResponseBody
	@RequestMapping("/outputStudentData")
	public void outputStudentData(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "students",required = true) String students,
			@RequestParam(value = "scores",required = true) String scores) {
		as.outputStudentData(callback, students, scores);
	}
	
	@ResponseBody
	@RequestMapping("/getKnowledgeName")
	public String getKnowledgeName(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "id",required = true) int id) {
		return as.getKnowledgeName(callback, id);
	} 
	
	@ResponseBody
	@RequestMapping("/getTeacherOnlyByTno")
	public String getTeacherOnlyByTno(
			@RequestParam(value = "json_callback",required = false) String callback,
			@RequestParam(value = "tNo",required = true) String tNo) {
		return as.getTeacherOnlyByTno(callback, tNo);
	}
} 
