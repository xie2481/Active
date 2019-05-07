package com.active.entity;

public class OJProblem {
	private int id;
	private String title;
	private int chaNo;
	private String chaName;
	private int difficulty;
	private int knowledgeId;
	public int getKnowledgeId() {
		return knowledgeId;
	}
	public void setKnowledgeId(int knowledgeId) {
		this.knowledgeId = knowledgeId;
	}
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	public String getChaName() {
		return chaName;
	}
	public void setChaName(String chaName) {
		this.chaName = chaName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getChaNo() {
		return chaNo;
	}
	public void setChaNo(int chaNo) {
		this.chaNo = chaNo;
	}
}
