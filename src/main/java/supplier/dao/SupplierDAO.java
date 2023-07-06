package supplier.dao;

/**
 * Author: Fadilah Ezlina Shahbudin (fadilahezlina@uitm.edu.my)
 * Date: 12 January 2023
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import supplier.connection.ConnectionManager;
import supplier.model.Supplier;

public class SupplierDAO {

	private static Connection con = null;
	private static ResultSet rs = null; 
	private static PreparedStatement ps=null;
	private static Statement stmt=null;
	private static String name, address,city, state, country,phoneno, sql;
	private static int sid, postcode, id; 

	//get all suppliers
	public static List<Supplier> getAllSupplier() {
		List<Supplier> suppliers = new ArrayList<Supplier>();
		try {
			//call getConnection() method
			con = ConnectionManager.getConnection();

			//3. create statement  
			sql = "SELECT * FROM supplier ORDER BY sid";
			stmt = con.createStatement();

			//4. execute query
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Supplier supplier = new Supplier();
				supplier.setSid(rs.getInt("sid"));
				supplier.setName(rs.getString("name"));
				supplier.setAddress(rs.getString("address"));
				supplier.setCity(rs.getString("city"));
				supplier.setPostcode(rs.getInt("postcode"));
				supplier.setState(rs.getString("state"));
				supplier.setCountry(rs.getString("country"));				
				supplier.setPhoneno(rs.getString("phoneno"));
				supplier.setId(rs.getInt("id"));
				suppliers.add(supplier);
			}
			//5. close connection
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();		
		}
		return suppliers;
	}

	//get supplier by sid
	public static Supplier getSupplierById(int sid) {
		Supplier supplier = new Supplier();
		try {
			//call getConnection() method  
			con = ConnectionManager.getConnection();

			//3. create statement  
			sql = "SELECT * FROM supplier WHERE sid='"+sid+"'";
			stmt = con.createStatement();

			//4. execute query
			rs = stmt.executeQuery(sql);

			if (rs.next()) {	            
				supplier.setSid(rs.getInt("sid"));
				supplier.setName(rs.getString("name"));
				supplier.setAddress(rs.getString("address"));
				supplier.setCity(rs.getString("city"));
				supplier.setPostcode(rs.getInt("postcode"));
				supplier.setState(rs.getString("state"));
				supplier.setCountry(rs.getString("country"));				
				supplier.setPhoneno(rs.getString("phoneno"));
				supplier.setId(rs.getInt("id"));
			}
			//5. close connection
			con.close();

		}catch(Exception e) {
			e.printStackTrace();

		}

		return supplier;
	}

	//get supplier by id
	public static Supplier getSupplierId(int id) {
		Supplier supplier = new Supplier();
		try {
			//call getConnection() method  
			con = ConnectionManager.getConnection();

			//3. create statement  
			sql = "SELECT * FROM supplier WHERE id='"+id+"'";
			stmt = con.createStatement();

			//4. execute query
			rs = stmt.executeQuery(sql);

			if (rs.next()) {	            
				supplier.setSid(rs.getInt("sid"));
				supplier.setName(rs.getString("name"));
				supplier.setAddress(rs.getString("address"));
				supplier.setCity(rs.getString("city"));
				supplier.setPostcode(rs.getInt("postcode"));
				supplier.setState(rs.getString("state"));
				supplier.setCountry(rs.getString("country"));				
				supplier.setPhoneno(rs.getString("phoneno"));
				supplier.setId(rs.getInt("id"));
			}
			//5. close connection
			con.close();

		}catch(Exception e) {
			e.printStackTrace();
		}

		return supplier;
	}

	//update supplier
	public void updateSupplier(Supplier bean) {

		sid = bean.getSid();
		name = bean.getName();
		address = bean.getAddress();
		city = bean.getCity();
		postcode = bean.getPostcode();
		state = bean.getState();
		country = bean.getCountry();		
		phoneno = bean.getPhoneno(); 		

		try {
			//call getConnection() method  
			con = ConnectionManager.getConnection();

			//3. create statement  
			sql = "UPDATE supplier SET name=?,address=?,city=?,postcode=?,state=?,country=?,phoneno=? WHERE sid=?";
			ps=con.prepareStatement(sql); 		  
			ps.setString(1,name);
			ps.setString(2,address);		
			ps.setString(3,city);
			ps.setInt(4,postcode);
			ps.setString(5,state);
			ps.setString(6,country);			
			ps.setString(7,phoneno);				
			ps.setInt(8,sid);

			//4. execute query
			ps.executeUpdate();

			//5. close connection
			con.close();

		}catch(Exception e) {
			e.printStackTrace();

		}
	}
}
