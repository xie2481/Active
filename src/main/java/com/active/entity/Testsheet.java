package com.active.entity;

public class Testsheet {

	private String sNo;
	private Integer qNo;
	private Integer trainNo;
	private String qAnswer;
	private Integer tsIsRight;

	public Testsheet() {
	}

	public Testsheet(String sNo, Integer qNo, Integer trainNo, String qAnswer,
			Integer tsIsRight) {
		super();
		this.sNo = sNo;
		this.qNo = qNo;
		this.trainNo = trainNo;
		this.qAnswer = qAnswer;
		this.tsIsRight = tsIsRight;
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

	public Integer getTrainNo() {
		return trainNo;
	}

	public void setTrainNo(Integer trainNo) {
		this.trainNo = trainNo;
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