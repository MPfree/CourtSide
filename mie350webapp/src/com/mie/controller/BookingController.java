package com.mie.controller;

import java.io.IOException;
import org.apache.commons.lang.time.DateUtils;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mie.model.*;
import com.mie.dao.*;

//William Jereza :)
public class BookingController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static String BOOKING = "bookings.jsp";
	
	private Booking booking;
	private BookingDao bookingDao;
	
	//constructor
	public BookingController() {
		super();
		bookingDao = new BookingDao();
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		/**
		 * This class retrieves the appropriate 'action' found on the JSP pages:
		 * 
		 * 
		 */
		String forward = "";
		String action = request.getParameter("action");

		if (action.equalsIgnoreCase("get")) {
			forward = BOOKING; // get will command to retrieve a desired booking
			request.setAttribute("bookings", bookingDao.allBookings(Integer.parseInt(request.getParameter("courtID")), new Date(request.getDateHeader("sign_up_date"))));
		}

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		/**
		 * This method retrieves all of the information entered in the form on
		 * the addPost.jsp page.
		 */
		// This code below should create a post object from the JSP info, only ever used by addPost.jsp
		
		// Problem below: all request.getParam values return strings
		Players player = (Players)request.getSession().getAttribute("currentSessionPlayer");
		int courtID = (Integer) request.getSession().getAttribute("courtID");
		int playerID = player.getPlayerID();
		Date signUpDate = Date.valueOf(request.getParameter("sign_up_date"));
		String time = request.getParameter("sign_up_time");
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
		long ms = 0;
		try {
			ms = sdf.parse(time).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Time signUpTime = new Time(ms);
		System.out.println(signUpTime);
		System.out.println(signUpDate);
		int teamSize = Integer.parseInt(request.getParameter("size"));
		String description = request.getParameter("description");
		Booking newBooking = new Booking(playerID, courtID, signUpDate, signUpTime, 
				teamSize, description);
		bookingDao.addBooking(newBooking);
		Date today = new Date(Calendar.getInstance().getTime().getTime());
		LinkedHashMap<Date,ArrayList<Booking>> bookings = bookingDao.allBookings(courtID, today);
		RequestDispatcher view = request
				.getRequestDispatcher(BOOKING);

		request.setAttribute("bookings", bookings);
		view.forward(request, response);
		
			



	}
	
}
