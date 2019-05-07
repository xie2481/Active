package com.active.entity;

/**
 * StudentId entity. @author MyEclipse Persistence Tools
 */

public class Student {

	// Fields

	private String sno;
	private String spwd;
	private String sname;
	private String ssex;
	private String scollege;
	private String smajor;
	private String claNo;

	// Constructors

	/** default constructor */
	public Student() {
	}

	/** full constructor */
	public Student(String sno, String spwd, String sname, String ssex,
			String scollege, String smajor, String claNo) {
		this.sno = sno;
		this.spwd = spwd;
		this.sname = sname;
		this.ssex = ssex;
		this.scollege = scollege;
		this.smajor = smajor;
		this.claNo = claNo;
	}

	// Property accessors

	public String getSno() {
		return this.sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getSpwd() {
		return this.spwd;
	}

	public void setSpwd(String spwd) {
		this.spwd = spwd;
	}

	public String getSname() {
		return this.sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSsex() {
		return this.ssex;
	}

	public void setSsex(String ssex) {
		this.ssex = ssex;
	}

	public String getScollege() {
		return this.scollege;
	}

	public void setScollege(String scollege) {
		this.scollege = scollege;
	}

	public String getSmajor() {
		return this.smajor;
	}

	public void setSmajor(String smajor) {
		this.smajor = smajor;
	}

	public String getClaNo() {
		return this.claNo;
	}

	public void setClaNo(String claNo) {
		this.claNo = claNo;
	}

}