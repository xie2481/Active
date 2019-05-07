package com.active.entity;

public class FeedbackCache {
	private String SNo;
	private int QNo;
	private String QAnswer;
	private int hash;
	public String getSNo() {
		return SNo;
	}
	public void setSNo(String sNo) {
		SNo = sNo;
	}
	public int getQNo() {
		return QNo;
	}
	public void setQNo(int qNo) {
		QNo = qNo;
	}
	public String getQAnswer() {
		return QAnswer;
	}
	public void setQAnswer(String qAnswer) {
		QAnswer = qAnswer;
	}
	public int getHash() {
		return hash;
	}
	public void setHash(int hash) {
		this.hash = hash;
	}
	
}
