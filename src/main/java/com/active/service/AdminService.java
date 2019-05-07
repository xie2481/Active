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
	 * ��½
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
	 * ��ȡ�����½�
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
	 * ��ȡ�ϴ�����Ƶ
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
	 * ��ȡ�ϴ��Ķ���
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
	 * �ϴ���Ƶ
	 * 
	 * @param video
	 */
	public void addVideo(Video video) {
		ad.addVideo(video);
	}

	/**
	 * ɾ����Ƶ
	 * 
	 * @param vno
	 */
	public void delVideo(int vno) {
		ad.delVideo(vno);
	}

	// document
	/**
	 * ��ȡ�ϴ����ļ�
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
	 * �ϴ��ļ�
	 * 
	 * @param doc
	 */
	public void addDoc(Document doc) {
		ad.addDoc(doc);
	}

	// question
	/**
	 * �����Ŀ
	 * 
	 * @param q
	 */
	public int addQuestion(Question q) {
		ad.addQuestion(q);
		return q.getqNo();
	}

	/**
	 * ���ѡ��
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
	 * ��ȡ��Ŀ
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
	 * ɾ����Ŀ
	 * 
	 * @param qNo
	 */
	public void delQuestion(int qNo) {
		ad.delQuestion(qNo);
		ad.delOptions(qNo);
	}

	// training
	/**
	 * ��ȡ��ϰ
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
	 * ��ȡ�Ծ�
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
	 * ��ȡ��Ҫ���ĵ��Ծ�
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
	 * ��ȡ��ĳ���Ծ��ѧ������
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
	 * ��ȡ����ѧ������
	 * 
	 * @param callback
	 * @return
	 */
	public String getStuNum(String callback) {
		String s = ad.getStuNum();
		return s;
	}

	/**
	 * ��ȡ�����ĵ�����
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
	 * ��ȡ��ĳ���Ծ��ѧ��
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
	 * ����Ծ�
	 * 
	 * @param exam
	 */
	public void addExam(Exam exam) {
		ad.addExams(exam);
	}

	/**
	 * �����Ŀ
	 * 
	 * @param examQ
	 */
	public void addExamQ(ExamQ examQ) {
		ad.addExamQ(examQ);
	}

	/**
	 * ɾ���Ծ�
	 * 
	 * @param eNo
	 */
	public void delExam(String eNo) {
		ad.delExam(eNo);
		ad.delExamQ(eNo);
	}

	/**
	 * �����Ծ�
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
	 * ��ȡ������Ϣ
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
		case 1://ѡ��OJ�б�
			list = ad.getOJList();
			break;
		case 2://ѡ����Ƶ�б�
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
			if(subobject.get("isonline").getAsInt() == 0) {//��������
				ad.insertOutlinePreviewDetail(tpid,subobject.get("trequire").getAsString());
			} else {//��������
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
		//��0��Ϊ��ͷ
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
			String head[] = {"֪ʶ������","Ԥϰǰ�ǳ���Ϥ��������","Ԥϰʱ�Ƚ���Ϥ��������","Ԥϰʱ��̫��⣨������","Ԥϰʱ��ȫ����⣨������",
					"�κ�ǳ���Ϥ��������","�κ�Ƚ���Ϥ��������",
					"�κ�̫��⣨������","�κ���ȫ����⣨������"};
			//���ͷ����Ϣ
			for(int i = 0; i < head.length; ++i) {
				sheet.addCell(new Label(i,0,head[i]));
			}
			//���ʵ����Ϣ
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
			String head[] = {"֪ʶ������","Ԥϰǰ�ǳ���Ϥ","Ԥϰʱ�Ƚ���Ϥ","Ԥϰʱ��̫���","Ԥϰʱ��ȫ�����",
					"�κ�ǳ���Ϥ","�κ�Ƚ���Ϥ",
					"�κ�̫���","�κ���ȫ�����"};
			//���ͷ����Ϣ
			for(int i = 0; i < head.length; ++i) {
				sheet.addCell(new Label(i,0,head[i]));
			}
			//���ʵ����Ϣ
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
			String head[] = {"ѧ��","����","רҵ","�༶","ƽʱ�ɼ�"};
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
