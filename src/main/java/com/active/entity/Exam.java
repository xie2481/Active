package com.active.entity;

import java.sql.Timestamp;

public class Exam {

	private String eNo;
	private String eName;
	private String tNo;
	private String claNo;
	private Timestamp eTime;
	private Timestamp eBeginTime;
	private Timestamp eEndTime;
	private Integer eDuration;
	private Integer eMark;
	private Integer cou;

	public Integer getCou() {
		return cou;
	}

	public void setCou(Integer cou) {
		this.cou = cou;
	}

	public String geteNo() {
		return eNo;
	}

	public void seteNo(String eNo) {
		this.eNo = eNo;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public String gettNo() {
		return tNo;
	}

	public void settNo(String tNo) {
		this.tNo = tNo;
	}

	public String getClaNo() {
		return claNo;
	}

	public void setClaNo(String claNo) {
		this.claNo = claNo;
	}

	public Timestamp geteTime() {
		return eTime;
	}

	public void seteTime(Timestamp eTime) {
		this.eTime = eTime;
	}

	public Timestamp geteBeginTime() {
		return eBeginTime;
	}

	public void seteBeginTime(Timestamp eBeginTime) {
		this.eBeginTime = eBeginTime;
	}

	public Timestamp geteEndTime() {
		return eEndTime;
	}

	public void seteEndTime(Timestamp eEndTime) {
		this.eEndTime = eEndTime;
	}

	public Integer geteDuration() {
		return eDuration;
	}

	public void seteDuration(Integer eDuration) {
		this.eDuration = eDuration;
	}

	public Integer geteMark() {
		return eMark;
	}

	public void seteMark(Integer eMark) {
		this.eMark = eMark;
	}

}