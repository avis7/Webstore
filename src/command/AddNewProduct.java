package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductsDAO;

import Store.Product;

public class AddNewProduct implements Command {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		Product product = new Product();
		ProductsDAO productBase = new ProductsDAO();
		product.setName(request.getParameter("prodName"));
		product.setPrice(Integer.parseInt(request.getParameter("prodPrice")));
		productBase.insertNewRecord(product);
		return "/NewProduct.jspx";
	}
	
}
