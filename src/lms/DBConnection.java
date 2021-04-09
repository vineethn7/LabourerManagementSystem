package lms;

import java.sql.*;

public class DBConnection<T extends Contractor> {
	Connection con=null;
	Connection startConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "Nvvrk@123");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("exception raised");
		}
		return con;
	}

	void closeConnection(Connection c) {
		try {
			c.close();
		} catch (SQLException e) {
			System.out.println("exception raised");
		}
	}
	T insert(String query,T user) {
		try {
			PreparedStatement ps=con.prepareStatement(query);
			if(user.userType==1) {
				ps.setString(1, user.getCFname());
				ps.setString(2, user.getCLname());
				ps.setString(3, user.getCDOB());
				ps.executeUpdate();
				ps=con.prepareStatement("SELECT Contractor_ID from contractor order by Contractor_ID desc limit 1;");
				ResultSet rs=ps.executeQuery();
				if(rs.next()) {
					user.setContractor_Id(rs.getInt(1));
				}
				ps=con.prepareStatement("insert into cphone_directory values(?,?)");
				ps.setInt(1, user.getContractor_Id());
				ps.setString(2, user.getPhone_num());
				ps.executeUpdate();
			}
			else {
				//labour
			}
		} catch (SQLException e) {
			System.out.println("exception raised");
		}
		return user;
	}
	T fetchCurrentUser(T user,String phone_num,int userType) {
		try {
			
			if(userType==1) {
				PreparedStatement ps=con.prepareStatement("select Contractor_ID,Fname,Lname,DOB from contractor c, cphone_directory cp where c.Contractor_ID=cp.CID and Phone_no=?");
				ps.setString(1, phone_num);
				ResultSet rs=ps.executeQuery();
				if(rs.next()) {
					user=(T) new Contractor(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),phone_num);
				}
				else {
					user=null;
				}
			}
			else {
				user=null;
			}
		} catch (SQLException e) {
			System.out.println("exception raised");
		}
		return user;
	}
}
