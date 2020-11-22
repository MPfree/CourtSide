package com.mie.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mie.model.*;

public class PostController extends HTTPServlet {
	
	private static final long serialVersionUID = 1L;
	private static String INSERT = "/addPost.jsp";
	private static String GET = "/getPosts.jsp";

	private Post post;
	
	public PostController() {
		super();
		post = new Post();
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
			forward = INSERT; // insert will command to create a new post
		} else if (action.equalsIgnoreCase("get")) {
			forward = GET; // get will command to retrieve a desired post
			request.setAttribute("posts", post.getCourtPosts(request.getAttribute("courtID")));
		} else {

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
	Post newPost = new Post(Post.generatePostID(),request.getAttribute("playerID"), request.getAttribute("courtID"), request.getParameter("title"), 
			request.getParameter("description"), request.getAttribute("postPic"),request.getAttribute("props"),request.getAttribute("rating"));

		try {
			newPost.makePost();
			// create social.jsp
			response.sendRedirect("social.jsp");
			
		} catch (ParseException e) {
			e.printStackTrace();
		}


	}

}
