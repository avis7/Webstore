package dao;


import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class BaseDAO <T> {
	ConnectionUtil helper = ConnectionUtil.getInstanse();
	String tblName=null;
	
		
	public T find(int id) {
		try {
			String query = "select * from "+tblName+" where id=?";
			Connection connection = helper.getConnection();
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1,id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			T retObj=fetchObject(rs);
			helper.closeConnection(connection, rs);
			return retObj;
		}
		catch (SQLException e) {
			throw new RuntimeException();
		} 
	}
	
	
	public ArrayList<T> findAll() {
		try {
            String query = "select * from "+tblName;
            Connection connection = helper.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			ArrayList<T> al = new ArrayList<T>();
			while(rs.next()) {
				al.add(fetchObject(rs));
			}
			helper.closeConnection(connection, rs);
			return al;
		}
		catch (SQLException e) {
			throw new RuntimeException();
		} 
	}
	
	public abstract void insertNewRecord(T obj);
	
	public void updateRecord(int id, T obj) {
		
	}
	
	abstract T fetchObject(ResultSet rs) throws SQLException;
	
	public abstract void deleteRecord(T obj) throws Exception;

}
