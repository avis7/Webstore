package dao;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.mysql.jdbc.Connection;

import Store.Order;
import Store.OrderItem;
import Store.Product;

public class OrdersDAO extends BaseDAO<Order> {

	@Override
	public void insertNewRecord(Order obj) {
		String orderQuery = "insert into orderitems (order_id,product_id,amount,order_price) values(?,?,?,?)";
		String orderInfoQuery = "insert into orders (id,date) values(?,?)";
		Connection connection;

		try {
			connection = helper.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("select max(id) from orders");
			ResultSet rs = stmt.executeQuery();
			rs.next();
			int id = rs.getInt("max(id)");
			++id;
			stmt = connection.prepareStatement(orderInfoQuery);
			java.util.Date date = new java.util.Date();
			stmt.setInt(1, id);
			stmt.setDate(2, new java.sql.Date(date.getTime()));
			stmt.executeUpdate();
			stmt = connection.prepareStatement(orderQuery);

			stmt.setInt(1, id);
			for (OrderItem item : obj.getItems()) {
				Product product = item.getProduct();
				stmt.setInt(2, product.getId());
				stmt.setInt(3, item.getAmount());
				stmt.setInt(4, item.getPrice());
				stmt.executeUpdate();
			}
			helper.closeConnection(connection);
		} catch (SQLException e) {
			  e.printStackTrace();
		}

	}

	public ArrayList<Order> findAll() {
		try {
			String query = "select * from orderitems right join orders on order_id=orders.id"
					+ " right join products on product_id=products.id order by order_id";
			Connection connection = helper.getConnection();
			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Order> al = new ArrayList<Order>();
			ArrayList<OrderItem> items = new ArrayList<OrderItem>();
			Order order = new Order();
			while (rs.next()) {
				int tempId = rs.getInt("order_id");
					if (order.getId() == 0) {
					order.setId(tempId);
					order.setDate(rs.getDate("date"));
				}
				if (tempId != order.getId()) {
					order.setItems(items);
					al.add(order);
					order = new Order();
					order.setId(tempId);
					order.setDate(rs.getDate("date"));
					items = new ArrayList<OrderItem>();
					items.add(getItem(rs));
					} else {
					items.add(getItem(rs));
				}
			}
			order.setItems(items);
			al.add(order);
			helper.closeConnection(connection, rs);
			return al;
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	private OrderItem getItem(ResultSet rs) throws SQLException{
		Product product = new Product();
		product.setId(rs.getInt("product_id"));
		product.setName(rs.getString("name"));
		product.setPrice(rs.getInt("price"));
		OrderItem item = new OrderItem();
		item.setAmount(rs.getInt("amount"));
		item.setPrice(rs.getInt("order_price"));
		item.setProduct(product);
		return item;
	}

	@Override
	Order fetchObject(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRecord(Order obj) throws Exception {
		// TODO Auto-generated method stub

	}

}
