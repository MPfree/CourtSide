package com.mie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mie.model.Courts;
import com.mie.util.DbUtil;

public class CourtsDao {

	private Connection connection;

	public CourtsDao() {
		connection = DbUtil.getConnection();
	}

	public void addCourt(Courts Court) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into Courts(CourtID,Court_Name,Address,Number_Nets,Double_Rim, Rating"
							+ ") values (?, ?, ?, ?, ?, ?)");
			// Parameters start with 1
			preparedStatement.setInt(1, generateCourtID());
			preparedStatement.setString(2, Court.getCourtName());
			preparedStatement.setString(3, Court.getAddress());
			preparedStatement.setInt(4, Court.getNumberNets());
			preparedStatement.setString(5, Court.getDoubleRim());
			preparedStatement.setFloat(6, Court.getRating());
			
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int generateCourtID() {
		try {
			
			String generateIDQuery = "select count(CourtID) from Courts";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(generateIDQuery);
			rs.next();
			int numCourts = rs.getInt(1);
			return numCourts+1;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void deleteCourt(int CourtID) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("delete from Courts where CourtID=?");
			// Parameters start with 1
			preparedStatement.setInt(1, CourtID);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Doesn't take average of all ratings but only puts most recent one
	public void addCourtRating(int RCourtID, float UserRating) {
		/*try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select Rating from Courts where CourtID=?");
			new_rating= old_rating= Courts.get
			*/
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update Courts set Rating=?"+ " where CourtID=?");
			preparedStatement.setFloat(1, UserRating);
			preparedStatement.setInt(2, RCourtID);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public float updateCourtRating(int RCourtID, float UserRating) {
		float newRating = UserRating;
		try {
			
		Statement statement = connection.createStatement();
		
		PreparedStatement preparedStatement = connection
				.prepareStatement("select Rating from Courts where CourtID=?");
		preparedStatement.setInt(1, RCourtID);
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		
		float CurrentRating = rs.getFloat("Rating");
		
		PreparedStatement preparedStatement1 = connection
				.prepareStatement("select Number_Ratings from Courts where CourtID=?");
		preparedStatement1.setInt(1, RCourtID);
		ResultSet rs1 = preparedStatement1.executeQuery();
		rs1.next();
		
		int Number_Ratings = rs1.getInt("Number_Ratings");
		PreparedStatement preparedStatement2 = connection
				.prepareStatement("update Courts set Rating=?"+ " where CourtID=?");
		Courts courts = new Courts();
		newRating = courts.updateRating(UserRating, CurrentRating,Number_Ratings);
		preparedStatement2.setFloat(1,newRating);
		preparedStatement2.setInt(2, RCourtID);
		preparedStatement2.executeUpdate();
		PreparedStatement preparedStatement3 = connection.prepareStatement
				("update Courts set Number_Ratings=?"+ " where CourtID=?");
		preparedStatement3.setInt(1,Number_Ratings+1);
		preparedStatement3.setInt(2, RCourtID);
		preparedStatement3.executeUpdate();
		return newRating;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newRating;
	}
		
	public ArrayList<Courts> getAllCourts() {
		ArrayList<Courts> Courts = new ArrayList<Courts>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from Courts");
			while (rs.next()) {
				Courts Court = new Courts();
				Court.setCourtID(rs.getInt("CourtID"));
				Court.setCourtName(rs.getString("Court_Name"));
				Court.setAddress(rs.getString("Address"));
				Court.setNumberNets(rs.getInt("Number_Nets"));
				Court.setDoubleRim(rs.getString("Double_Rim"));
				Court.setRating(rs.getFloat("Rating"));
				Courts.add(Court);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Courts;
	}
	
	
	public Courts getCourtById(int CourtID) {
		Courts Court = new Courts();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from users where CourtID=?");
			preparedStatement.setInt(1, CourtID);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				Court.setCourtID(rs.getInt("userid"));
				Court.setCourtName(rs.getString("Court Name"));
				Court.setAddress(rs.getString("Address"));
				Court.setNumberNets(rs.getInt("Number of Nets"));
				Court.setDoubleRim(rs.getString("Double RIm"));
				Court.setRating(rs.getFloat("Rating"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Court;
	}
	

	public List<Courts> getCourtsByKeyword(String keyword) {
		List<Courts> courts = new ArrayList<Courts>();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from Courts where CourtID LIKE ? OR Court_Name LIKE ? OR Address LIKE ? "
							+ "OR Number_Nets LIKE ? OR Double_Rim LIKE ? OR Rating LIKE ? OR Number_Ratings LIKE ?");
			
//			SELECT title FROM pages WHERE my_col LIKE %$param1% OR another_col LIKE %$param2%;
			
			preparedStatement.setString(1, "%" + keyword + "%");
			preparedStatement.setString(2, "%" + keyword + "%");
			preparedStatement.setString(3, "%" + keyword + "%");
			preparedStatement.setString(4, "%" + keyword + "%");
			preparedStatement.setString(5, "%" + keyword + "%");
			preparedStatement.setString(6, "%" + keyword + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Courts court = new Courts();
				court.setCourtID(rs.getInt("CourtID"));
				court.setCourtName(rs.getString("Court_Name"));
				court.setAddress(rs.getString("Address"));
				court.setNumberNets(rs.getInt("Number_Nets"));
				court.setDoubleRim(rs.getString("Double_Rim"));
				court.setRating(rs.getFloat("Rating"));
				court.setNumberRatings(rs.getInt("Number_Ratings"));
				courts.add(court);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return courts;
	}
}

