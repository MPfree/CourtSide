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
					.prepareStatement("insert into PlayedID, Username, Password"
							+ "Name, Position, Height, Profile_Picture)"
							+ " values (?,?,?,?,?,?,?)");
			ps.setInt(1, player.getPlayerID());
			ps.setString(2, player.getUsername());
			ps.setString(3, player.getPassword());
			ps.setString(4, player.getName());
			ps.setString(5, player.getPosition());
			ps.setString(6, player.getHeight());
			ps.setBytes(7, null);
			
			//finally execute request
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int generatePlayerID() {
		conn = DbUtil.getConnection();
		try {

			String generateIDQuery = "select count(PlayerID) from Players";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(generateIDQuery);

			return rs.getInt(1)+1;

		}catch(SQLException e) {
			e.printStackTrace();
			return 0;
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
			
			//finally add to list
			players.add(player);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}

	return players;
}

	public static Players login(Players player) {

		/**
		 * This method attempts to find the member that is trying to log in by
		 * first retrieving the username and password entered by the user.
		 */
		Statement stmt = null;

		String username = player.getUsername();
		String password = player.getPassword();

		/**
		 * Prepare a query that searches the members table in the database
		 * with the given username and password.
		 */
		String searchQuery = "select * from members where username='"
				+ username + "' AND password='" + password + "'";

		try {
			// connect to DB
			currentCon = DbUtil.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean more = rs.next();

			/**
			 * If there are no results from the query, set the member to false.
			 * The person attempting to log in will be redirected to the home
			 * page when isValid is false.
			 */

			if (!more) {
				player.setValid(false);
			}

			/**
			 * If the query results in an database entry that matches the
			 * username and password, assign the appropriate information to
			 * the Member object.
			 */
			else if (more) {
				String name = rs.getString("Name");
				player.setFirstName(name);
				member.setValid(true);
			}
		}

		catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! "
					+ ex);
		}
		/**
		 * Return the Member object.
		 */
		return player;

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