package controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Store.Order;
import Store.OrderItem;
import Store.Product;

import command.Command;
import dao.OrdersDAO;

public class MakeOrder implements Command {
	private OrdersDAO orderBase = new OrdersDAO();

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (session.getAttribute("cart") != null) {
			Order order = new Order();
			java.util.Date date = new java.util.Date();
			order.setDate(new java.sql.Date(date.getTime()));
			order.setItems((ArrayList<OrderItem>)  session.getAttribute("cart")); 
			orderBase.insertNewRecord(order);
		}
		return "/List.jspx";
	}

}
