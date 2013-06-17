package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

import Store.Product;

public class ProductsDAO extends BaseDAO<Product> {
	{
		tblName = "products";
	}

	@Override
	Product fetchObject(ResultSet rs) throws SQLException {
		Product product = new Product();
		product.setId(rs.getInt("id"));
		product.setName(rs.getString("name"));
		product.setPrice(rs.getInt("price"));
		return product;
	}

	@Override
	public void deleteRecord(Product obj) throws Exception {
		String deleteFromeOrders = " delete from orders where name="
				+ obj.getId();
		String query = "delete from " + tblName + " where name="
				+ obj.getName() + " and price=" + obj.getPrice()
				+ deleteFromeOrders;
		Connection connection = helper.getConnection();
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.executeQuery();
		helper.closeConnection(connection);
	}

	@Override
	public void insertNewRecord(Product obj) {
		String query = "insert into " + tblName + " (name, price) values(?,?)";
		Connection connection;
		try {
			connection = helper.getConnection();
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, obj.getName());
			stmt.setInt(2, obj.getPrice());
			stmt.executeUpdate();
			helper.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	public void updateRecord(Product obj) {
		String query = "update " + tblName + " set price=? where name=?";
		Connection connection;
		try {
			connection = helper.getConnection();
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(2, obj.getName());
			stmt.setInt(1, obj.getPrice());
			stmt.executeUpdate();
			helper.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
}
