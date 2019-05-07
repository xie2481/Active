package com.active.entity;

public class Question {

	private Integer qNo;
	private String qTitle;
	private Integer qType;
	private Integer chaNo;
	private Integer qDifficulty;
	private String qRange;
	private String qAnswer;
	private int qKnowledge;
	
	public int getqKnowledge() {
		return qKnowledge;
	}

	public void setqKnowledge(int qKnowledge) {
		this.qKnowledge = qKnowledge;
	}

	public Integer getqNo() {
		return qNo;
	}

	public void setqNo(Integer qNo) {
		this.qNo = qNo;
	}

	public String getqTitle() {
		return qTitle;
	}

	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
	}

	public Integer getqType() {
		return qType;
	}

	public void setqType(Integer qType) {
		this.qType = qType;
	}

	public Integer getChaNo() {
		return chaNo;
	}

	public void setChaNo(Integer chaNo) {
		this.chaNo = chaNo;
	}

	public Integer getqDifficulty() {
		return qDifficulty;
	}

	public void setqDifficulty(Integer qDifficulty) {
		this.qDifficulty = qDifficulty;
	}

	public String getqRange() {
		return qRange;
	}

	public void setqRange(String qRange) {
		this.qRange = qRange;
	}

	public String getqAnswer() {
		return qAnswer;
	}

	public void setqAnswer(String qAnswer) {
		this.qAnswer = qAnswer;
	}

}