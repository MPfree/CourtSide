package com.mie.model;

public class Players {
	
	private int playerID;
	private String username;
	private String password;
	private String name;
	private String position;
	private String height;
	// private image profilePic; not sure how to import images in java
	
	public Players(int playerID, String username, String password, String name,
			String position, String height, image profilePic) {
		this.playerID=playerID;
		this.username=username;
		this.password=password;
		this.position=position;
		this.name=name;
		this.height=height;
//		this.profilePic=profilePic;
	}
			

}
