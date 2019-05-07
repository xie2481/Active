package com.active.entity;

import java.sql.Timestamp;

public class Video {

	private String vno;
	private String vname;
	private String vintroduce;
	private Integer vtype;
	private Integer chano;
	private String vrange;
	private Timestamp vtime;
	private String tno;
	private Integer vcount;
	private String vaddress;
	private String vimg;
	private String chaName;
	
	public String getChaName() {
		return chaName;
	}

	public void setChaName(String chaName) {
		this.chaName = chaName;
	}

	public String getVno() {
		return vno;
	}

	public void setVno(String vno) {
		this.vno = vno;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public String getVintroduce() {
		return vintroduce;
	}

	public void setVintroduce(String vintroduce) {
		this.vintroduce = vintroduce;
	}

	public Integer getVtype() {
		return vtype;
	}

	public void setVtype(Integer vtype) {
		this.vtype = vtype;
	}

	public Integer getChano() {
		return chano;
	}

	public void setChano(Integer chano) {
		this.chano = chano;
	}

	public String getVrange() {
		return vrange;
	}

	public void setVrange(String vrange) {
		this.vrange = vrange;
	}

	public Timestamp getVtime() {
		return vtime;
	}

	public void setVtime(Timestamp vtime) {
		this.vtime = vtime;
	}

	public String getTno() {
		return tno;
	}

	public void setTno(String tno) {
		this.tno = tno;
	}

	public Integer getVcount() {
		return vcount;
	}

	public void setVcount(Integer vcount) {
		this.vcount = vcount;
	}

	public String getVaddress() {
		return vaddress;
	}

	public void setVaddress(String vaddress) {
		this.vaddress = vaddress;
	}

	public String getVimg() {
		return vimg;
	}

	public void setVimg(String vimg) {
		this.vimg = vimg;
	}

}