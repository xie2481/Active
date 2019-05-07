package com.active.entity;

import java.sql.Timestamp;

public class Training {
	private Integer trainNo;
	private String trainName;
	private Integer trainType;
	private Timestamp trainTime;
	private Timestamp trainEndTime;
	private Integer chaNo;
	private Integer cou;

	public Integer getTrainNo() {
		return trainNo;
	}

	public void setTrainNo(Integer trainNo) {
		this.trainNo = trainNo;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public Integer getTrainType() {
		return trainType;
	}

	public void setTrainType(Integer trainType) {
		this.trainType = trainType;
	}

	public Timestamp getTrainTime() {
		return trainTime;
	}

	public void setTrainTime(Timestamp trainTime) {
		this.trainTime = trainTime;
	}

	public Timestamp getTrainEndTime() {
		return trainEndTime;
	}

	public void setTrainEndTime(Timestamp trainEndTime) {
		this.trainEndTime = trainEndTime;
	}

	public Integer getChaNo() {
		return chaNo;
	}

	public void setChaNo(Integer chaNo) {
		this.chaNo = chaNo;
	}

	public Integer getCou() {
		return cou;
	}

	public void setCou(Integer cou) {
		this.cou = cou;
	}

}
