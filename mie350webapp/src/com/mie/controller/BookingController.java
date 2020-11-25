package com.mie.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mie.model.*;
import com.mie.dao.*;

//William Jereza :)
public class BookingController extends HTTPServlet {

	private static final long serialVersionUID = 1L;
	private static String INSERT = "/addPost.jsp";
	private static String GET = "/getPosts.jsp";
	
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

		if (action.equalsIgnoreCase("insert")) {
			forward = INSERT; // insert will command to create a new booking
		} else if (action.equalsIgnoreCase("get")) {
			forward = GET; // get will command to retrieve a desired booking
			request.setAttribute("bookings", bookingDao.allBookings(request.getAttribute("courtID"), new Date(request.getDateHeader("sign_up_date"))));
		} else {
			;
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
		Booking newBooking = new Booking(request.getAttribute("playerID"), request.getAttribute("courtID"), new Date(request.getDateHeader("sign_up_date")), 
			new Date(request.getDateHeader("sign_up_time")), request.getAttribute("team_size"),request.getParameter("description"));
		try {
			bookingDao.addBooking(newBooking);
			// create booking.jsp
			response.sendRedirect("booking.jsp");
			
		} catch (ParseException e) {
			e.printStackTrace();
		}


	}
	
}
