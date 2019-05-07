package com.active.entity;

/**
 * ChapterId entity. @author MyEclipse Persistence Tools
 */

public class Chapter {

	// Fields

	private Integer chaNo;
	private String chaName;
	private Integer chaNoLast;

	// Property accessors

	public Integer getChaNo() {
		return this.chaNo;
	}

	public void setChaNo(Integer chaNo) {
		this.chaNo = chaNo;
	}

	public String getChaName() {
		return this.chaName;
	}

	public void setChaName(String chaName) {
		this.chaName = chaName;
	}

	public Integer getChaNoLast() {
		return this.chaNoLast;
	}

	public void setChaNoLast(Integer chaNoLast) {
		this.chaNoLast = chaNoLast;
	}

}