package com.active.entity;

/**
 * CourseId entity. @author MyEclipse Persistence Tools
 */

public class Course {

	// Fields

	private String tno;
	private String cno;
	private String couName;

	// Constructors

	/** default constructor */
	public Course() {
	}

	/** full constructor */
	public Course(String tno, String cno, String couName) {
		this.tno = tno;
		this.cno = cno;
		this.couName = couName;
	}

	// Property accessors

	public String getTno() {
		return this.tno;
	}

	public void setTno(String tno) {
		this.tno = tno;
	}

	public String getCno() {
		return this.cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public String getCouName() {
		return this.couName;
	}

	public void setCouName(String couName) {
		this.couName = couName;
	}

}