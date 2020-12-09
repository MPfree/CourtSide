package com.mie.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mie.dao.CommentDao;
import com.mie.model.Comment;
import com.mie.model.Post;

public class PropsController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static String SOCIAL = "/social.jsp";
	Post dao = new Post();
	
	public PropsController() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int postID = Integer.parseInt(request.getParameter("postID"));
		dao.updateProps(postID);
		
		RequestDispatcher view = request
				.getRequestDispatcher(SOCIAL);
		int courtID = (Integer) request.getSession().getAttribute("courtID");
		Post post = new Post();
		HashMap<Integer,Post> posts = post.getCourtPosts(courtID);
		request.setAttribute("posts", posts);
		view.forward(request, response);
	}

}
