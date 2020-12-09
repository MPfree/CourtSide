package com.mie.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;

import com.mie.dao.CommentDao;
import com.mie.model.Comment;
import com.mie.model.Post;
import com.mie.model.image;

public class CommentController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static String SOCIAL = "/social.jsp";
	CommentDao dao = new CommentDao();
	
	public CommentController() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int postID = Integer.parseInt(request.getParameter("postID"));
		String commentContent = request.getParameter("comment");
		Comment newComment = new Comment(postID, commentContent);
		dao.addComment(newComment);
		
		RequestDispatcher view = request
				.getRequestDispatcher(SOCIAL);
		int courtID = (Integer) request.getSession().getAttribute("courtID");
		Post post = new Post();
		HashMap<Integer,Post> posts = post.getCourtPosts(courtID);
		request.setAttribute("posts", posts);
		view.forward(request, response);
	}
}


