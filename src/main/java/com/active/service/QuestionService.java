package com.active.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.active.dao.QuestionDao;
import com.active.entity.ClassFeedback;
import com.active.entity.Exam;
import com.active.entity.ExamQ;
import com.active.entity.Examresult;
import com.active.entity.Examsheet;
import com.active.entity.Examtime;
import com.active.entity.FeedbackExamResult;
import com.active.entity.OJProblem;
import com.active.entity.Question;
import com.active.entity.Testsheet;
import com.active.entity.WrongQuestion;
import com.active.util.Compile;
import com.active.util.CompileJava;
import com.active.util.ReadFile;
import com.active.util.UtilTool;

/**
 * @author: 耿飞
 */
@Service
public class QuestionService {

	@Resource
	private QuestionDao qd;
	private UtilTool ut = new UtilTool();

	// 练习
	/**
	 * 获取练习表
	 * 
	 * @param callback
	 * @return
	 */
	public String getTrainingList(String callback) {
		List<?> list = qd.getTrainingList();
		return ut.ListToJson(list, callback);
	}

	public String getTrainingListByTime(String callback) {
		List<?> list = qd.getTrainingListByTime();
		return ut.ListToJson(list, callback);
	}

	public String getTrainingListByChaNo(String callback, int chaNo) {
		List<?> list = qd.getHomeWorkListByChaNo(chaNo);
		return ut.ListToJson(list, callback);
	}

	public String getHomeWorkList(String callback) {
		List<?> list = qd.getHomeWorkList();
		return ut.ListToJson(list, callback);
	}

	public String getHomeWorkListByTime(String callback) {
		List<?> list = qd.getHomeWorkListByTime();
		return ut.ListToJson(list, callback);
	}

	public String getHomeWorkListByChaNo(String callback, int chaNo) {
		List<?> list = qd.getHomeWorkListByChaNo(chaNo);
		return ut.ListToJson(list, callback);
	}

	public String getTrainingAndHomeWorkListByChaNo(String callback, int chaNo) {
		List<?> list = qd.getTrainingAndHomeWorkListByChaNo(chaNo);
		return ut.ListToJson(list, callback);
	}

	public String getTrainingListByTrainNo(String callback, int trainNo) {
		List<?> list = qd.getTrainingListByTrainNo(trainNo);
		return ut.ListToJson(list, callback);
	}

	public String getQuestionListByTrainNo(String callback, int trainNo) {
		List<?> list = qd.getQuestionListByTrainNo(trainNo);
		return ut.ListToJson(list, callback);
	}

	public String getQuestionListByTrainNoQType(String callback, int trainNo,
			int qType) {
		List<?> list = qd.getQuestionListByTrainNoQType(trainNo, qType);
		return ut.ListToJson(list, callback);
	}

	public String getQuestionByQNo(String callback, int qNo) {
		List<?> list = qd.getQuestionByQNo(qNo);
		return ut.ListToJson(list, callback);
	}

	public String getQuestionByQNo2(String callback, int qNo) {
		List<Question> list = qd.getQuestionByQNo(qNo);
		list.get(0).setqAnswer("");
		return ut.ListToJson(list, callback);
	}

	public String getOptionListByQNo(String callback, int qNo) {
		List<?> list = qd.getOptionListByQNo(qNo);
		return ut.ListToJson(list, callback);
	}

	public String getTestsheetChcheList(String callback, Testsheet ts) {
		List<?> list = qd.getTestsheetChcheList(ts);
		return ut.ListToJson(list, callback);
	}

	public String getTestsheetChche(String callback, Testsheet ts) {
		List<?> list = qd.getTestsheetChche(ts);
		return ut.ListToJson(list, callback);
	}

	public String getTestsheetList(String callback, Testsheet ts) {
		List<?> list = qd.getTestsheetList(ts);
		return ut.ListToJson(list, callback);
	}

	public String getTestsheet(String callback, Testsheet ts) {
		List<?> list = qd.getTestsheet(ts);
		return ut.ListToJson(list, callback);
	}

	public void addTestsheetCache(Testsheet ts) {
		qd.deleteTestsheetCache2(ts);
		qd.addTestsheetCache(ts);
	}

	public void addTestsheet(Testsheet ts) {
		qd.addTestsheet(ts);
		qd.deleteTestsheetCache(ts);
	}

	// 试卷
	public String getExamListByTime(String callback) {
		List<?> list = qd.getExamListByTime();
		return ut.ListToJson(list, callback);
	}

	public String getExamListByENo(String callback, String eNo) {
		List<?> list = qd.getExamListByENo(eNo);
		return ut.ListToJson(list, callback);
	}

	public String getQuestionListByENo(String callback, String eNo) {
		List<?> list = qd.getQuestionListByENo(eNo);
		return ut.ListToJson(list, callback);
	}

	public String getQuestionListByENoQType(String callback, String eNo,
			int qType) {
		List<?> list = qd.getQuestionListByENoQType(eNo, qType);
		return ut.ListToJson(list, callback);
	}

	public String getExamsheetList(String callback, Examsheet es) {
		List<?> list = qd.getExamsheetList(es);
		return ut.ListToJson(list, callback);
	}

	public String getExamsheet(String callback, Examsheet es) {
		List<?> list = qd.getExamsheet(es);
		return ut.ListToJson(list, callback);
	}

	public String getExamsheetCacheList(String callback, Examsheet es) {
		List<?> list = qd.getExamsheetCacheList(es);
		return ut.ListToJson(list, callback);
	}

	public String getExamsheetCache(String callback, Examsheet es) {
		List<?> list = qd.getExamsheetCache(es);
		return ut.ListToJson(list, callback);
	}

	/**
	 * 临时保存题目
	 * 
	 * @param es
	 */
	public void addExamsheetCache(Examsheet es) {
		qd.deleteExamsheetCache2(es);
		Question q = qd.getQuestionByQNo(es.getqNo()).get(0);
		// 是否为没做的题，实际上是在用户点击交卷时执行 addExamsheet() 方法时会使用到
		if (es.getTsIsRight() == 0) {
			qd.addExamsheetCache(es);
			return;
		}
		// 正常情况下用户点击保存
		// 是否为客观题
		if (q.getqType() == 1 || q.getqType() == 2 || q.getqType() == 4) {
			// 是否正确
			if (q.getqAnswer().equals(es.getqAnswer())) {
				List<ExamQ> l2 = qd.getQuestionListByENo(es.geteNo());
				// 查看分值
				for (Iterator<ExamQ> iterator = l2.iterator(); iterator
						.hasNext();) {
					ExamQ examQ = (ExamQ) iterator.next();
					if (examQ.getqNo() == es.getqNo()) {
						// 正确得分
						es.setTsIsRight(examQ.getqMark());
						qd.addExamsheetCache(es);
						return;
					}
				}
			} else {
				// 0分
				es.setTsIsRight(0);
				qd.addExamsheetCache(es);
				qd.addWrongQuestion(es.getqNo());
			}
		} else {
			// 主观题待批改
			es.setTsIsRight(-1);
			qd.addExamsheetCache(es);
		}

	}

	/**
	 * 保存成绩
	 * 
	 * @param es
	 */
	public void addExamresult(Examsheet es) {
		Examresult er = new Examresult();
		er.setsNo(es.getsNo());
		er.seteNo(es.geteNo());
		List<Examsheet> list = qd.getExamsheetList(es);
		int sum = 0;
		for (Examsheet examsheet : list) {
			if (examsheet.getTsIsRight() == -1) {
				sum = -1;
				er.setErMark(sum);
				qd.addExamresult(er);
				return;
			} else {
				sum += examsheet.getTsIsRight();
			}
		}
		er.setErMark(sum);
		qd.addExamresult(er);
	}

	/**
	 * 提交试卷
	 * 
	 * @param es
	 */
	public void addExamsheet(Examsheet es) {
		List<ExamQ> list = qd.getQuestionListByENo(es.geteNo());
		List<Examsheet> list2 = qd.getExamsheetCacheList(es);
		// 检测有没有没做的题目
		for (ExamQ examQ : list) {
			boolean b = true;
			for (Examsheet examsheet : list2) {
				if (examsheet.getqNo() == examQ.getqNo()) {
					b = false;
				}
			}
			// 如果有，插入答卷临时表，0分
			if (b) {
				Examsheet es2 = new Examsheet();
				es2.setsNo(es.getsNo());
				es2.seteNo(es.geteNo());
				es2.setqNo(examQ.getqNo());
				es2.setqAnswer("");
				es2.setTsIsRight(0);
				addExamsheetCache(es2);
			}
		}
		qd.addExamsheet(es);
		addExamresult(es);
		qd.deleteExamsheetCache(es);
	}

	public String getExamresult(String callback, Examresult er) {
		List<?> list = qd.getExamresult(er);
		return ut.ListToJson(list, callback);
	}

	public String getExamresultBySno(String callback, String sNo) {
		List<?> list = qd.getExamresultBySno(sNo);
		return ut.ListToJson(list, callback);
	}

	public void addTestTime(String sNo, String eNo) {
		qd.addTestTime(sNo, eNo);
	}

	public String getTestTime(String callback, String sNo, String eNo) {
		List<?> list = qd.getTestTime(sNo, eNo);
		return ut.ListToJson(list, callback);
	}

	public String getTestTime2(String callback, String sNo, String eNo) {
		List<Examtime> list = qd.getTestTime(sNo, eNo);
		// 开考时间 timestamp
		Timestamp ts = list.get(0).geteTime();
		// 当前时间 timestamp
		Timestamp now = new Timestamp(System.currentTimeMillis());
		// 已用时间 s
		long time = (now.getTime() - ts.getTime()) / 1000;
		// 总时间 min
		List<Exam> exams = qd.getExamListByENo(eNo);
		long total = exams.get(0).geteDuration();
		// 剩余时间 s
		long remain = total * 60 - time;
		return String.valueOf(remain);
	}

	public String getOjProblemListByChaNo(String callback,int chaNo) {
		List<OJProblem> list = qd.getOjProblemListByChaNo(chaNo);
		return ut.ListToJson(list,callback);
	}
	
	public String getOJQuestion(String title) {
		 return ReadFile.readMDFile(title);
	}
	
	public String getOJInterface(String title,String codeType) {
		return ReadFile.readInterface(title,codeType);
	}
	
	public String getOJResult(String title,String answer,String codeType) {
		Compile com = null;
		if(codeType.equals("java")) {
			com = new CompileJava();
		}
		com.storeAnswerCode(title, answer);
		return com.compile(title);
	}
	
	public void addRecordAccepted(int trainNo,String SNo) {
		qd.addRecordAccepted(SNo, trainNo);
	}
	
	public String getClassFeedbackQuestionare(String callback,String claNo,int qType) {
		List<ClassFeedback> list = qd.getClassFeedbackQuestionare(claNo,qType);
		return ut.ListToJson(list,callback);
	}
	
	
	public void updateFeedbackScore(String callback,String jsondata) { 
		Map<String,String> maps = ut.jsonToMap(jsondata);
		for(Map.Entry<String, String> entry : maps.entrySet()) {
			qd.updateFeedbackKnowledgeScore(Integer.parseInt(entry.getKey()), Integer.parseInt(entry.getValue()));
		}
	}
	
	public void addFeedbacksheetCache(String callback,String SNo,int qNo,String answer,int hash) {
		qd.addFeedbacksheetCache(SNo, qNo, answer,hash);
	}
	
	public String getFeedbackCacheList(String callback,String sNo,int hash) {
		List<?> list = qd.getFeedbackCacheList(sNo,hash);
		return ut.ListToJson(list,callback);
	}
	
	public void addFeedbacksheet(String sNo,int hash) {
		List<FeedbackExamResult> list = qd.getFeedbacksheetResult(sNo, hash);
		List<WrongQuestion> questions = qd.getWrongQuestionFromFeedbacksheet(sNo,hash);
		for(FeedbackExamResult res : list) {
			if(res.getRate() >= 0.9) {//回答正确的问题总数占该知识点总数的90%
				qd.updateVeryHigh(sNo,res.getQKnowledge());
				qd.updateKnowVeryHigh(res.getQKnowledge());
			}
			else if(res.getRate() >= 0.7 && res.getRate() <= 0.9) {
				qd.updateHigh(sNo, res.getQKnowledge());
				qd.updateKnowHigh(res.getQKnowledge());
			}
			else if(res.getRate() >= 0.6 && res.getRate() <= 0.7) {
				qd.updateLow(sNo, res.getQKnowledge());
				qd.updateKnowLow(res.getQKnowledge());
			}
			else {
				qd.updateVeryLow(sNo,res.getQKnowledge());
				qd.updateKnowVeryLow(res.getQKnowledge());
			}
		}
	}
	
	public String checkFeedQuestionState(String callback,String sNo,String claNo) {
		if(qd.checkFeedQuestionState(sNo,claNo) != 0)
			return "true";
		else
			return "false";
	}
}
