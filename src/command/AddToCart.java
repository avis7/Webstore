package command;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProductsDAO;

import Store.OrderItem;
import Store.Product;

public class AddToCart implements Command {
	private ProductsDAO productBase = new ProductsDAO();

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("cart") == null) {
			session.setAttribute("cart", new ArrayList<OrderItem>());
		}
		ArrayList<OrderItem> orderList = (ArrayList<OrderItem>) session
				.getAttribute("cart");
		OrderItem item = new OrderItem();
		item.setAmount(Integer.parseInt(request.getParameter("ammount")));
		item.setProduct(productBase.find(Integer.parseInt(request.getParameter("productid"))));
		item.setPrice(item.getProduct().getPrice());
		orderList.add(item);
		session.setAttribute("cart", orderList);
		return "/List.jspx";
	}

}
