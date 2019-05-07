package com.active.entity;

public class Preview {
	private int id;
	private String sno;
	private int tpdid;
	private String sstate;
	private String trequire;
	private int require_type;
	private int require_id;
	private int isonline;
	public int getIsonline() {
		return isonline;
	}
	public void setIsonline(int isonline) {
		this.isonline = isonline;
	}
	public int getRequire_type() {
		return require_type;
	}
	public void setRequire_type(int require_type) {
		this.require_type = require_type;
	}
	public int getRequire_id() {
		return require_id;
	}
	public void setRequire_id(int require_id) {
		this.require_id = require_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public int getTpdid() {
		return tpdid;
	}
	public void setTpdid(int tpdid) {
		this.tpdid = tpdid;
	}
	public String getSstate() {
		return sstate;
	}
	public void setSstate(String sstate) {
		this.sstate = sstate;
	}
	public String getTrequire() {
		return trequire;
	}
	public void setTrequire(String trequire) {
		this.trequire = trequire;
	}
}
