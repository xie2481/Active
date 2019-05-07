package com.active.entity;

import java.sql.Timestamp;

public class Examresult {

	private String sNo;
	private String eNo;
	private Integer erMark;
	private Timestamp seTime;

	public String getsNo() {
		return sNo;
	}

	public void setsNo(String sNo) {
		this.sNo = sNo;
	}

	public String geteNo() {
		return eNo;
	}

	public void seteNo(String eNo) {
		this.eNo = eNo;
	}

	public Integer getErMark() {
		return erMark;
	}

	public void setErMark(Integer erMark) {
		this.erMark = erMark;
	}

	public Timestamp getSeTime() {
		return seTime;
	}

	public void setSeTime(Timestamp seTime) {
		this.seTime = seTime;
	}

}