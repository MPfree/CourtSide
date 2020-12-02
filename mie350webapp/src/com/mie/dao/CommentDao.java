package com.mie.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mie.model.Comment;
import com.mie.util.DbUtil;

public class CommentDao {
	Connection conn = null;
	
	public void addComment(Comment newComment) {
		conn = DbUtil.getConnection();
		try {
			int postID = newComment.getPostID();
			int commentID = generateCommentID(postID);
			
			PreparedStatement makeCommentPrep = conn.prepareStatement("insert into Comment (CommentID,PostID,Comment)"
					+ "values (?,?,?)");
			makeCommentPrep.setInt(1, commentID);
			makeCommentPrep.setInt(2, postID);
			makeCommentPrep.setString(3, newComment.getComment());
			makeCommentPrep.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Comment> getComments(int postID) {
		conn = DbUtil.getConnection();
		ArrayList<Comment> comments = new ArrayList<Comment>();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Comments "
					+ "WHERE PostID=" + postID);
			while(rs.next()) {
				int commentID = rs.getInt("CommentID");
				String commentContent = rs.getString("Comment");
				Comment comment = new Comment(commentID, postID, commentContent);
				comments.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			return comments;
		}
	
	public int generateCommentID(int postID) {
		conn = DbUtil.getConnection();
		try {
			
			String generateIDQuery = "select count(CommentID) from Comments "
					+ "where PostID=" + postID;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(generateIDQuery);
			
			return rs.getInt(1)+1;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

}
