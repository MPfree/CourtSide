package com.mie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mie.model.Players;
import com.mie.util.DbUtil;

//William Jereza :)
public class PlayersDao {

	private Connection connection; 
	
	//constructor: initialize the data access object and establish a connection with database
	public PlayersDao() {
		connection = DbUtil.getConnection();
	}
	
	//add player
	public void addPlayer(Players player) {
		try {
			PreparedStatement ps = connection
					.prepareStatement("insert into players values (?,?,?,?,?,?,?)");
			ps.setInt(1, player.getPlayerID());
			ps.setString(2, player.getUsername());
			ps.setString(3, player.getPassword());
			ps.setString(4, player.getName());
			ps.setString(5, player.getPosition());
			ps.setString(6, player.getHeight());
			ps.setBytes(7, player.getProfilePic());
			
			//finally execute request
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//delete player
	public void deletePlayer(Players player) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("delete from players where playerid=?");
			// Parameters start with 1
			preparedStatement.setInt(1, player.getPlayerID());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//get list of all players
	public List<Players> getAllPlayers() {
	List<Players> players = new ArrayList<Players>();
	try {
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("select * from players");
		while (rs.next()) {
			Players player = new Players();
			player.setPlayerID(rs.getInt("playerid"));
			player.setUsername(rs.getString("username"));
			player.setPassword(rs.getString("password"));
			player.setName(rs.getString("name"));
			player.setPosition(rs.getString("position"));
			player.setHeight(rs.getString("height"));
			player.setProfilepic(rs.getBytes("profile"));
			
			//finally add to list
			players.add(player);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}

	return players;
}

//	public void updatePlayer (Player player) {
//		try {
//			PreparedStatement preparedStatement = connection
//					.prepareStatement("update users set firstname=?, lastname=?, dob=?, email=?"
//							+ " where userid=?");
//			// Parameters start with 1
//			preparedStatement.setString(1, user.getFirstName());
//			preparedStatement.setString(2, user.getLastName());
//			preparedStatement.setDate(3, new java.sql.Date(user.getDob()
//					.getTime()));
//			preparedStatement.setString(4, user.getEmail());
//			preparedStatement.setInt(5, user.getUserid());
//			preparedStatement.executeUpdate();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
	

//	public User getUserById(int userId) {
//		User user = new User();
//		try {
//			PreparedStatement preparedStatement = connection
//					.prepareStatement("select * from users where userid=?");
//			preparedStatement.setInt(1, userId);
//			ResultSet rs = preparedStatement.executeQuery();
//
//			if (rs.next()) {
//				user.setUserid(rs.getInt("userid"));
//				user.setFirstName(rs.getString("firstname"));
//				user.setLastName(rs.getString("lastname"));
//				user.setDob(rs.getDate("dob"));
//				user.setEmail(rs.getString("email"));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return user;
//	}
	
}