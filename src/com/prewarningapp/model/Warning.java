package com.prewarningapp.model;

public class Warning {
	/** idΨһ���к� **/
	private int id;
	/** �����ص� **/
	private String warningPlace;
	/** ����ʱ�� **/
	private String warningTime;
	/** ģ���id **/
	private String boardID;
	/** �������� **/
	private String warningType;
	/** �����������1Ϊ�Ѵ���0Ϊδ���� **/
	private int warningHandle;
	/** ��������ʱ�� **/
	private String warningHTime;
	/** ���������� **/
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
