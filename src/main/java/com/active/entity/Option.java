package com.active.entity;

public class Option {

	private Integer qNo;
	private String oNo;
	private String oContent;
	private Integer oIsAnswer;

	public Integer getqNo() {
		return qNo;
	}

	public void setqNo(Integer qNo) {
		this.qNo = qNo;
	}

	public String getoNo() {
		return oNo;
	}

	public void setoNo(String oNo) {
		this.oNo = oNo;
	}

	public String getoContent() {
		return oContent;
	}

	public void setoContent(String oContent) {
		this.oContent = oContent;
	}

	public Integer getoIsAnswer() {
		return oIsAnswer;
	}

	public void setoIsAnswer(Integer oIsAnswer) {
		this.oIsAnswer = oIsAnswer;
	}

}