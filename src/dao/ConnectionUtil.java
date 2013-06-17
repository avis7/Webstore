package dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class ConnectionUtil {
	Connection connection;
	private static ConnectionUtil instance;
	public static ConnectionUtil getInstanse(){
		if (instance != null) return instance;
		else return new ConnectionUtil();
	}
	// Название драйвера
	private final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	// Create a connection to the database
	private final String SERVER_NAME = "localhost";
	private final String MY_DATABASE = "webstore";
	private final String URL = "jdbc:mysql://" + SERVER_NAME + "/" + MY_DATABASE;
	private final String USERNAME = "root";
	private final String PASSWORD = "breangerfear";
	public Connection getConnection() throws SQLException {
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException();
		}
		connection = (Connection) DriverManager.getConnection(URL, USERNAME,
				PASSWORD);
		return connection;
	}
	public void closeConnection(Connection connection) throws SQLException{
		connection.close();
	}
	public void closeConnection(Connection connection, ResultSet rs) throws SQLException{
		rs.close();
		connection.close();
	}
		
}
