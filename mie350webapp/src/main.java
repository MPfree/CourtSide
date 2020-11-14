import com.mie.model.image;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.mie.model.Student;
import com.mie.util.DbUtil;

public class main {
	
	private Connection conn;
	
	public static void main(String args[]) {
		
		
		
	}
	
	public void makePost(int postID, int playerID, int courtID, String title, 
			String description, image postPic, int props, double rating) {
		conn = DbUtil.getConnection();
		try {
			
			PreparedStatement makePostPrep = conn.prepareStatement("insert into Social (PropID,CourtID,PlayerID,Title,Description,Post_Image"
					+ ",Props,Rating values (?,?,?,?,?,?,?,?)");
			makePostPrep.setInt(1, postID);
			makePostPrep.setInt(2, courtID);
			makePostPrep.setInt(3, playerID);
			makePostPrep.setString(4, title);
			makePostPrep.setString(5, description);
//			makePostPrep.setImage(6, postPic);
			makePostPrep.setInt(7, props);
			makePostPrep.setDouble(8, rating);
			
			makePostPrep.execute();

			
		}catch(SQLException e) {
			e.printStackTrace();

		}
		
	}

}
