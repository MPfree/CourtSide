package com.mie.model;

public class Court {
	private int courtID;
	private String courtName;
	private String address;
	private int numNets;
	private boolean doubleRim;
	private double rating;
	private int numRatings;
	
	public Court(int courtID, String courtName, String Address, int numNets,
			boolean doubleRim, double rating, int numRatings) {
		this.courtID=courtID;
		this.courtName=courtName;
		this.address=address;
		this.numNets=numNets;
		this.doubleRim=doubleRim;
		this.rating=rating;
		this.numRatings=numRatings;
	}
}
