package com.mie.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.mie.dao.BookingDao;
import com.mie.dao.CourtsDao;
import com.mie.dao.StudentDao;
import com.mie.model.Booking;
import com.mie.model.Courts;
import com.mie.model.Post;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

/**
 * Servlet implementation class MapController
 */
public class MapController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String INDEX = "/index.jsp";
	private static String BOOKING = "/bookings.jsp";
	private Courts court;
	private CourtsDao dao = new CourtsDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MapController() {
        super();
        court = new Courts();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String forward = "";
		String action = request.getParameter("action");
		if(action==null) {
			forward = INDEX;
			ArrayList<Courts> courts = dao.getAllCourts();
			request.setAttribute("courts", courts);
		}
		else {
			if(action.equalsIgnoreCase("select")) {
				HttpSession session = request.getSession(true);
				int courtID = Integer.parseInt(request.getParameter("courtID"));
				int numberNets = Integer.parseInt(request.getParameter("numberNets"));
				String doubleRim = request.getParameter("doubleRim");
				float rating = Float.parseFloat(request.getParameter("rating"));
				String courtName = request.getParameter("courtName");
				session.setAttribute("courtID", courtID);
				session.setAttribute("courtName", courtName);
				session.setAttribute("numberNets", numberNets);
				session.setAttribute("doubleRim", doubleRim);
				session.setAttribute("rating", rating);
				long millis=System.currentTimeMillis();  
		        java.sql.Date today =new java.sql.Date(millis);
		        System.out.println(today);
				BookingDao bookingDao = new BookingDao();
				HashMap<Date, ArrayList<Booking>> bookings = bookingDao.allBookings(courtID, today);
				request.setAttribute("bookings", bookings);
				forward = BOOKING;
			} 
			else if(action.equalsIgnoreCase("get")) {
				forward = INDEX;
				ArrayList<Courts> courts = dao.getAllCourts();
				request.setAttribute("courts", courts);
			}
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String courtName = request.getParameter("courtName");
		Courts court= new Courts();
		court.setCourtName(courtName);
		court.setAddress(request.getParameter("location"));
		court.setNumberNets(Integer.parseInt(request.getParameter("numNets")));
		court.setDoubleRim(request.getParameter("doubleRim"));
		court.setRating(Float.parseFloat(request.getParameter("rating")));
		dao.addCourt(court);

		/**
		 * Once the court or rating has been added or updated, the page will redirect to
		 * the listing of courts.
		 */
		RequestDispatcher view = request
				.getRequestDispatcher(INDEX);
		request.setAttribute("courts", dao.getAllCourts());
		view.forward(request, response);
	}

}
