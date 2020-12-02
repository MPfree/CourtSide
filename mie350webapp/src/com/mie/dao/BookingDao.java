package com.mie.dao;

import java.sql.Connection;
import org.apache.commons.lang.time.DateUtils;
import java.sql.Date;
import java.sql.Time;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mie.model.Booking;
import com.mie.util.DbUtil;

//William Jereza
public class BookingDao {

	private Connection connection;

	public BookingDao() {
		connection = DbUtil.getConnection();
	}
	
	//add a booking
	public void addBooking(Booking booking) {
		try {
			PreparedStatement ps = connection.prepareStatement("insert into Booking("
					+ "PlayerID, CourtID, Sign_Up_Time, Sign_Up_Date, Team_Size, Description)"
					+ " values (?,?,?,?,?,? )");
			ps.setInt(1, booking.getPlayerID());
			ps.setInt(2, booking.getcourtID());
			ps.setDate(3, booking.getsignupDate());
			ps.setTime(4, booking.getsignupTime());
			ps.setInt(5, booking.getteamSize());
			ps.setString(6, booking.getDescription());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//delete a booking
	public void deleteBooking(Booking booking) {
		try {
			PreparedStatement ps = connection
					.prepareStatement("delete from players where playerid=?, sign_up_date=?, sign_up_time=?");
			// Parameters start with 1
			ps.setInt(1, booking.getPlayerID());
			ps.setDate(2, booking.getsignupDate());
			ps.setTime(3, booking.getsignupTime());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//helper functions that converts the date [VERY JANK I AM SORRY]
	public Date yesterday(Date sqlDate) {
		java.util.Date yesterdayDate = DateUtils.addDays(sqlDate, -1);
		Date sqlYesterdayDate = new Date(yesterdayDate.getYear(), yesterdayDate.getMonth(), yesterdayDate.getDay());
		return sqlYesterdayDate;	
	}
	//[ALSO VERY JANKY]
	public Date tomorrow(Date sqlDate) {
		java.util.Date tomorrowDate = DateUtils.addDays(sqlDate, 1);
		Date sqlTomorrowDate = new Date(tomorrowDate.getYear(), tomorrowDate.getMonth(), tomorrowDate.getDay());
		return sqlTomorrowDate;		
	}
	
	
	//API that will display the status of all the bookings given the courtID and date
	public HashMap<Date,List<Booking>> allBookings(int courtID, Date date) {
		
		//A hashmap where the key is the date, and the values are all the bookings for that date
		HashMap<Date,List<Booking>> courtBookings = new HashMap<Date,List<Booking>>();
		
		//we must iterate three times to get the bookings for yesterday, today, and tomorrow
		for (int i=0; i<3; i++) {
			
			try{
				
				List<Booking> bookings = new ArrayList<Booking>();
				Date iterationDate;
				PreparedStatement statement = connection.prepareStatement("select * from Booking where CourtID=?, Sign_Up_Date=? ORDER BY Sign_Up_Time");    
				statement.setInt(1, courtID);
				
				//if statement for the dates
				if (i==0) { //today
					iterationDate = date;
					statement.setDate(2, iterationDate);
				}
				else if (i==1) { //yesterday
					iterationDate = yesterday(date);
					statement.setDate(2, yesterday(iterationDate));
				}
				else { //tomorrow (i==2)
					iterationDate = tomorrow(date);
					statement.setDate(2, tomorrow(iterationDate));
				}
				
				//execute query
				ResultSet rs = statement.executeQuery();
				
				//add each booking to the list of bookings for given date
				while (rs.next()) {
					Booking booking = new Booking ();
					booking.setPlayerID(rs.getInt("playerid"));
					booking.setcourtID(rs.getInt("courtid"));
					booking.setsignupDate(rs.getDate("sign_up_date"));
					booking.setsignupTime(rs.getTime("sign_up_time"));
					booking.setteamSize(rs.getInt("team_size"));
					booking.setDescription(rs.getString("description"));
					bookings.add(booking);
					
					//FINALLY append to courtBookings
					bookings.add(booking);
				}
				courtBookings.put(iterationDate, bookings);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return courtBookings;
		
	}
	
}