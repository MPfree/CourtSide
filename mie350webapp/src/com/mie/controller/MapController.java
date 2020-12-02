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
	private static String MAPS = "/mapspage.jsp";
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
		String forward = MAPS;
		request.setAttribute("Courts", dao.getAllCourts());

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
			court.setCourt_Name(request.getParameter("Court_Name"));
			court.setAddress(request.getParameter("Address"));
			court.setNumber_Nets(Integer.parseInt(request.getParameter("Number_Nets")));
			court.setDouble_Rim(Integer.parseInt(request.getParameter("Double_Rim")));
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
				.getRequestDispatcher(MAPS);
		request.setAttribute("Courts", dao.getAllCourts());
		view.forward(request, response);
	}

}
