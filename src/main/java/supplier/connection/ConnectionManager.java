package supplier.connection;


 

import java.sql.*;

public class ConnectionManager {
    private static Connection con;
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost/myproject";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    
    public static Connection getConnection() {

        try {
        	//1. load the driver
            Class.forName(DB_DRIVER);

            try {
            	//2. create connection
                con = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
                System.out.println("connected");
            }

            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }
}
