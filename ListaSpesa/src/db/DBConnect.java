package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnect {
	
	public static Connection getConnection(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			//in caso di fallimento scrivo infor nel log di sistema 
			Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,e);
		}
		
		String url = "jdbc:mysql://localhost/listaspesa?user=root&password=";
		Connection conn = null;
		
		try {
			conn= DriverManager.getConnection(url);
		} catch (SQLException e) {
			Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,e);
		}
		
		return conn;
	}

}
