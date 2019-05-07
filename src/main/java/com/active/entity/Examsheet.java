package com.active.entity;

public class Examsheet {

	private String eNo;
	private String sNo;
	private Integer qNo;
	private String qAnswer;
	private Integer tsIsRight;

	public Examsheet() {
	}

	public Examsheet(String eNo, String sNo, Integer qNo, String qAnswer,
			Integer tsIsRight) {
		super();
		this.eNo = eNo;
		this.sNo = sNo;
		this.qNo = qNo;
		this.qAnswer = qAnswer;
		this.tsIsRight = tsIsRight;
	}

	public String geteNo() {
		return eNo;
	}

	public void seteNo(String eNo) {
		this.eNo = eNo;
	}

	public String getsNo() {
		return sNo;
	}

	public void setsNo(String sNo) {
		this.sNo = sNo;
	}

	public Integer getqNo() {
		return qNo;
	}

	public void setqNo(Integer qNo) {
		this.qNo = qNo;
	}

	public String getqAnswer() {
		return qAnswer;
	}

	public void setqAnswer(String qAnswer) {
		this.qAnswer = qAnswer;
	}

	public Integer getTsIsRight() {
		return tsIsRight;
	}

	public void setTsIsRight(Integer tsIsRight) {
		this.tsIsRight = tsIsRight;
	}

}