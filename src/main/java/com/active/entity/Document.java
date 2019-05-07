package com.active.entity;

import java.sql.Timestamp;

public class Document {

	private int docNo;
	private String docName;
	private Timestamp docTime;
	private String tno;
	private String tname;
	private String docPath;
	private String docType;
	private Integer chano;

	public Integer getChano() {
		return chano;
	}

	public void setChano(Integer chano) {
		this.chano = chano;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public int getDocNo() {
		return docNo;
	}

	public void setDocNo(int docNo) {
		this.docNo = docNo;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public Timestamp getDocTime() {
		return docTime;
	}

	public void setDocTime(Timestamp docTime) {
		this.docTime = docTime;
	}

	public String getTno() {
		return tno;
	}

	public void setTno(String tno) {
		this.tno = tno;
	}

	public String getDocPath() {
		return docPath;
	}

	public void setDocPath(String docPath) {
		this.docPath = docPath;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

}