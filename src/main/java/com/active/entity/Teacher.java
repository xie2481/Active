package com.active.entity;

/**
 * TeacherId entity. @author MyEclipse Persistence Tools
 */

public class Teacher {

	// Fields

	private String tno;
	private String tpwd;
	private String tname;
	private String tsex;
	private String tschool;
	private String tcollege;
	private String tmajor;
	private String tabout;
	private String previewRequire;
	private String claNo;
	// Constructors

	public String getClaNo() {
		return claNo;
	}

	public void setClaNo(String claNo) {
		this.claNo = claNo;
	}

	public String getPreviewRequire() {
		return previewRequire;
	}

	public void setPreviewRequire(String previewRequire) {
		this.previewRequire = previewRequire;
	}

	/** default constructor */
	public Teacher() {
	}

	/** full constructor */
	public Teacher(String tno, String tpwd, String tname, String tsex,
			String tschool, String tcollege, String tmajor, String tabout) {
		this.tno = tno;
		this.tpwd = tpwd;
		this.tname = tname;
		this.tsex = tsex;
		this.tschool = tschool;
		this.tcollege = tcollege;
		this.tmajor = tmajor;
		this.tabout = tabout;
	}

	// Property accessors

	public String getTno() {
		return this.tno;
	}

	public void setTno(String tno) {
		this.tno = tno;
	}

	public String getTpwd() {
		return this.tpwd;
	}

	public void setTpwd(String tpwd) {
		this.tpwd = tpwd;
	}

	public String getTname() {
		return this.tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTsex() {
		return this.tsex;
	}

	public void setTsex(String tsex) {
		this.tsex = tsex;
	}

	public String getTschool() {
		return this.tschool;
	}

	public void setTschool(String tschool) {
		this.tschool = tschool;
	}

	public String getTcollege() {
		return this.tcollege;
	}

	public void setTcollege(String tcollege) {
		this.tcollege = tcollege;
	}

	public String getTmajor() {
		return this.tmajor;
	}

	public void setTmajor(String tmajor) {
		this.tmajor = tmajor;
	}

	public String getTabout() {
		return this.tabout;
	}

	public void setTabout(String tabout) {
		this.tabout = tabout;
	}

}