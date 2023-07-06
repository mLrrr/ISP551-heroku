package supplier.dao;

/**
 * Author: Fadilah Ezlina Shahbudin (fadilahezlina@uitm.edu.my)
 * Date: 12 January 2023
 */

import java.security.*;
import java.sql.*;
import java.util.*;
import supplier.connection.ConnectionManager;
import supplier.model.User;

public class UserDAO {

	private static Connection con = null;
	private static ResultSet rs = null; 
	private static PreparedStatement ps=null;
	private static Statement stmt=null;
	private static String email, password, role,sql;
	private static int id;

	//login
	public static User login(User bean) throws NoSuchAlgorithmException{
		//get email and password
		email = bean.getEmail();
		password = bean.getPassword();

		//convert the password to MD5
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());

		byte byteData[] = md.digest();

		//convert the byte to hex format
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}

		try {
			//call getConnection() method 
			con = ConnectionManager.getConnection();
			
			//3. create statement
			sql = "SELECT * FROM  user WHERE email='" + email + "'AND password='" + sb.toString() + "'";
			stmt = con.createStatement();
			
		    //4. execute query
			rs = stmt.executeQuery(sql);

			// if user exists set the isValid variable to true
			if (rs.next()) {
				bean.setId(rs.getInt("id"));
				bean.setEmail(rs.getString("email"));
				bean.setRole(rs.getString("role"));
				bean.setValid(true);
			}
			// if user does not exist set the isValid variable to false
			else{
				bean.setValid(false);
			}

			//5. close connection
			con.close();
		}catch(Exception e) {
			e.printStackTrace();		
		}

		return bean;
	}

	//add new user (register)
	public void add(User bean) throws NoSuchAlgorithmException{
		//get email,name and password
		email = bean.getEmail();
		password = bean.getPassword();
		role = bean.getRole();

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte byteData[] = md.digest();

		//convert the byte to hex format
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}

		try {
			//call getConnection() method 
			con = ConnectionManager.getConnection();
			
			//3. create statement  
			sql = "INSERT INTO user(email,password,role)VALUES(?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1,email);
			ps.setString(2,sb.toString());
			ps.setString(3,role);
			
			//4. execute query
			ps.executeUpdate();			
			
			//5. close connection
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();		
		}
	}

	//get user
	public static User getUser(User bean)  {   
		//get email
		email = bean.getEmail();
		
		try {
			//call getConnection() method 
			con = ConnectionManager.getConnection();
			
			//3. create statement  
			sql = "SELECT * FROM user WHERE email='" + email + "'";
			stmt = con.createStatement();
			
			//execute statement
			rs = stmt.executeQuery(sql);

			// if user exists set the isValid variable to true
			if (rs.next()) {
				bean.setId(rs.getInt("id"));
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("password"));
				bean.setRole(rs.getString("role"));
				bean.setValid(true);
			}
			// if user does not exist set the isValid variable to false
			else{
				bean.setValid(false);
			}
			//5. close connection
			con.close();	
			
		}catch(Exception e) {
			e.printStackTrace();		
		}
		return bean;
	}

	//get user by email
	public static User getUserByEmail(String email) {
		User user = new User();
		try {
			//call getConnection() method 
			con = ConnectionManager.getConnection();
			
			//3. create statement  
			sql = "SELECT * FROM user WHERE email='" + email + "'";
			stmt = con.createStatement();
			
			//execute statement
			rs = stmt.executeQuery(sql);

			if (rs.next()) {	            
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));				
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
			}
			//5. close connection
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();		
		}

		return user;
	}

	//get user by id
	public static User getUserById(int id) {
		User user = new User();
		try {
			//call getConnection() method 
			con = ConnectionManager.getConnection();
			
			//3. create statement  
			sql = "SELECT * FROM user WHERE id='" + id + "'";
			stmt = con.createStatement();
			
			//execute statement
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
			}
			//5. close connection
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();		
		}

		return user;
	}
	
	//get all user by id
	public static List<User> getUserId() {
		List<User> users = new ArrayList<User>();
		try {
			//call getConnection() method 
			con = ConnectionManager.getConnection();
			
			//3. create statement  
			sql = "SELECT id, email FROM user";
			stmt = con.createStatement();
			
			//4. execute query
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				users.add(user);
			}
			//5. close connection
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();		
		}

		return users;
	}
	//get all user role supplier
	public static List<User> getUserSupplier() {
		List<User> users = new ArrayList<User>();
		try {
			//call getConnection() method 
			con = ConnectionManager.getConnection();
			
			//3. create statement  
			stmt = con.createStatement();
			
			//4. execute query
			sql = "SELECT * FROM user u INNER JOIN supplier s ON u.id = s.id";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setSupplier(SupplierDAO.getSupplierId(rs.getInt("id")));
				users.add(user);
			}
			//5. close connection
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();		
		}

		return users;
	}
		
	//delete staff and supplier from table users
	public void deleteUser(int id) {
		try {
			//call getConnection() method 
			con = ConnectionManager.getConnection();
			
			//3. create statement 
			sql = "DELETE FROM user WHERE id=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			
			//4. execute query
			ps.executeUpdate();

			//5. close connection
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();		
		}
	}		
}
