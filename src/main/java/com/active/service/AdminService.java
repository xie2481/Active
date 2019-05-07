package com.active.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.active.dao.AdminDao;
import com.active.dao.QuestionDao;
import com.active.entity.Document;
import com.active.entity.Exam;
import com.active.entity.ExamQ;
import com.active.entity.Examsheet;
import com.active.entity.Knowledge;
import com.active.entity.OJProblem;
import com.active.entity.Option;
import com.active.entity.Question;
import com.active.entity.Video;
import com.active.util.CurrentPath;
import com.active.util.ParseJson;
import com.active.util.UtilTool;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;



@SuppressWarnings("restriction")
@Service
public class AdminService {

	@Resource
	private AdminDao ad;
	@Resource
	private QuestionDao qd;
	private UtilTool ut = new UtilTool();

	// login
	/**
	 * 登陆
	 * 
	 * @param callback
	 * @param tNo
	 * @param tPwd
	 * @return
	 */
	public String getTeacherByTnoTpwd(String callback, String tNo, String tPwd) {
		List<?> list = ad.getTeacherByTnoTpwd(tNo, tPwd);
		return ut.ListToJson(list, callback);
	}

	// all
	/**
	 * 获取所有章节
	 * 
	 * @param callback
	 * @return
	 */
	public String getAllChapter(String callback) {
		List<?> list = ad.getAllChapter();
		return ut.ListToJson(list, callback);
	}

	// video/flash
	/**
	 * 读取上传的视频
	 * 
	 * @param callback
	 * @param tNo
	 * @return
	 */
	public String getVideosByTno(String callback, String tNo) {
		List<?> list = ad.getVideosByTno(tNo);
		return ut.ListToJson(list, callback);
	}

	/**
	 * 读取上传的动画
	 * 
	 * @param callback
	 * @param tNo
	 * @return
	 */
	public String getFlashsByTno(String callback, String tNo) {
		List<?> list = ad.getFlashsByTno(tNo);
		return ut.ListToJson(list, callback);
	}

	/**
	 * 上传视频
	 * 
	 * @param video
	 */
	public void addVideo(Video video) {
		ad.addVideo(video);
	}

	/**
	 * 删除视频
	 * 
	 * @param vno
	 */
	public void delVideo(int vno) {
		ad.delVideo(vno);
	}

	// document
	/**
	 * 读取上传的文件
	 * 
	 * @param callback
	 * @param tNo
	 * @return
	 */
	public String getDocsByTno(String callback, String tNo) {
		List<?> list = ad.getDocsByTno(tNo);
		return ut.ListToJson(list, callback);
	}

	/**
	 * 上传文件
	 * 
	 * @param doc
	 */
	public void addDoc(Document doc) {
		ad.addDoc(doc);
	}

	// question
	/**
	 * 添加题目
	 * 
	 * @param q
	 */
	public int addQuestion(Question q) {
		ad.addQuestion(q);
		return q.getqNo();
	}

	/**
	 * 添加选项
	 * 
	 * @param qNo
	 * @param options
	 */
	public void addOptions(int qNo, String[] options) {
		String[] abc = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
				"L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
				"X", "Y", "Z" };
		for (int i = 0; i < options.length; i++) {
			Option option = new Option();
			option.setoNo(abc[i]);
			option.setqNo(qNo);
			option.setoContent(options[i]);
			ad.addOptions(option);
		}
	}

	/**
	 * 读取题目
	 * 
	 * @param callback
	 * @param qType
	 * @param qDifficulty
	 * @param chaNo
	 * @return
	 */
	public String getQuestions(String callback, String qType,
			String qDifficulty, String chaNo) {
		List<?> list = ad.getQuestions(qType, qDifficulty, chaNo);
		return ut.ListToJson(list, callback);
	}

	/**
	 * 删除题目
	 * 
	 * @param qNo
	 */
	public void delQuestion(int qNo) {
		ad.delQuestion(qNo);
		ad.delOptions(qNo);
	}

	// training
	/**
	 * 读取练习
	 * 
	 * @param callback
	 * @param trainType
	 * @param chaNo
	 * @return
	 */
	public String getTrainings(String callback, String trainType, String chaNo) {
		List<?> list = ad.getTrainings(trainType, chaNo);
		return ut.ListToJson(list, callback);
	}

	// exam
	/**
	 * 读取试卷
	 * 
	 * @param callback
	 * @param tNo
	 * @return
	 */
	public String getExams(String callback, String tNo) {
		List<?> list = ad.getExams(tNo);
		return ut.ListToJson(list, callback);
	}

	/**
	 * 读取需要批改的试卷
	 * 
	 * @param callback
	 * @param tNo
	 * @return
	 */
	public String getExamsNeedCorrect(String callback, String tNo) {
		List<?> list = ad.getExamsNeedCorrect(tNo);
		return ut.ListToJson(list, callback);
	}

	/**
	 * 读取做某张试卷的学生个数
	 * 
	 * @param callback
	 * @param eNo
	 * @return
	 */
	public String getStuDoExamNum(String callback, String eNo) {
		String s = ad.getStuDoExamNum(eNo);
		return s;
	}

	/**
	 * 读取所有学生个数
	 * 
	 * @param callback
	 * @return
	 */
	public String getStuNum(String callback) {
		String s = ad.getStuNum();
		return s;
	}

	/**
	 * 读取已批改的人数
	 * 
	 * @param callback
	 * @param eNo
	 * @return
	 */
	public String getToCorrectNum(String callback, String eNo) {
		String s = ad.getToCorrectNum(eNo);
		return s;
	}

	/**
	 * 读取做某张试卷的学生
	 * 
	 * @param callback
	 * @param eNo
	 * @return
	 */
	public String getFirstSno(String callback, String eNo) {
		String s = ad.getFirstSno(eNo);
		return s;
	}

	/**
	 * 添加试卷
	 * 
	 * @param exam
	 */
	public void addExam(Exam exam) {
		ad.addExams(exam);
	}

	/**
	 * 添加题目
	 * 
	 * @param examQ
	 */
	public void addExamQ(ExamQ examQ) {
		ad.addExamQ(examQ);
	}

	/**
	 * 删除试卷
	 * 
	 * @param eNo
	 */
	public void delExam(String eNo) {
		ad.delExam(eNo);
		ad.delExamQ(eNo);
	}

	/**
	 * 批改试卷
	 * 
	 * @param examsheet
	 */
	public void correctExam(Examsheet examsheet) {
		int sum = 0;
		ad.updateMark(examsheet);
		List<Examsheet> examsheets = qd.getExamsheetList(examsheet);
		for (Examsheet es : examsheets) {
			if (es.getTsIsRight() == -1) {
				sum = -1;
				return;
			} else {
				sum += es.getTsIsRight();
			}
		}
		examsheet.setTsIsRight(sum);
		ad.updateResult(examsheet);
	}

	public String getExamStudents(String callback, String eNo) {
		List<?> list = ad.getExamStudents(eNo);
		return ut.ListToJson(list, callback);
	}

	public String getStudents(String callback) {
		List<?> list = ad.getStudents();
		return ut.ListToJson(list, callback);
	}

	// info
	/**
	 * 获取个人信息
	 * 
	 * @param callback
	 * @param tNo
	 * @return
	 */
	public String getTeacherByTno(String callback, String tNo) {
		List<?> list = ad.getTeacherByTno(tNo);
		return ut.ListToJson(list, callback);
	}

	public String getInfoList(String callback,int etype) {
		List<?> list = null;
		switch(etype) {
		case 1://选择OJ列表
			list = ad.getOJList();
			break;
		case 2://选择视频列表
			list = ad.getVideoList();
			break;
		}
		return ut.ListToJson(list,callback);
	}
	
	public void addNewPreview(String callback,String js,String jsKnowledge,String tno,String claNo) {
		ad.insertNewPreview(tno,claNo);
		int tpid = ad.getLastestPreviewId(tno,claNo);
		JsonArray array = ParseJson.parseJsonArray(js);
		JsonArray knows = ParseJson.parseJsonArray(jsKnowledge);
		for(int i = 0; i < array.size(); ++i) {
			JsonObject subobject = array.get(i).getAsJsonObject();
			if(subobject.get("isonline").getAsInt() == 0) {//线下任务
				ad.insertOutlinePreviewDetail(tpid,subobject.get("trequire").getAsString());
			} else {//线上任务
				ad.insertOnlinePreviewDetail(tpid,
						subobject.get("trequire").getAsString(), 
						subobject.get("require_type").getAsInt(), 
						subobject.get("require_id").getAsInt());
			}
		}
		for(int i = 0; i < knows.size(); ++i) {
			JsonObject object = knows.get(i).getAsJsonObject();
			System.out.println(object.get("rid").getAsInt());
			ad.insertNewPreviewKnowledge(tno, claNo, tpid, object.get("rid").getAsInt());
		}
		ad.broadcastStudentOnlinePreview(claNo);
	}
	
	public String getKnowledgeBychaNo(String callback,int chaNo) {
		List<?> list = ad.getKnowledgeBychaNo(chaNo);
		return ut.ListToJson(list,callback);
	}
	
	@SuppressWarnings("unused")
	public int getOJId() {
		return ad.getOJId();
	}
	
	public void addOjProblem(OJProblem oj) {
		ad.addOjProblem(oj);
	}
	
	public String getOJs(String callback,String chaNo,String qDifficulty) {
		List<?> list = ad.getOJs(chaNo, qDifficulty);
		return ut.ListToJson(list,callback);
	}
	
	public void delOJQuestion(String callback,int trainNo) {
		ad.delOJQuestion(trainNo);
	}
	
	public String getStudentByclaNo(String callback,String claNo) {
		List<?> list = ad.getStudentByclaNo(claNo);
		return ut.ListToJson(list,callback);
	}
	
	public void deleteDcoBydocNo(String callback,String docNo) {
		ad.deleteDcoBydocNo(docNo);
	}
	
	public String getClassByTno(String callback,String tno) {
		List<?> list = ad.getClassByTno(tno);
		return ut.ListToJson(list,callback);
	} 
	
	public void insertClass(String callback,String tno,String name) {
		ad.insertClass(tno,name);
	}
	
	public void addStudentInfo(Workbook workbook) {
		Sheet sheet = workbook.getSheet(0);
		//第0行为表头
		for(int i = 1; i < sheet.getRows(); ++i) {
			String sno = sheet.getCell(0,i).getContents();
			String name = sheet.getCell(1,i).getContents();
			String smajor = sheet.getCell(2,i).getContents();
			String claNo = sheet.getCell(3,i).getContents();
			ad.addStudentInfo(sno, name, smajor, claNo);
		}
	}
	
	public String getAllStudent(String callback) {
		List<?> list = ad.getAllStudent();
		return ut.ListToJson(list,callback);
	}
	
	public void outputAllData(String callback,int chaNo) {
		List<Knowledge> list = ad.getAllKnowledegeInThisChapter(chaNo);
		String path = "/res/data.xls";
		String savePath = CurrentPath.getCurrentPath() + path;
		try {
			WritableWorkbook book = Workbook.createWorkbook(new File(savePath));
			WritableSheet sheet = book.createSheet("student_data", 0);
			String head[] = {"知识点名称","预习前非常熟悉（人数）","预习时比较熟悉（人数）","预习时不太理解（人数）","预习时完全不理解（人数）",
					"课后非常熟悉（人数）","课后比较熟悉（人数）",
					"课后不太理解（人数）","课后完全不理解（人数）"};
			//添加头部信息
			for(int i = 0; i < head.length; ++i) {
				sheet.addCell(new Label(i,0,head[i]));
			}
			//添加实际信息
			int i = 1;
			for(Knowledge know : list) {
				sheet.addCell(new Label(0,i,know.getKnowledge()));
				sheet.addCell(new Label(1,i,
						Integer.toString(know.getPreview_veryHigh())));
				sheet.addCell(new Label(2,i,
						Integer.toString(know.getPreview_High())));
				sheet.addCell(new Label(3,i,
						Integer.toString(know.getPreview_Low())));
				sheet.addCell(new Label(4,i,
						Integer.toString(know.getPreview_veryLow())));
				sheet.addCell(new Label(5,i,
						Integer.toString(know.getClass_veryHigh())));
				sheet.addCell(new Label(6,i,
						Integer.toString(know.getClass_High())));
				sheet.addCell(new Label(7,i,
						Integer.toString(know.getClass_Low())));
				sheet.addCell(new Label(8,i,
						Integer.toString(know.getClass_veryLow())));
				++i;
			}
			book.write();
			book.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void outputPersonData(String callback,String sno,int chaNo) {
		List<Knowledge> list = ad.getPersonData(sno, chaNo);
		String path = "/res/" + sno + "_data.xls";
		String savePath = CurrentPath.getCurrentPath() + path;
		try {
			WritableWorkbook book = Workbook.createWorkbook(new File(savePath));
			WritableSheet sheet = book.createSheet("student_data", 0);
			String head[] = {"知识点名称","预习前非常熟悉","预习时比较熟悉","预习时不太理解","预习时完全不理解",
					"课后非常熟悉","课后比较熟悉",
					"课后不太理解","课后完全不理解"};
			//添加头部信息
			for(int i = 0; i < head.length; ++i) {
				sheet.addCell(new Label(i,0,head[i]));
			}
			//添加实际信息
			int i = 1;
			for(Knowledge know : list) {
				sheet.addCell(new Label(0,i,know.getKnowledge()));
				sheet.addCell(new Label(1,i,
						Integer.toString(know.getPreview_veryHigh())));
				sheet.addCell(new Label(2,i,
						Integer.toString(know.getPreview_High())));
				sheet.addCell(new Label(3,i,
						Integer.toString(know.getPreview_Low())));
				sheet.addCell(new Label(4,i,
						Integer.toString(know.getPreview_veryLow())));
				sheet.addCell(new Label(5,i,
						Integer.toString(know.getClass_veryHigh())));
				sheet.addCell(new Label(6,i,
						Integer.toString(know.getClass_High())));
				sheet.addCell(new Label(7,i,
						Integer.toString(know.getClass_Low())));
				sheet.addCell(new Label(8,i,
						Integer.toString(know.getClass_veryLow())));
				++i;
			}
			book.write();
			book.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addFirstChapter(String callback,String chpName) {
		ad.addFirstChapter(chpName);
	}
	
	public void addNotFirstChapter(String callback,int chaNoLast,String chpName) {
		ad.addNotFirstChapter(chaNoLast, chpName);
	}
	
	public void addKnowledge(String callback,String knowledge,int chpNo) {
		ad.addKnowledge(knowledge, chpNo);
	}
	
	public int getUsualScoreBySno(String callback,String sno) {
		return ad.getUsualScoreBySno(sno);
	}
	
	public void outputStudentData(String callback,String students,String scores) {
		JsonArray stus = ParseJson.parseJsonArray(students);
		JsonArray scs = ParseJson.parseJsonArray(scores);
		String claNo = stus.get(0).getAsJsonObject().get("claNo").getAsString();
		String path = "/res/" + claNo + ".xls";
		String savePath = CurrentPath.getCurrentPath() + path;
		try {
			WritableWorkbook wb = Workbook.createWorkbook(new File(savePath));
			WritableSheet sheet = wb.createSheet(claNo, 0);
			String head[] = {"学号","姓名","专业","班级","平时成绩"};
			try {
				for(int i = 0; i < head.length; ++i)
					sheet.addCell(new Label(i,0,head[i]));
				for(int i = 0; i < stus.size(); ++i) {
					JsonObject ob = stus.get(i).getAsJsonObject();
					sheet.addCell(new Label(0,i + 1,ob.get("sno").getAsString()));
					sheet.addCell(new Label(1,i + 1,ob.get("sname").getAsString()));
					sheet.addCell(new Label(2,i + 1,ob.get("smajor").getAsString()));
					sheet.addCell(new Label(3,i + 1,claNo));
					sheet.addCell(new Label(4,i + 1,scs.get(i).getAsString()));
				}
				wb.write();
				wb.close();
			} catch (RowsExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getKnowledgeName(String callback,int id) {
		List<?> list = ad.getKnowledgeName(id);
		return ut.ListToJson(list,callback);
	}
	
	public String getTeacherOnlyByTno(String callback,String tNo) {
		List<?> list = ad.getTeacherOnlyByTno(tNo);
		return ut.ListToJson(list,callback);
	}
}
