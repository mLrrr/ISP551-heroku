package supplier.dao;

/**
 * Author: Fadilah Ezlina Shahbudin (fadilahezlina@uitm.edu.my)
 * Date: 12 January 2023
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import supplier.connection.ConnectionManager;
import supplier.model.Product;

public class ProductDAO {
	private static Connection con = null;
	private static ResultSet rs = null; 
	private static PreparedStatement ps=null;
	private static Statement stmt=null;
	private static String productname, sql;
	private static double price;
	private static int pid, quantity, sid; 

	//add product
	public void add(Product bean){		
		productname = bean.getProductname();
		price = bean.getPrice();
		quantity = bean.getQuantity();
		sid = bean.getSid();

		try {
			//call getConnection() method
			con = ConnectionManager.getConnection();

			//3. create statement
			sql = "INSERT INTO product(productname,price,quantity,sid)VALUES(?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1,productname);
			ps.setDouble(2,price);
			ps.setInt(3,quantity);
			ps.setInt(4,sid);

			//4. execute query
			ps.executeUpdate();

			//5. close connection
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	//get all products
	public static List<Product> getAllProduct() { 
		List<Product> products = new ArrayList<Product>(); 
		try { 
			//call getConnection() method
			con = ConnectionManager.getConnection();

			//3. create statement
			sql = "SELECT * FROM product ORDER BY pid";
			stmt = con.createStatement(); 

			//4. execute query
			rs = stmt.executeQuery(sql);

			while (rs.next()) { 
				Product product = new Product();
				product.setPid(rs.getInt("pid"));	  
				product.setProductname(rs.getString("productname"));
				product.setPrice(rs.getDouble("price"));
				product.setQuantity(rs.getInt("quantity"));
				product.setSid(rs.getInt("sid"));
				products.add(product);

			} 
			//5. close connection
			con.close();

		}catch(Exception e) {
			e.printStackTrace();
		}
		return products; 
	}

	//get product by Id
	public static Product getProductById(int pid) {
		Product product = new Product();
		try {
			//call getConnection() method
			con = ConnectionManager.getConnection();

			//3. create statement 
			sql = "SELECT * FROM product WHERE pid='"+pid+"'";
			stmt = con.createStatement(); 

			//4. execute query
			rs = stmt.executeQuery(sql);

			if (rs.next()) {	            
				product.setPid(rs.getInt("pid"));	  
				product.setProductname(rs.getString("productname"));
				product.setPrice(rs.getDouble("price"));
				product.setQuantity(rs.getInt("quantity"));
				product.setSid(rs.getInt("sid"));
			}

			//5. close connection
			con.close();

		}catch(Exception e) {
			e.printStackTrace();
		}

		return product;
	}

	//delete product
	public void deleteProduct(int pid) {
		try {
			//call getConnection() method 
			con = ConnectionManager.getConnection();

			//3. create statement 
			sql = "DELETE FROM product WHERE pid=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, pid);

			//4. execute query
			ps.executeUpdate();

			//5. close connection
			con.close();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	//update product
	public void updateProduct(Product bean) {

		pid = bean.getPid();
		productname = bean.getProductname();
		price = bean.getPrice();
		quantity = bean.getQuantity();
		sid = bean.getSid();

		try {
			//call getConnection() method 
			con = ConnectionManager.getConnection();

			//3. create statement 
			sql = "UPDATE product SET productname=?, price=?, quantity=?, sid=? WHERE pid=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,productname);
			ps.setDouble(2,price);
			ps.setInt(3,quantity);
			ps.setInt(4,sid);
			ps.setInt(5,pid);

			//4. execute query
			ps.executeUpdate();

			//5. close connection
			con.close();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	//get products and supplier
	public static List<Product> getSupplierProducts() { 
		List<Product> products = new ArrayList<Product>(); 
		try { 
			//call getConnection() method 
			con = ConnectionManager.getConnection();

			//3. create statement 
			sql = "SELECT * FROM product p INNER JOIN supplier s ON p.sid = s.sid";
			stmt = con.createStatement(); 

			//4. execute query
			rs = stmt.executeQuery(sql);

			while (rs.next()) { 
				Product product = new Product();
				product.setPid(rs.getInt("pid"));	  
				product.setProductname(rs.getString("productname"));
				product.setPrice(rs.getDouble("price"));
				product.setQuantity(rs.getInt("quantity"));
				product.setSid(rs.getInt("sid"));
				product.setSupplier(SupplierDAO.getSupplierById(rs.getInt("sid")));
				products.add(product);
			} 

			//5. close connection
			con.close();

		}catch(Exception e) {
			e.printStackTrace();
		}

		return products; 
	}
}
