package com.mie.model;

public class Courts {

	private int courtID;
	private String courtName;
	private String address;
//	private Date dob;
	private int numberNets;
	private String doubleRim;
	private float rating;
	private int numberRatings;

	public int getCourtID() {
		return courtID;
	}

	public void setCourtID(int courtID) {
		this.courtID = courtID;
	}

	public String getCourtName() {
		return courtName;
	}

	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getNumberNets() {
		return numberNets;
	}

	public void setNumberNets(int numberNets) {
		this.numberNets = numberNets;
	}

	public String getDoubleRim() {
		return doubleRim;
	}

	public void setDoubleRim(String doubleRim) {
		this.doubleRim = doubleRim;
	}
	public float getRating() {
		return rating;
	}

	public void setRating(float Rating) {
		this.rating = Rating;
	}
	
	public float updateRating(float UserRating, float Rating, int Number_Ratings){
		return (((Rating*Number_Ratings)+UserRating)/(Number_Ratings+1));
	}
	
	
	public float getNumberRatings(int numberRatings) {
		return numberRatings;
	}

	public void setNumberRatings(int numberRatings) {
		this.numberRatings = numberRatings;
	}
	

	@Override
	public String toString() {
		return "Court [ID=" + courtID + ", Name=" + courtName
				+ ", Address=" + address + ", Number of Nets=" + numberNets+ ", Double Rim="
				+ doubleRim + ", Rating="+ rating + ", Number of Ratings=" + numberRatings + "]";
	}
}
