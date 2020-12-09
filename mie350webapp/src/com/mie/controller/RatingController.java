package com.mie.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import com.mie.dao.CourtsDao;
import com.mie.model.Post;

public class RatingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String SOCIAL = "/social.jsp";
	CourtsDao dao = new CourtsDao();
	
	public RatingController() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int courtID = (Integer) request.getSession().getAttribute("courtID");
		int userRating = Integer.parseInt(request.getParameter("userRating"));
		float newRating = dao.updateCourtRating(courtID, userRating);
		HttpSession session = request.getSession(true);
		session.setAttribute("rating", newRating);
		
		RequestDispatcher view = request
				.getRequestDispatcher(SOCIAL);
		Post post = new Post();
		HashMap<Integer,Post> posts = post.getCourtPosts(courtID);
		request.setAttribute("posts", posts);
		view.forward(request, response);
	}
}
