package com.mie.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mie.dao.CourtsDao;
import com.mie.dao.StudentDao;
import com.mie.model.Courts;
import com.mie.model.Post;

/**
 * Servlet implementation class MapController
 */
public class MapController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String ADDC = "/addCourt.jsp";
	private static String UPDATE = "/updateRating.jsp";
	private static String AllCourts = "/allCourts.jsp";
	
	///private CourtsDao Cdao;
	private Courts court;
	private CourtsDao dao;
       
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
		String forward = AllCourts;
		String action = request.getParameter("action");
		request.setAttribute("Courts", dao.getAllCourts());

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Courts court= new Courts();
		court.setCourt_Name(request.getParameter("Court_Name"));
		court.setAddress(request.getParameter("Address"));
		court.setNumber_Nets(request.getParameter("Number_Nets"));
		court.setDouble_Rim(request.getParameter("Double_Rim"));
		court.setRating(request.getParameter("Rating"));
		
		String CourtID = request.getParameter("CourtID");
		
		if (CourtID == null || CourtID.isEmpty()) {
			dao.addCourt(court);
		} else {
			/**
			 * Otherwise, if the field is already filled (this occurs when the
			 * user is trying to Edit A Student), then the student's information
			 * will be updated accordingly.
			 */
			court.setCourtID(Integer.parseInt(CourtID));
			dao.updateCourtRating(court);
		}
		/**
		 * Once the student has been added or updated, the page will redirect to
		 * the listing of students.
		 */
		RequestDispatcher view = request
				.getRequestDispatcher(ADDC);
		request.setAttribute("Courts", dao.getAllCourts());
		view.forward(request, response);
	}

}
