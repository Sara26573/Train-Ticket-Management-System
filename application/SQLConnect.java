package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnect {
	 public static Connection connectionDatabase;
	   public Connection getConnection() {
	       
	       
	            String databaseName = "treni"; 
	            String dbUname = "root"; 
	            String dbPass = "root";
	            String url="jdbc:mysql://localhost:3306/" +databaseName;
	            try {
	            	connectionDatabase= DriverManager.getConnection(url, dbUname, dbPass);
	            }
	         catch(SQLException ex) {
	            System.out.println("MYSQL not connected");
	            System.out.println(ex.getMessage());
	        }
	        return connectionDatabase;
	    }
}
