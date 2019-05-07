package com.active.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.active.entity.Chapter;
import com.active.entity.ClassRoom;
import com.active.entity.Document;
import com.active.entity.Exam;
import com.active.entity.ExamQ;
import com.active.entity.Examsheet;
import com.active.entity.Knowledge;
import com.active.entity.OJProblem;
import com.active.entity.Option;
import com.active.entity.Question;
import com.active.entity.Student;
import com.active.entity.Teacher;
import com.active.entity.Training;
import com.active.entity.Video;


public interface AdminDao {

	// login
	/**
	 * 验证用户名,密码
	 * 
	 * @param tNo
	 * @param tPwd
	 * @return
	 */
	@Select("SELECT tno,tname,tsex,tschool,tcollege,tmajor,tabout FROM teacher WHERE tno = #{0} and tpwd = #{1}")
	List<Teacher> getTeacherByTnoTpwd(String tNo, String tPwd);

	// all
	@Select("SELECT * FROM chapter")
	List<Chapter> getAllChapter();

	// video/flash
	@Select("SELECT * FROM video WHERE vtype=1 AND tno = #{tNo} ORDER BY vtime DESC")
	List<Video> getVideosByTno(String tNo);

	@Select("SELECT * FROM video WHERE vtype=2 AND tno = #{tNo} ORDER BY vtime DESC")
	List<Video> getFlashsByTno(String tNo);

	@Insert("INSERT INTO video (VName,VIntroduce,VType,ChaNo,VTime,TNo,VCount,VAddress,VImg) VALUES (#{vname},#{vintroduce},#{vtype},#{chano},CURRENT_TIMESTAMP,#{tno},0,#{vaddress},#{vimg})")
	void addVideo(Video video);

	@Delete("DELETE FROM video WHERE vno = #{vno}")
	void delVideo(int vno);

	// document
	@Select("SELECT * FROM document WHERE tno = #{tNo} ORDER BY doctime DESC")
	List<Document> getDocsByTno(String tNo);

	@Insert("INSERT INTO document (DocName,DocTime,TNo,DocPath,DocType,ChaNo) VALUES (#{docName},CURRENT_TIMESTAMP,#{tno},#{docPath},#{docType},#{chano})")
	void addDoc(Document doc);

	// question
	@SelectKey(resultType = int.class, before = false, keyProperty = "qNo", statement = { "SELECT LAST_INSERT_ID() AS qNo" })
	@Insert("INSERT INTO question (QTitle,QType,ChaNo,QDifficulty,QRange,QAnswer,QKnowledge) VALUES (#{qTitle},#{qType},#{chaNo},#{qDifficulty},#{qRange},#{qAnswer},#{qKnowledge})")
	void addQuestion(Question q);

	@Insert("INSERT INTO `option` (ONo,QNo,OContent) VALUES (#{oNo},#{qNo},#{oContent})")
	void addOptions(Option o);

	@Select("SELECT * FROM question WHERE QType = ifnull(#{0},QType) AND QDifficulty=ifnull(#{1},QDifficulty) AND ChaNo=ifnull(#{2},ChaNo)")
	List<Question> getQuestions(String qType, String qDifficulty, String chaNo);

	@Delete("DELETE FROM question WHERE QNo = #{qNo}")
	void delQuestion(int qNo);

	@Delete("DELETE FROM `option` WHERE QNo = #{qNo}")
	void delOptions(int qNo);

	@Select("SELECT LAST_INSERT_ID()")
	int getQnoByAdd();

	// training
	@Select("SELECT * FROM Training WHERE TrainType = ifnull(#{0},TrainType) AND ChaNo=ifnull(#{1},ChaNo)")
	List<Training> getTrainings(String trainType, String chaNo);

	// exam
	@Select("SELECT * FROM exam WHERE tno = #{tNo}")
	List<Exam> getExams(String tNo);

	@Select("SELECT * FROM exam WHERE tno = #{tNo} AND EEndTime < current_timestamp()")
	List<Exam> getExamsNeedCorrect(String tNo);

	@Select("SELECT COUNT(DISTINCT sno) FROM examresult WHERE ENo = #{eNo}")
	String getStuDoExamNum(String eNo);

	@Select("SELECT COUNT(DISTINCT sno) FROM student")
	String getStuNum();

	@Select("SELECT COUNT(*) FROM examresult WHERE eno=#{eNo} AND ermark!=-1")
	String getToCorrectNum(String eNo);

	@Select("SELECT sno FROM examresult WHERE eno = #{eNo} AND ermark = -1 ORDER BY setime ASC LIMIT 0,1")
	String getFirstSno(String eNo);

	@Insert("INSERT INTO exam VALUES (#{eNo},#{eName},#{tNo},#{claNo},CURRENT_TIMESTAMP,#{eBeginTime},#{eEndTime},#{eDuration},#{eMark})")
	void addExams(Exam exam);

	@Insert("INSERT INTO exam_q VALUES (#{eNo},#{qNo},#{qMark})")
	void addExamQ(ExamQ examQ);

	@Delete("DELETE FROM exam WHERE eNo = #{eNo}")
	void delExam(String eNo);

	@Delete("DELETE FROM exam_q WHERE eNo = #{eNo}")
	void delExamQ(String eNo);

	@Update("UPDATE examsheet SET tsisright = #{tsIsRight} WHERE eno = #{eNo} AND sno = #{sNo} AND qno = #{qNo}")
	void updateMark(Examsheet examsheet);

	@Update("UPDATE examresult SET ermark = #{tsIsRight} WHERE eno = #{eNo} AND sno = #{sNo}")
	void updateResult(Examsheet examsheet);

	@Select("SELECT * FROM student WHERE claNo='666' AND sno in (SELECT DISTINCT sno FROM examsheet WHERE eno = #{eNo})")
	List<Student> getExamStudents(String eNo);

	@Select("SELECT * FROM student WHERE claNo='666'")
	List<Student> getStudents();

	// info
	/**
	 * 验证获取个人信息
	 * 
	 * @param tNo
	 * @return
	 */
	@Select("SELECT tno,tname,tsex,tschool,tcollege,tmajor,tabout,ClaNo as claNo FROM teacher,classroom WHERE tno = #{tNo}"
			+ "and TNo = CTNo")
	List<Teacher> getTeacherByTno(String tNo);
	
	@Select("SELECT a.*,b.ChaName as ChaName FROM ojproblem a,chapter b where a.ChaNo = b.ChaNo;")
	List<OJProblem> getOJList();
	
	@Insert("INSERT INTO tpreview(TNo,ClaNo) values(#{0},#{1})")
	void insertNewPreview(String tno,String claNo);
	
	@Select("SELECT Max(id) from tpreview where TNo = #{0} and ClaNo = #{1}")
	int getLastestPreviewId(String tno,String claNo);
	
	@Insert("INSERT INTO tpreview_detail(tpid,trequire,isonline) values(#{tpid},#{trequire},'0')")
	void insertOutlinePreviewDetail(@Param("tpid")int tpid,@Param("trequire")String trequire);
	
	@Insert("INSERT INTO tpreview_detail(tpid,trequire,require_type,require_id,isonline)\r\n"
			+" values(#{tpid},#{trequire},#{require_type},#{require_id},'1')")
	void insertOnlinePreviewDetail(@Param("tpid")int tpid,
			                     @Param("trequire")String trequire,
			                     @Param("require_type")int require_type,
			                     @Param("require_id")int require_id);
	
	@Insert("insert ignore into spreview(SNo,tpdid,sstate) select a.SNo, b.id as tpid,'未完成' as sstate from student a,tpreview_detail b where a.ClaNo = #{1}\r\n" + 
			"and b.tpid in (select max(tpid) from tpreview)")
	void broadcastStudentOnlinePreview(String claNo);
	
	@Select("Select * from knowledge where chpNo = #{0}")
	List<Knowledge>getKnowledgeBychaNo(int chpNo);
	
	@Insert("INSERT INTO preview_knowledge(TNo,ClaNo,tpid,rid) values(#{0},#{1},#{2},#{3})")
	void insertNewPreviewKnowledge(String tno,String claNo,int tpid,int rid);
	
	@Select("SELECT count(*) + 1 FROM ojproblem")
	int getOJId();
	
	@Insert("INSERT INTO ojproblem(title,ChaNo,difficulty,knowledgeId) values(#{title},#{chaNo},#{difficulty},#{knowledgeId})")
	void addOjProblem(OJProblem oj);
	
	@Select("SELECT * FROM ojproblem where ChaNo = ifnull(#{0},ChaNo) and difficulty = ifnull(#{1},difficulty)")
	List<OJProblem> getOJs(String chaNo,String difficulty);
	
	@Delete("Delete from ojproblem where id = #{0}")
	void delOJQuestion(int trainNo);
	
	@Select("Select * from student where ClaNo = #{0}")
	List<Student> getStudentByclaNo(String claNo);
	
	@Delete("Delete from document where DocNo = #{0}")
	void deleteDcoBydocNo(String docNo);
	
	@Select("select a.*,b.ChaName from video a,chapter b where a.chaNo = b.ChaNo")
	List<Video> getVideoList();
	
	@Select("select * from classroom where CTNo = #{0}")
	List<ClassRoom> getClassByTno(String tno);
	
	@Insert("Insert Ignore into classroom values(#{1},#{1},#{0})")
	void insertClass(String tno,String name);
	
	@Insert("Insert Ignore into student values(#{0},#{0},#{1},'男','安徽农业大学',#{2},#{3})")
	void addStudentInfo(String sno,String sname,String smajor,String claNo);
	
	@Select("Select * from student")
	List<Student> getAllStudent();
	
	@Select("select * from knowledge where chpNo in \r\n" + 
			"(select a.chaNo from chapter a,chapter b,chapter c \r\n" + 
			"where a.chaNoLast = b.chaNo and b.chaNoLast = c.chaNo and c.chaNo = #{0}) ")
	List<Knowledge> getAllKnowledegeInThisChapter(int chaNo);
	
	@Select("select a.id,b.knowledge,a.preview_veryHigh,a.preview_High,\r\n" + 
			"a.preview_Low,a.preview_veryLow,a.class_veryHigh,\r\n" + 
			"a.class_High,a.class_Low,a.class_veryLow,b.chpNo\r\n" + 
			"from student_knowledge a,knowledge b\r\n" + 
			"where a.kid = b.id and b.chpNo in (select c.ChaNo from  chapter c,chapter d,chapter e\r\n" + 
			"where c.ChaNoLast = d.ChaNo and d.ChaNoLast = e.ChaNo and e.ChaNo = #{1}) and a.SNo = #{0}")
	List<Knowledge> getPersonData(String sno,int chaNo);
	
	@Insert("Insert ignore into chapter(ChaName,ChaNoLast) values(#{0},0)")
	void addFirstChapter(String chpName);
	
	@Insert("Insert ignore into chapter(ChaName,ChaNoLast) values(#{1},#{0})")
	void addNotFirstChapter(int chaNoLast,String chpName);
	
	@Insert("Insert ignore into knowledge(knowledge,chpNo) values(#{0},#{1})")
	void addKnowledge(String knowledge,int chpNo);
	
	@Select("select	ifnull(sum(case when b.require_type = 1 and b.require_id = c.id then 5 * c.difficulty \r\n" + 
			"		   else 10 end),0) as usualScore\r\n" + 
			"from spreview a,tpreview_detail b,ojproblem c where a.sstate = '已完成' \r\n" + 
			"and a.tpdid = b.id and a.SNo = #{0}")
	int getUsualScoreBySno(String sno);
	
	@Select("select * from knowledge where id = #{0}")
	List<Knowledge> getKnowledgeName(int id);
	
	@Select("select tno,tname,tsex,tschool,tcollege,tmajor,tabout from teacher where TNo = #{0}")
	List<Teacher> getTeacherOnlyByTno(String tNo);
}
