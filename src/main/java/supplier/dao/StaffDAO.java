package supplier.dao;

/**
 * Author: Fadilah Ezlina Shahbudin (fadilahezlina@uitm.edu.my)
 * Date: 12 January 2023
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import supplier.connection.ConnectionManager;
import supplier.model.Staff;

public class StaffDAO {
	private static Connection con = null;
	private static ResultSet rs = null; 
	private static PreparedStatement ps=null;
	private static Statement stmt=null;
	private static String name, sql;
	private static int staffid; 	

	//get all staff
	public static List<Staff> getAllStaff() {
		List<Staff> staffs = new ArrayList<Staff>();
		try {
			//call getConnection() method 
			con = ConnectionManager.getConnection();

			//3. create statement 
			sql = "SELECT * FROM staff ORDER BY staffid";
			stmt = con.createStatement();

			//4. execute query
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Staff staff = new Staff();
				staff.setStaffid(rs.getInt("staffid"));
				staff.setName(rs.getString("name"));
				staff.setId(rs.getInt("id"));
				staffs.add(staff);

			}
			//5. close connection
			con.close();

		}catch(Exception e) {
			e.printStackTrace();
		}

		return staffs;
	}


	//get staff by staffid
	public static Staff getStaffById(int staffid) {
		Staff staff = new Staff();
		try {
			//call getConnection() method 
			con = ConnectionManager.getConnection();
			
			//3. create statement 
			sql = "SELECT * FROM staff WHERE staffid='"+staffid+"'";
			stmt = con.createStatement(); 

			//4. execute query
			rs = stmt.executeQuery(sql);
			
			if (rs.next()) {	            
				staff.setStaffid(rs.getInt("staffid"));
				staff.setName(rs.getString("name"));
				staff.setId(rs.getInt("id"));
			}
			//5. close connection
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

		return staff;
	}

	//update staff
	public void updateStaff(Staff bean) {

		staffid = bean.getStaffid();
		name = bean.getName();	

		try {
			//call getConnection() method
			con = ConnectionManager.getConnection();
			
			//3. create statement 
			sql = "UPDATE staff SET name=? WHERE staffid=?";
			ps=con.prepareStatement(sql); 		
			ps.setString(1,name);	
			ps.setInt(2,staffid);
			
			//4. execute query
			ps.executeUpdate();
			
			//5. close connection
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
