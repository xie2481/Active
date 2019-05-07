package com.active.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.active.dao.AdminDao;
import com.active.dao.SqlDao;
import com.active.entity.Feedback;
import com.active.entity.Preview;
import com.active.entity.Student;
import com.active.entity.Teacher;
import com.active.entity.Video;
import com.active.util.UtilTool;

@Service
public class ActiveService {

	@Resource
	private SqlDao sd;
	private UtilTool ut = new UtilTool();

	public void addFeedback(String callback, String feedTitle,
			String feedContent, String sNo) {
		Feedback fb = new Feedback();
		fb.setFeedTitle(feedTitle);
		fb.setFeedContent(feedContent);
		fb.setsNo(sNo);
		sd.addFeedback(fb);
	}

	public String loginBySnoSpwd(String callback, String sNo, String sPwd) {
		Student student = new Student();
		student.setSno(sNo);
		student.setSpwd(sPwd);
		List<?> list = sd.getStudentBySnoSpwd(student);
		return ut.ListToJson(list, callback);
	}

	public String getStudentBySno(String callback, String sNo) {
		Student student = new Student();
		student.setSno(sNo);
		List<?> list = sd.getStudentBySno(student);
		return ut.ListToJson(list, callback);
	}

	public void ChangePwd(String callback, String sNo, String sPwd) {
		Student student = new Student();
		student.setSno(sNo);
		student.setSpwd(sPwd);
		sd.ChangePwd(student);
	}

	public String getChapterListByChaNoLast(String callback, int chaNoLast) {
		List<?> list = sd.getChapterListByChaNoLast(chaNoLast);
		return ut.ListToJson(list, callback);
	}

	public String getVideoList(String callback) {
		List<?> list = sd.getVideoList();
		return ut.ListToJson(list, callback);
	}

	public String getVideoListByChaNo(String callback, int chaNo) {
		List<?> list = sd.getVideoListByChaNo(chaNo);
		return ut.ListToJson(list, callback);
	}

	public String getVideoListByVNo(String callback, String vNo) {
		List<?> list = sd.getVideoListByVNo(vNo);
		return ut.ListToJson(list, callback);
	}

	public String getVideoListByTime(String callback) {
		List<?> list = sd.getVideoListByTime();
		return ut.ListToJson(list, callback);
	}

	public String getVideoListByHot(String callback) {
		List<?> list = sd.getVideoListByHot();
		return ut.ListToJson(list, callback);
	}

	public String updateVideoCount(String callback, int vcount, String vno) {
		Video video = new Video();
		video.setVcount(vcount);
		video.setVno(vno);
		boolean b = sd.updateVideoCount(video);
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("result", b);
		String s = ut.MapToJson(map);
		if (callback != null) {
			s = callback + "(" + s + ")";
		}
		return s;
	}

	public String getDocList(String callback) {
		List<?> list = sd.getDocList();
		return ut.ListToJson(list, callback);
	}

	public String getDocListByTime(String callback) {
		List<?> list = sd.getDocListByTime();
		return ut.ListToJson(list, callback);
	}

	public String getDocListByChaNo(String callback, int chaNo) {
		List<?> list = sd.getDocListByChaNo(chaNo);
		return ut.ListToJson(list, callback);
	}

	public String getFlashList(String callback) {
		List<?> list = sd.getFlashList();
		return ut.ListToJson(list, callback);
	}

	public String getFlashListByChaNo(String callback, int chaNo) {
		List<?> list = sd.getFlashListByChaNo(chaNo);
		return ut.ListToJson(list, callback);
	}

	public String getFlashListByTime(String callback) {
		List<?> list = sd.getFlashListByTime();
		return ut.ListToJson(list, callback);
	}

	public String getFlashListByHot(String callback) {
		List<?> list = sd.getFlashListByHot();
		return ut.ListToJson(list, callback);
	}

	public String getPreviewRequireBySNo(String callback,@Param("SNo")String SNo,String claNo) {
		List<?> list = sd.getPreviewRequireBySNo(SNo,claNo);
		return ut.ListToJson(list,callback);
	}
	
	public String getPreviewAskByClaNo(String callback,@Param("ClaNo")String claNo) {
		List<?> list = sd.getPreviewQuestionaireByClaNo(claNo);
		return ut.ListToJson(list,callback); 
	}
	
	public  void updatePreviewKnowledgeScore(String callback,String sNo,String claNo,@Param("jsondata")String jsondata) {
		Map<String,String> maps = ut.jsonToMap(jsondata);
		int tpid = sd.getLastPreviewIdByclaNo(claNo);
		for(Map.Entry<String, String> entry : maps.entrySet()) {
			int mode = Integer.parseInt(entry.getValue());
			int id = Integer.parseInt(entry.getKey());
			/*更新选择相应选项的人数*/
			switch(mode) {
			case 1:/*完全不理解*/
				sd.updatePreviewVeryLow(id);
				sd.insertVeryLow(sNo,id,tpid);
				break;
			case 2:/*不太熟练*/
				sd.updatePreviewLow(id);
				sd.insertLow(sNo,id,tpid);
				break;
			case 3:/*基本掌握*/
				sd.updatePreviewHigh(id);
				sd.insertHigh(sNo, id,tpid);
				break;
			case 4:/*熟练*/
				sd.updatePreviewVeryHigh(id);
				sd.insertVeryHigh(sNo, id,tpid);
				break;
			}
		}
	}
	
	public String selectOJState(String callback,String SNo,int id) {
		if(sd.selectOJState(SNo, id) == 0) {
			return "false";
		} else {
			return "true";
		}
	}
	
	public String checkState(String callback,String SNo,String claNo) {
		int tpid = sd.getLastPreviewIdByclaNo(claNo);
		if(sd.checkState(SNo,tpid) != 0)
			return "true";
		else
			return "false";
	}
	
	public String getPreviewTotolGraph(String callback,String chaNo) {
		List<?> list = sd.getPreviewTotolGraph(chaNo);
		return ut.ListToJson(list,callback);
	}
	
	public String getClassTotolGraph(String callback,String chaNo) {
		List<?> list = sd.getClassTotolGraph(chaNo);
		return ut.ListToJson(list,callback);
	}
	public String getPreviewTotolPersonGraph(String callback,String chaNo,String sno) {
		List<?> list = sd.getPreviewTotolPersonGraph(chaNo, sno);
		return ut.ListToJson(list,callback);
	}
	
	public String getClassTotolPersonGraph(String callback,String chaNo,String sno) {
		List<?> list = sd.getClassTotolPersonGraph(chaNo, sno);
		return ut.ListToJson(list,callback);
	}
	
	public void updateStudentPreviewState(String callback,int id) {
		sd.updateStudentPreviewState(id);
	}
	
	public void addVideoFinish(String callback,String sno,int vno) {
		sd.addVideoFinish(sno, vno);
	}
	
	public String selectVideoState(String callback,String sno,int id) {
		if(sd.selectVideoState(sno, id) != 0) {
			return "true";
		} else {
			return "false";
		}
	}
	
	public String getOJById(String callback,int id) {
		List<?> list = sd.getOJById(id);
		return ut.ListToJson(list,callback);
	}
}
