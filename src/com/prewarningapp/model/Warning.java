package com.prewarningapp.model;

public class Warning {
	/** id唯一序列号 **/
	private int id;
	/** 报警地点 **/
	private String warningPlace;
	/** 报警时间 **/
	private String warningTime;
	/** 模块的id **/
	private String boardID;
	/** 报警类型 **/
	private String warningType;
	/** 报警处理情况1为已处理，0为未处理 **/
	private int warningHandle;
	/** 报警处理时间 **/
	private String warningHTime;
	/** 报警处理人 **/
	private String warningHPeople;
	public int getId() {
		return id;
	}
	public String getBoardID() {
		return boardID;
	}
	public void setBoardID(String boardID) {
		this.boardID = boardID;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWarningPlace() {
		return warningPlace;
	}
	public void setWarningPlace(String warningPlace) {
		this.warningPlace = warningPlace;
	}
	public String getWarningTime() {
		return warningTime;
	}
	public void setWarningTime(String warningTime) {
		this.warningTime = warningTime;
	}
	public String getWarningType() {
		return warningType;
	}
	public void setWarningType(String warningType) {
		this.warningType = warningType;
	}
	public int getWarningHandle() {
		return warningHandle;
	}
	public void setWarningHandle(int warningHandle) {
		this.warningHandle = warningHandle;
	}
	public String getWarningHTime() {
		return warningHTime;
	}
	public void setWarningHTime(String warningHTime) {
		this.warningHTime = warningHTime;
	}
	public String getWarningHPeople() {
		return warningHPeople;
	}
	public void setWarningHPeople(String warningHPeople) {
		this.warningHPeople = warningHPeople;
	}
	
}
