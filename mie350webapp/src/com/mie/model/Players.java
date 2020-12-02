package com.mie.model;

//William Jereza :)
public class Players {
	
	//instance variables
	private int playerID;
	private String username;
	private String password;
	private String name;
	private String position;
	private String height;
	private boolean valid; //for logging in
	//constructor
	public Players (int playerID, String username, String password, String name, String position,String height) {
		this.playerID = playerID;
		this.username = username;
		this.username = password;
		this.name = name;
		this.position = position;
		this.height = height;
	}
	
	//constructor #2 empty argument 
	public Players () {
		//initialize all the variables
		this.playerID = 0;
		this.username = "";
		this.username = "";
		this.name = "";
		this.position = "";
		this.height = "";
	}
	
	// GETTERS AND SETTERS
	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}


	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public void setValid(boolean isValid) { this.valid = isValid;}

	public boolean isValid() {return this.valid;}


	//Override toString()
	@Override
	public String toString() {
		return "Player [playerid=" + playerID + ", username=" + username
				+ ", password=" + password + ", name=" + name + ", position="
				+ position +  ", height=" + height + "]";
	}
	
}