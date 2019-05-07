package com.active.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.active.entity.Chapter;
import com.active.entity.Document;
import com.active.entity.Feedback;
import com.active.entity.Graph;
import com.active.entity.OJProblem;
import com.active.entity.Preview;
import com.active.entity.PreviewQuestionaire;
import com.active.entity.Student;
import com.active.entity.Video;


public interface SqlDao {

	/**
	 * 反馈
	 */
	@Insert("INSERT INTO `feedback` (FeedTitle,FeedContent,SNo) VALUES (#{feedTitle},#{feedContent},#{sNo})")
	void addFeedback(Feedback fb);

	/**
	 * 验证用户名密码
	 */
	@Select("select sno,sname,ssex,scollege,smajor,clano from student where SNo = #{sno} and SPwd = #{spwd}")
	List<Student> getStudentBySnoSpwd(Student student);

	/**
	 * 改密
	 */
	@Update("UPDATE student SET spwd = #{spwd} WHERE sno = #{sno}")
	void ChangePwd(Student student);

	/**
	 * 获取个人信息
	 */
	@Select("select sno,sname,ssex,scollege,smajor,clano from student where SNo = #{sno}")
	List<Student> getStudentBySno(Student student);

	/**
	 * 读取章节 根据上级章节号
	 */
	@Select("SELECT * FROM Chapter WHERE ChaNoLast = #{chaNoLast}")
	List<Chapter> getChapterListByChaNoLast(int chaNoLast);

	/**
	 * 读取视频 所有
	 */
	@Select("SELECT * FROM Video WHERE vtype=1")
	List<Video> getVideoList();

	/**
	 * 读取视频 根据章节号
	 */
	@Select("SELECT * FROM Video WHERE ChaNo = #{chaNo} AND vtype=1")
	List<Video> getVideoListByChaNo(int chaNo);

	/**
	 * 读取视频/动画 根据视频号
	 */
	@Select("SELECT * FROM Video WHERE VNo = #{vNo}")
	List<Video> getVideoListByVNo(String vNo);

	/**
	 * 读取视频 最新上传
	 */
	@Select("SELECT * FROM video WHERE vtype=1 ORDER BY vtime DESC LIMIT 10")
	List<Video> getVideoListByTime();

	/**
	 * 读取视频 最多观看
	 */
	@Select("SELECT * FROM video WHERE vtype=1 ORDER BY vcount DESC LIMIT 10")
	List<Video> getVideoListByHot();

	/**
	 * 更新视频/动画 增加点击量
	 */
	@Update("UPDATE video SET vcount = #{vcount} WHERE vno = #{vno}")
	Boolean updateVideoCount(Video video);

	/**
	 * 读取文件 所有
	 */
	@Select("SELECT document.*,teacher.tname FROM document INNER JOIN teacher on document.TNo=teacher.TNo ORDER BY doctime DESC")
	List<Document> getDocList();

	/**
	 * 读取文件 最新上传
	 */
	@Select("SELECT document.*,teacher.tname FROM document INNER JOIN teacher on document.TNo=teacher.TNo ORDER BY doctime DESC LIMIT 10")
	List<Document> getDocListByTime();

	/**
	 * 读取文件 根据章节号
	 */
	@Select("SELECT * FROM document WHERE ChaNo = #{chaNo}")
	List<Document> getDocListByChaNo(int chaNo);

	/**
	 * 读取视频 所有
	 */
	@Select("SELECT * FROM Video WHERE vtype=2")
	List<Video> getFlashList();

	/**
	 * 读取视频 根据章节号
	 */
	@Select("SELECT * FROM Video WHERE ChaNo = #{chaNo} AND vtype=2")
	List<Video> getFlashListByChaNo(int chaNo);

	/**
	 * 读取动画 最新上传
	 */
	@Select("SELECT * FROM video WHERE vtype=2 ORDER BY vtime DESC LIMIT 10")
	List<Video> getFlashListByTime();

	/**
	 * 读取动画 最多观看
	 */
	@Select("SELECT * FROM video WHERE vtype=2 ORDER BY vcount DESC LIMIT 10")
	List<Video> getFlashListByHot();
	
	/*
	 * 按照学生所在班级读取教师安排的预习要求
	 */
	@Select("select a.*,b.trequire,b.require_type,b.require_id,isonline from spreview a,tpreview_detail b \r\n" + 
			"where a.tpdid = b.id and b.tpid = (select MAX(id) from tpreview group by ClaNo "
			+ "having ClaNo = #{1} ) and a.SNo = #{0}")
	List<Preview> getPreviewRequireBySNo(String SNo,String claNo);
	
	/*
	 * 按照学生所在班级得到预习的知识点问卷
	 */
	@Select("select a.*,b.knowledge from preview_knowledge a,knowledge b "
			+ "where a.tpid = (select MAX(id) from tpreview group by ClaNo having ClaNo = #{0}) "
			+ "and a.rid = b.id and a.ClaNo = #{ClaNo}")
	List<PreviewQuestionaire> getPreviewQuestionaireByClaNo(String claNo);
	
	@Update("Update knowledge\r\n"+
			"set preview_veryHigh = preview_veryHigh + 1 where id = #{0}")
	void updatePreviewVeryHigh(int id);
	
	@Update("Update knowledge\r\n" +
			"set preview_High = preview_High + 1 where id = #{0}")
	void updatePreviewHigh(int id);
	
	@Update("Update knowledge\r\n" +
	        "set preview_Low = preview_Low + 1 where id = #{0}")
	void updatePreviewLow(int id);
	
	@Update("Update knowledge\r\n" +
	        "set preview_veryLow = preview_veryLow + 1 where id = #{0}")
	void updatePreviewVeryLow(int id);
	
	@Insert("Insert into student_knowledge(SNo,kid,preview_veryHigh,tpid) values(#{0},#{1},1,#{2})")
	void insertVeryHigh(String SNo,int id,int tpid);
	
	@Insert("Insert into student_knowledge(SNo,kid,preview_High,tpid) values(#{0},#{1},1,#{2})")
	void insertHigh(String SNo,int id,int tpid);
	
	@Insert("Insert into student_knowledge(SNo,kid,preview_Low,tpid) values(#{0},#{1},1,#{2})")
	void insertLow(String SNo,int id,int tpid);
	
	@Insert("Insert into student_knowledge(SNo,kid,preview_veryLow,tpid) values(#{0},#{1},1,#{2})")
	void insertVeryLow(String SNo,int id,int tpid);
	
	@Select("SELECT COUNT(*) from ojacproblem where SNo = #{0} and ojid = #{1}")
	int selectOJState(String SNo,int id);
	
	@Select("select count(*) from student_knowledge where kid \r\n" + 
			"in (select rid from preview_knowledge where tpid in (select max(tpid) from preview_knowledge))\r\n" + 
			"and SNo = #{0} and tpid = #{1}")
	int checkState(String Sno,int tpid);
	
	@Select("select max(tpid) from tpreview_detail a,tpreview b where a.tpid = b.id and b.claNo = #{0}")
	int getLastPreviewIdByclaNo(String claNo);
	
	@Select("select knowledge,(preview_veryHigh + preview_High) as High,(preview_Low + preview_veryLow) as Low \r\n" + 
			"from knowledge\r\n" + 
			"where chpNo in (select a.chaNo from chapter a,chapter b,chapter c \r\n" + 
			"where a.chaNoLast = b.chaNo and b.chaNoLast = c.chaNo and c.chaNo = #{0})")
	List<Graph> getPreviewTotolGraph(String chaNo);
	
	@Select("select knowledge,(class_veryHigh + class_High) as High,(class_Low + class_veryLow) as Low \r\n" + 
			"from knowledge\r\n" + 
			"where chpNo in (select a.chaNo from chapter a,chapter b,chapter c \r\n" + 
			"where a.chaNoLast = b.chaNo and b.chaNoLast = c.chaNo and c.chaNo = #{0})")
	List<Graph> getClassTotolGraph(String chaNo);
	
	@Select("select knowledge,(sk.preview_veryHigh + sk.preview_High) as High,(sk.preview_Low + sk.preview_veryLow) as Low \r\n" + 
			"from student_knowledge sk,knowledge kn\r\n" + 
			"where sk.kid = kn.id and sk.SNo = #{1} and kn.chpNo in (select a.chaNo from chapter a,chapter b,chapter c \r\n" + 
			"where a.chaNoLast = b.chaNo and b.chaNoLast = c.chaNo and c.chaNo = #{0})")
	List<Graph> getPreviewTotolPersonGraph(String chaNo,String sno);
	
	@Select("select knowledge,(sk.class_veryHigh + sk.class_High) as High,(sk.class_Low + sk.class_veryLow) as Low \r\n" + 
			"from student_knowledge sk,knowledge kn\r\n" + 
			"where sk.kid = kn.id and sk.SNo = #{1} and kn.chpNo in (select a.chaNo from chapter a,chapter b,chapter c \r\n" + 
			"where a.chaNoLast = b.chaNo and b.chaNoLast = c.chaNo and c.chaNo = #{0})")
	List<Graph> getClassTotolPersonGraph(String chaNo,String sno);
	
	@Update("Update spreview set sstate = '已完成' where id = #{0}")
	void updateStudentPreviewState(int id);
	
	@Insert("INSERT IGNORE INTO videofinish(sno,vno) values(#{0},#{1})")
	void addVideoFinish(String sno,int vno);
	
	@Select("SELECT COUNT(*) from videofinish where sno = #{0} and vno = #{1}")
	int selectVideoState(String sno,int id);
	
	@Select("Select * from ojproblem where id = #{0}")
	List<OJProblem> getOJById(int id);
}
