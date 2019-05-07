package com.active.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.active.entity.ClassFeedback;
import com.active.entity.Exam;
import com.active.entity.ExamQ;
import com.active.entity.Examresult;
import com.active.entity.Examsheet;
import com.active.entity.Examtime;
import com.active.entity.FeedbackCache;
import com.active.entity.FeedbackExamResult;
import com.active.entity.OJProblem;
import com.active.entity.Option;
import com.active.entity.Question;
import com.active.entity.Testsheet;
import com.active.entity.TrainQ;
import com.active.entity.Training;
import com.active.entity.WrongQuestion;


public interface QuestionDao {
	/**
	 * ��ȡ��ϰ ����
	 */
	String sql = "SELECT training.*, count_question.cou FROM training INNER JOIN count_question on training.trainNo = count_question.trainNo";
	String sql2 = "SELECT exam.*, count_exam.cou FROM exam INNER JOIN count_exam on exam.eNo = count_exam.eNo";

	@Select(sql + " WHERE TrainType = 1")
	List<Training> getTrainingList();

	/**
	 * ��ȡ��ϰ �����ϴ�
	 */
	@Select(sql + " WHERE TrainType = 1 ORDER BY TrainTime DESC LIMIT 10")
	List<Training> getTrainingListByTime();

	/**
	 * ��ȡ��ϰ �����½ں�
	 */
	@Select(sql + " WHERE TrainType = 1 AND ChaNo = #{chaNo}")
	List<Training> getTrainingListByChaNo(int chaNo);

	/**
	 * ��ȡ��ϰ��ҵ ���п���
	 */
	@Select(sql + " WHERE TrainType = 2 AND TrainEndTime > current_timestamp()")
	List<Training> getHomeWorkList();

	/**
	 * ��ȡ��ϰ��ҵ �����ϴ�����
	 */
	@Select(sql
			+ " WHERE TrainType = 2 AND TrainEndTime > current_timestamp() ORDER BY TrainTime DESC LIMIT 10")
	List<Training> getHomeWorkListByTime();

	/**
	 * ��ȡ��ϰ��ҵ �����½ںŲ�����
	 */
	@Select(sql
			+ " WHERE TrainType = 2 AND ChaNo = #{chaNo} AND TrainEndTime < current_timestamp()")
	List<Training> getHomeWorkListByChaNo(int chaNo);

	/**
	 * ��ȡ��ϰ����ҵ �����½ںſ���
	 */
	@Select(sql + " WHERE ChaNo = #{chaNo}")
	List<Training> getTrainingAndHomeWorkListByChaNo(int chaNo);

	/**
	 * ��ȡ��ϰ ������ϰ��
	 */
	@Select("SELECT * FROM training WHERE trainNo = #{trainNo}")
	List<Training> getTrainingListByTrainNo(int trainNo);

	/**
	 * ��ȡ��Ŀ�б� ������ϰ��
	 */
	@Select("SELECT * FROM train_q WHERE trainNo = #{trainNo}")
	List<TrainQ> getQuestionListByTrainNo(int trainNo);

	/**
	 * ��ȡ��Ŀ�б� ������ϰ��,��Ŀ����
	 * 
	 * @param trainNo
	 * @param qType
	 * @return
	 */
	@Select("SELECT * FROM question WHERE qno in (SELECT qno FROM train_q WHERE trainNo = #{0}) AND qType=#{1}")
	List<Question> getQuestionListByTrainNoQType(int trainNo, int qType);

	/**
	 * ��ȡ��Ŀ �������
	 */
	@Select("SELECT * FROM question WHERE qNo = #{qNo}")
	List<Question> getQuestionByQNo(int qNo);

	/**
	 * ��ȡѡ�� �������
	 */
	@Select("SELECT * FROM `option` WHERE qNo = #{qNo}")
	List<Option> getOptionListByQNo(int qNo);

	/**
	 * ��ȡ���⻺���б�
	 */
	@Select("SELECT * FROM `testsheet_cache` WHERE sno=#{sNo} AND trainNo=#{trainNo}")
	List<Testsheet> getTestsheetChcheList(Testsheet ts);

	/**
	 * ��ȡ���⻺��
	 */
	@Select("SELECT * FROM `testsheet_cache` WHERE sno=#{sNo} AND qNo=#{qNo} AND trainNo=#{trainNo}")
	List<Testsheet> getTestsheetChche(Testsheet ts);

	/**
	 * ��ȡ���⻺���б�
	 */
	@Select("SELECT * FROM `testsheet` WHERE sno=#{sNo} AND trainNo=#{trainNo}")
	List<Testsheet> getTestsheetList(Testsheet ts);

	/**
	 * ��ȡ���⻺��
	 */
	@Select("SELECT * FROM `testsheet` WHERE sno=#{sNo} AND qNo=#{qNo} AND trainNo=#{trainNo}")
	List<Testsheet> getTestsheet(Testsheet ts);

	/**
	 * �������⻺��
	 */
	@Insert("INSERT INTO `testsheet_cache` VALUES (#{sNo}, #{qNo}, #{trainNo}, #{qAnswer}, #{tsIsRight})")
	void addTestsheetCache(Testsheet ts);

	/**
	 * ɾ�����⻺��
	 */
	@Delete("DELETE FROM `testsheet_cache` WHERE sno=#{sNo} AND qNo=#{qNo} AND trainNo=#{trainNo}")
	void deleteTestsheetCache2(Testsheet ts);

	/**
	 * ɾ�����⻺��
	 */
	@Delete("DELETE FROM `testsheet_cache` WHERE sno=#{sNo} AND trainNo=#{trainNo}")
	void deleteTestsheetCache(Testsheet ts);

	/**
	 * ��������
	 */
	@Insert("INSERT INTO testsheet SELECT * FROM testsheet_cache WHERE sno=#{sNo} AND trainNo=#{trainNo}")
	void addTestsheet(Testsheet ts);

	// �Ծ�
	/**
	 * ��ȡ�Ծ� �����ϴ�
	 */
	@Select(sql2 + " ORDER BY eTime DESC LIMIT 10")
	List<Exam> getExamListByTime();

	/**
	 * ��ȡ�Ծ� �����Ծ��
	 */
	@Select("SELECT * FROM exam WHERE eNo = #{eNo}")
	List<Exam> getExamListByENo(String eNo);

	/***
	 * ��ȡ��Ŀ�б� �����Ծ��,��Ŀ����
	 * 
	 * @param eNo
	 * @param qType
	 * @return
	 */
	@Select("SELECT * FROM question WHERE qno in (SELECT qno FROM exam_q WHERE eNo = #{0}) AND qType=#{1}")
	List<Question> getQuestionListByENoQType(String eNo, int qType);

	/**
	 * ��ȡ��Ŀ�б� �����Ծ��
	 */
	@Select("SELECT * FROM exam_q WHERE eNo = #{eNo}")
	List<ExamQ> getQuestionListByENo(String eNo);

	/**
	 * ��ȡ�����б�
	 */
	@Select("SELECT * FROM `examsheet` WHERE sno=#{sNo} AND eNo=#{eNo}")
	List<Examsheet> getExamsheetList(Examsheet es);

	/**
	 * ��ȡ�����б�
	 */
	@Select("SELECT * FROM `examsheet` WHERE sno=#{sNo} AND qNo=#{qNo} AND eNo=#{eNo}")
	List<Examsheet> getExamsheet(Examsheet es);

	/**
	 * ��ȡ���⻺���б�
	 */
	@Select("SELECT * FROM `examsheet_cache` WHERE sno=#{sNo} AND eNo=#{eNo}")
	List<Examsheet> getExamsheetCacheList(Examsheet es);

	/**
	 * ��ȡ���⻺���б�
	 */
	@Select("SELECT * FROM `examsheet_cache` WHERE sno=#{sNo} AND qNo=#{qNo} AND eNo=#{eNo}")
	List<Examsheet> getExamsheetCache(Examsheet es);

	/**
	 * �������⻺��
	 */
	@Insert("INSERT INTO `examsheet_cache` VALUES (#{eNo}, #{sNo}, #{qNo}, #{qAnswer}, #{tsIsRight})")
	void addExamsheetCache(Examsheet es);

	/**
	 * ɾ�����⻺��
	 */
	@Delete("DELETE FROM `examsheet_cache` WHERE sno=#{sNo} AND qNo=#{qNo} AND eNo=#{eNo}")
	void deleteExamsheetCache2(Examsheet es);

	/**
	 * ɾ�����⻺��
	 */
	@Delete("DELETE FROM `examsheet_cache` WHERE sno=#{sNo} AND eNo=#{eNo}")
	void deleteExamsheetCache(Examsheet es);

	/**
	 * ��������
	 */
	@Insert("INSERT INTO examsheet SELECT * FROM Examsheet_cache WHERE sno=#{sNo} AND eNo=#{eNo}")
	void addExamsheet(Examsheet es);

	/**
	 * ������
	 */
	@Insert("INSERT INTO examresult VALUES (#{sNo},#{eNo},#{erMark},CURRENT_TIMESTAMP)")
	void addExamresult(Examresult er);

	/**
	 * ��ȡ���
	 */
	@Select("SELECT * FROM `examresult` WHERE sno=#{sNo} AND eNo=#{eNo}")
	List<Examresult> getExamresult(Examresult er);

	/**
	 * ���뿪ʼ���ʱ��
	 */
	@Insert("INSERT INTO `examtime` (SNo, ENo) VALUES (#{0}, #{1})")
	void addTestTime(String sNo, String eNo);

	/**
	 * ��ȡ��ʼ���ʱ��
	 */
	@Select("SELECT * FROM `examtime` WHERE sno=#{0} AND eNo=#{1}")
	List<Examtime> getTestTime(String sNo, String eNo);

	/**
	 * ��ȡ���
	 */
	@Select("SELECT * FROM `examresult` WHERE sno=#{0} ORDER BY seTime DESC")
	List<Examresult> getExamresultBySno(String sNo);

	@Select("SELECT * FROM ojproblem WHERE ChaNo = #{0}")
	List<OJProblem> getOjProblemListByChaNo(int ChaNo);
	
	@Insert("INSERT IGNORE INTO ojacproblem(SNo,ojid) values(#{0},#{1})")
	void addRecordAccepted(String SNo,int trainNo);
	
	@Select("select a.* from question a where a.QKnowledge in \r\n" + 
			"(select id from knowledge where id in (select rid from preview_knowledge where tpid \r\n" + 
			"in (select max(id) from tpreview group by ClaNo having ClaNo = #{0}) and ClaNo = #{0})) and a.QType = #{1}")
	List<ClassFeedback> getClassFeedbackQuestionare(String claNo,int qType);
	
	@Update("update knowledge\r\n" + 
			"set hardscore = hardscore + #{1}\r\n" + 
			"where id = #{0}")
	void updateFeedbackKnowledgeScore(int id,int score);
	
	@Insert("INSERT IGNORE INTO classfeedbackcache values(#{0},#{1},#{2},#{3})")
	void addFeedbacksheetCache(String SNo,int qNo,String answer,int hash);
	
	@Select("SELECT * FROM classfeedbackcache WHERE SNo = #{0} and hash = #{1}")
	List<FeedbackCache> getFeedbackCacheList(String SNo,int hash);
	
	@Select("select a.QKnowledge,count(*)/count(a.QNo) as rate from question a,classfeedbackcache b where a.QAnswer = b.QAnswer\r\n" + 
			"and b.SNo = #{0} and hash = #{1} group by a.QNo")
	List<FeedbackExamResult> getFeedbacksheetResult(String SNo,int hash);
	
	@Select("select a.QNo from question a,classfeedbackcache b where a.QNo = b.QNo and a.QAnswer != b.QAnswer\r\n" + 
			"and b.hash = #{1} and b.SNo = #{0}")
	List<WrongQuestion> getWrongQuestionFromFeedbacksheet(String SNo,int hash);
	
	@Update("update student_knowledge set class_veryHigh = class_veryHigh + 1 "
			+ "where SNo = #{0} and kid = #{1}")
	void updateVeryHigh(String SNo,int id);
	
	@Update("update student_knowledge set class_High = class_High + 1 "
			+ "where SNo = #{0} and kid = #{1}")
	void updateHigh(String SNo,int id);
	
	@Update("update student_knowledge set class_Low = class_Low + 1 "
			+ "where SNo = #{0} and kid = #{1}")
	void updateLow(String SNo,int id);
	
	@Update("update student_knowledge set class_veryLow = class_veryLow + 1 "
			+ "where SNo = #{0} and kid = #{1}")
	void updateVeryLow(String SNo,int id);
	
	@Update("update knowledge set class_veryHigh = class_veryHigh + 1 "
			+ "where id = #{0}")
	void updateKnowVeryHigh(int id);
	
	@Update("update knowledge set class_High = class_High + 1 "
			+ "where id = #{0}")
	void updateKnowHigh(int id);
	
	@Update("update knowledge set class_Low = class_Low + 1 "
			+ "where id = #{0}")
	void updateKnowLow(int id);
	
	@Update("update knowledge set class_veryLow = class_veryLow + 1 "
			+ "where id = #{0}")
	void updateKnowVeryLow(int id);
	
	@Select("select count(*) from student_knowledge where  \r\n" + 
			"kid in (select rid from preview_knowledge where tpid in\r\n" + 
			"(select max(id) from tpreview group by ClaNo having ClaNo = #{1})) and (class_veryHigh != 0 or class_High != 0 or\r\n" + 
			"class_Low != 0 or class_veryLow != 0 ) and SNo = #{0}")
	int checkFeedQuestionState(String SNo,String claNo);
	
	@Insert("Insert into wrongquestion(qid) values(#{0})")
	void addWrongQuestion(int qno);
}
