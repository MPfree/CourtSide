package com.mie.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.mie.dao.CommentDao;
import com.mie.util.DbUtil;

public class Post {
	
	private Connection conn;
	
	private int postID;
	private int playerID;
	private int courtID;
	private String title;
	private String description;
	private ArrayList<Comment> comments;
	
	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public int getPostID() {
		return postID;
	}

	public void setPostID(int postID) {
		this.postID = postID;
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public int getCourtID() {
		return courtID;
	}

	public void setCourtID(int courtID) {
		this.courtID = courtID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public image getPostPic() {
		return postPic;
	}

	public void setPostPic(image postPic) {
		this.postPic = postPic;
	}

	public int getProps() {
		return props;
	}

	public void setProps(int props) {
		this.props = props;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
	
	public ArrayList<Comment> getComments(){
		return comments;
	}
	
	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}

	private image postPic;
	private int props;
	private double rating;
	
	public Post(int postID, int playerID, int courtID, String title, 
			String description, image postPic, int props, double rating, ArrayList<Comment> comments) {
		this.postID = postID;
		this.playerID = playerID;
		this.courtID = courtID;
		this.title = title;
		this.description = description;
		this.postPic = postPic;
		this.props = props;
		this.rating = rating;
		this.comments = comments;
	}
	
	public Post(int postID, int playerID, int courtID, String title, 
			String description, image postPic, int props, double rating) {
		this(postID, playerID, courtID, title, description, postPic, props, rating, null);
	}
	
	
	public Post() {
		
	}
	
	
	public void makePost() {
		conn = DbUtil.getConnection();
		try {
			int postID = generatePostID();
			
			if (postID != 0) {
				PreparedStatement makePostPrep = conn.prepareStatement("insert into Social (PropID,CourtID,PlayerID,Title,Description,Post_Image"
						+ ",Props,Rating) values (?,?,?,?,?,?,?,?)");
				makePostPrep.setInt(1, postID);
				makePostPrep.setInt(2, this.courtID);
				makePostPrep.setInt(3, this.playerID);
				makePostPrep.setString(4, this.title);
				makePostPrep.setString(5, this.description);
//				makePostPrep.setImage(6, this.postPic);
				makePostPrep.setInt(7, this.props);
				makePostPrep.setDouble(8, this.rating);
				
				makePostPrep.execute();
			}else {
				// not good but not sure if this would ever happen
			}
			
		}catch(SQLException e) {
			e.printStackTrace();

		}
		
	}
	
	public HashMap<Integer,Post> getCourtPosts(int courtID) {
		conn = DbUtil.getConnection();
		CommentDao commentDao = new CommentDao();
		try {
			// Creates a hashmap where the key is the postID and the value is the Post object
			HashMap<Integer,Post> posts = new HashMap<Integer,Post>();
			
			String getPostQuery = "Select * from Social where CourtID = " + courtID + " order by PostID desc";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(getPostQuery);
			
			while(rs.next()) {
				int postID = rs.getInt(1);
				ArrayList<Comment> comments = commentDao.getComments(postID);
				Post newPost = new Post(postID, courtID, rs.getInt(3), rs.getString(4),rs.getString(5),
						(image)rs.getObject(6),rs.getInt(7),rs.getDouble(8), comments);
				
				posts.put(rs.getInt(1), newPost);
			}
			
			return posts;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;

		}
	}
	
	public int generatePostID() {
		conn = DbUtil.getConnection();
		try {
			
			String generateIDQuery = "select count(PostID) from Social";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(generateIDQuery);
			
			return rs.getInt(1)+1;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int getProps(int postID) {
		conn = DbUtil.getConnection();
		try {
			String getProps = "select Props from Social where PostID = "+postID;
			Statement stProps = conn.createStatement();
			ResultSet rs = stProps.executeQuery(getProps);
			
			return rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public void updateProps(int postID) {
		conn = DbUtil.getConnection();
		try {
			
			String updateProps = "update Social set Props = "+ (getProps(postID)+1) + "where PostID = " + postID;
			Statement st = conn.createStatement();
			st.execute(updateProps);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}

