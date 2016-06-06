package com.prewarningapp.model;

public class Board {

	/** id唯一序列号 **/
	private int id;
	/** 模块的id **/
	private int boardID;
	/** 模块的状态，0为按下，1为弹起 **/
	private int state;
	/** 模块所在的厂区-int型 **/
	private int position;
	/** 模块的所在的X轴坐标 **/
	private int posX;
	/** 模块的所在的Y轴坐标 **/
	private int posY;
	/** 模块的所在的厂区 **/
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
