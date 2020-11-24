package com.mie.model;

public class Courts {

	private int CourtID;
	private String Court_Name;
	private String Address;
//	private Date dob;
	private int Number_Nets;
	private int Double_Rim;
	private float Rating;
	private int Number_Ratings;

	public int getCourtID() {
		return CourtID;
	}

	public void setCourtID(int CourtID) {
		this.CourtID = CourtID;
	}

	public String getCourt_Name() {
		return Court_Name;
	}

	public void setCourt_Name(String Court_Name) {
		this.Court_Name = Court_Name;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String Address) {
		this.Address = Address;
	}

	public int getNumber_Nets() {
		return Number_Nets;
	}

	public void setNumber_Nets(int Number_Nets) {
		this.Number_Nets = Number_Nets;
	}

	public int getDouble_Rim() {
		return Double_Rim;
	}

	public void setDouble_Rim(int Double_Rim) {
		this.Double_Rim = Double_Rim;
	}
	public float getRating() {
		return Rating;
	}

	public void setRating(float Rating) {
		this.Rating = Rating;
	}
	
	public float updateRating(float UserRating, float Rating, int Number_Ratings){
		return (((Rating*Number_Ratings)+UserRating)/Number_Ratings+1);
	}
	
	
	public float getNumber_Ratings(int Number_Ratings) {
		return Number_Ratings;
	}

	public void setNumber_Ratings(int Number_Ratings) {
		this.Number_Ratings = Number_Ratings;
	}
	

	@Override
	public String toString() {
		return "Court [ID=" + CourtID + ", Name=" + Court_Name
				+ ", Address=" + Address + ", Number of Nets=" + Number_Nets+ ", Double Rim="
				+ Double_Rim + ", Rating="+ Rating + ", Number of Ratings=" + Number_Ratings + "]";
	}
}
