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
	 * ����
	 */
	@Insert("INSERT INTO `feedback` (FeedTitle,FeedContent,SNo) VALUES (#{feedTitle},#{feedContent},#{sNo})")
	void addFeedback(Feedback fb);

	/**
	 * ��֤�û�������
	 */
	@Select("select sno,sname,ssex,scollege,smajor,clano from student where SNo = #{sno} and SPwd = #{spwd}")
	List<Student> getStudentBySnoSpwd(Student student);

	/**
	 * ����
	 */
	@Update("UPDATE student SET spwd = #{spwd} WHERE sno = #{sno}")
	void ChangePwd(Student student);

	/**
	 * ��ȡ������Ϣ
	 */
	@Select("select sno,sname,ssex,scollege,smajor,clano from student where SNo = #{sno}")
	List<Student> getStudentBySno(Student student);

	/**
	 * ��ȡ�½� �����ϼ��½ں�
	 */
	@Select("SELECT * FROM Chapter WHERE ChaNoLast = #{chaNoLast}")
	List<Chapter> getChapterListByChaNoLast(int chaNoLast);

	/**
	 * ��ȡ��Ƶ ����
	 */
	@Select("SELECT * FROM Video WHERE vtype=1")
	List<Video> getVideoList();

	/**
	 * ��ȡ��Ƶ �����½ں�
	 */
	@Select("SELECT * FROM Video WHERE ChaNo = #{chaNo} AND vtype=1")
	List<Video> getVideoListByChaNo(int chaNo);

	/**
	 * ��ȡ��Ƶ/���� ������Ƶ��
	 */
	@Select("SELECT * FROM Video WHERE VNo = #{vNo}")
	List<Video> getVideoListByVNo(String vNo);

	/**
	 * ��ȡ��Ƶ �����ϴ�
	 */
	@Select("SELECT * FROM video WHERE vtype=1 ORDER BY vtime DESC LIMIT 10")
	List<Video> getVideoListByTime();

	/**
	 * ��ȡ��Ƶ ���ۿ�
	 */
	@Select("SELECT * FROM video WHERE vtype=1 ORDER BY vcount DESC LIMIT 10")
	List<Video> getVideoListByHot();

	/**
	 * ������Ƶ/���� ���ӵ����
	 */
	@Update("UPDATE video SET vcount = #{vcount} WHERE vno = #{vno}")
	Boolean updateVideoCount(Video video);

	/**
	 * ��ȡ�ļ� ����
	 */
	@Select("SELECT document.*,teacher.tname FROM document INNER JOIN teacher on document.TNo=teacher.TNo ORDER BY doctime DESC")
	List<Document> getDocList();

	/**
	 * ��ȡ�ļ� �����ϴ�
	 */
	@Select("SELECT document.*,teacher.tname FROM document INNER JOIN teacher on document.TNo=teacher.TNo ORDER BY doctime DESC LIMIT 10")
	List<Document> getDocListByTime();

	/**
	 * ��ȡ�ļ� �����½ں�
	 */
	@Select("SELECT * FROM document WHERE ChaNo = #{chaNo}")
	List<Document> getDocListByChaNo(int chaNo);

	/**
	 * ��ȡ��Ƶ ����
	 */
	@Select("SELECT * FROM Video WHERE vtype=2")
	List<Video> getFlashList();

	/**
	 * ��ȡ��Ƶ �����½ں�
	 */
	@Select("SELECT * FROM Video WHERE ChaNo = #{chaNo} AND vtype=2")
	List<Video> getFlashListByChaNo(int chaNo);

	/**
	 * ��ȡ���� �����ϴ�
	 */
	@Select("SELECT * FROM video WHERE vtype=2 ORDER BY vtime DESC LIMIT 10")
	List<Video> getFlashListByTime();

	/**
	 * ��ȡ���� ���ۿ�
	 */
	@Select("SELECT * FROM video WHERE vtype=2 ORDER BY vcount DESC LIMIT 10")
	List<Video> getFlashListByHot();
	
	/*
	 * ����ѧ�����ڰ༶��ȡ��ʦ���ŵ�ԤϰҪ��
	 */
	@Select("select a.*,b.trequire,b.require_type,b.require_id,isonline from spreview a,tpreview_detail b \r\n" + 
			"where a.tpdid = b.id and b.tpid = (select MAX(id) from tpreview group by ClaNo "
			+ "having ClaNo = #{1} ) and a.SNo = #{0}")
	List<Preview> getPreviewRequireBySNo(String SNo,String claNo);
	
	/*
	 * ����ѧ�����ڰ༶�õ�Ԥϰ��֪ʶ���ʾ�
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
	
	@Update("Update spreview set sstate = '�����' where id = #{0}")
	void updateStudentPreviewState(int id);
	
	@Insert("INSERT IGNORE INTO videofinish(sno,vno) values(#{0},#{1})")
	void addVideoFinish(String sno,int vno);
	
	@Select("SELECT COUNT(*) from videofinish where sno = #{0} and vno = #{1}")
	int selectVideoState(String sno,int id);
	
	@Select("Select * from ojproblem where id = #{0}")
	List<OJProblem> getOJById(int id);
}
