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
				String courtName = request.getParameter("courtName");
				session.setAttribute("courtID", courtID);
				session.setAttribute("courtName", courtName);
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
		String courtName = request.getParameter("Court_Name");
		if(courtName != null) {
			Courts court= new Courts();
			court.setCourtID(Integer.parseInt(request.getParameter("CourtID")));
			court.setCourtName(request.getParameter("Court_Name"));
			court.setAddress(request.getParameter("Address"));
			court.setNumberNets(Integer.parseInt(request.getParameter("Number_Nets")));
			court.setDoubleRim(request.getParameter("Double_Rim"));
			court.setRating(Float.parseFloat(request.getParameter("Rating")));
			dao.addCourt(court);
		}
		else {
			int courtID = Integer.parseInt(request.getParameter("CourtID"));
			float rating = Float.parseFloat(request.getParameter("rating"));
			dao.updateCourtRating(courtID, rating);
		}
		/**
		 * Once the court or rating has been added or updated, the page will redirect to
		 * the listing of courts.
		 */
		RequestDispatcher view = request
				.getRequestDispatcher(INDEX);
		request.setAttribute("Courts", dao.getAllCourts());
		view.forward(request, response);
	}

}
