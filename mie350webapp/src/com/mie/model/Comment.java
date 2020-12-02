package com.mie.model;
import java.sql.Connection;

public class Comment {

	private int commentID;
	private int postID;
	private String comment;
	
	public Comment(int commentID, int postID, String comment) {
		this.commentID = commentID;
		this.postID = postID;
		this.comment = comment;
	}
	
	public Comment(int postID, String comment) {
		this.postID = postID;
		this.comment = comment;
	}

	public int getCommentID() {
		return commentID;
	}

	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}

	public int getPostID() {
		return postID;
	}

	public void setPostID(int postID) {
		this.postID = postID;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
