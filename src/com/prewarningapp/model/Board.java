package com.prewarningapp.model;

public class Board {

	/** idΨһ���к� **/
	private int id;
	/** ģ���id **/
	private int boardID;
	/** ģ���״̬��0Ϊ���£�1Ϊ���� **/
	private int state;
	/** ģ�����ڵĳ���-int�� **/
	private int position;
	/** ģ������ڵ�X������ **/
	private int posX;
	/** ģ������ڵ�Y������ **/
	private int posY;
	/** ģ������ڵĳ��� **/
	private String place;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBoardID() {
		return boardID;
	}
	public void setBoardID(int boardID) {
		this.boardID = boardID;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	
}
