package com.mie.model;

import java.sql.Date;

//William Jereza :)
public class Booking {
	
	private int playerID;
	private int courtID;
	private Date signupDate;
	private Date signupTime;
	private int teamSize;
	private String description;
	
	//constructor
	public Booking(int playerID, int courtID, Date signupDate, Date signupTime, int teamSize, String description) {
		this.playerID = playerID;
		this.courtID = courtID;
		this.signupDate = signupDate;
		this.signupTime = signupTime;
		this.teamSize = teamSize;
		this.description = description;
		
	}
	
	//constructor #2 - empty 
	public Booking() {
		this.playerID = 0;
		this.courtID = 0;
		this.signupDate = null;
		this.signupTime = null;
		this.teamSize = 0;
		this.description = "";
		
	}
	
	// GETTERS AND SETTERS
	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	
	public int getcourtID() {
		return courtID;
	}

	public void setcourtID(int courtID) {
		this.courtID = courtID;
	}

	public Date getsignupDate() {
		return signupDate;
	}

	public void setsignupDate(Date signupDate) {
		this.signupDate = signupDate;
	}
	
	public Date getsignupTime() {
		return signupTime;
	}

	public void setsignupTime(Date signupTime) {
		this.signupTime = signupTime;
	}

	public int getteamSize() {
		return teamSize;
	}

	public void setteamSize(int teamSize) {
		this.teamSize = teamSize;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	//Override toString()
	@Override
	public String toString() {
		return "Booking [playerid=" + playerID + ", courtid=" + courtID
				+ ", signupDate=" + signupDate + ", signupTime=" + signupTime + ", teamSize="
				+ teamSize +  ", description=" + description + "]";
	}
		
}