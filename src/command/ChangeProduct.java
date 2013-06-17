package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Store.Product;
import dao.ProductsDAO;

public class ChangeProduct implements Command {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		Product product = new Product();
		ProductsDAO productBase = new ProductsDAO();
		product.setName(request.getParameter("prodName"));
		product.setPrice(Integer.parseInt(request.getParameter("prodPrice")));
		productBase.updateRecord(product);
		return "/NewProduct.jspx";
		
	}

}
