package com.mie.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mie.model.*;

public class PostController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static String SOCIAL = "/social.jsp";

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

		if (action.equalsIgnoreCase("get")) {
			forward = SOCIAL; // get will command to retrieve a desired post
			int courtId = Integer.parseInt(request.getParameter("courtID"));
			HashMap<Integer,Post> posts = post.getCourtPosts(courtId);
			request.setAttribute("posts", posts);
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
		int postID = post.generatePostID();
		int playerID = Integer.parseInt(request.getParameter("playerID"));
		int courtID = Integer.parseInt(request.getParameter("courtID"));
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		image postPic = null;
		int props = Integer.parseInt(request.getParameter("props"));
		int rating = Integer.parseInt(request.getParameter("rating"));
		
		Post newPost = new Post(postID, playerID, courtID, title, description, postPic, props, rating);

	
		newPost.makePost();
		// create social.jsp
		RequestDispatcher view = request
				.getRequestDispatcher(SOCIAL);
		int courtId = Integer.parseInt(request.getParameter("courtID"));
		HashMap<Integer,Post> posts = post.getCourtPosts(courtId);
		request.setAttribute("posts", posts);
		view.forward(request, response);
	}
}
