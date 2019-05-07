package com.active.entity;

import java.sql.Timestamp;

public class Feedback {

	private Integer feedNo;
	private String feedTitle;
	private String feedContent;
	private Timestamp feedTime;
	private String sNo;

	public Integer getFeedNo() {
		return feedNo;
	}

	public void setFeedNo(Integer feedNo) {
		this.feedNo = feedNo;
	}

	public String getFeedTitle() {
		return feedTitle;
	}

	public void setFeedTitle(String feedTitle) {
		this.feedTitle = feedTitle;
	}

	public String getFeedContent() {
		return feedContent;
	}

	public void setFeedContent(String feedContent) {
		this.feedContent = feedContent;
	}

	public Timestamp getFeedTime() {
		return feedTime;
	}

	public void setFeedTime(Timestamp feedTime) {
		this.feedTime = feedTime;
	}

	public String getsNo() {
		return sNo;
	}

	public void setsNo(String sNo) {
		this.sNo = sNo;
	}

}
