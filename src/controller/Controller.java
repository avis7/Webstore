package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Store.Product;
import Store.ProductList;

import command.AddToCart;
import command.Command;
import dao.ProductsDAO;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductList productList = ProductList.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doStuff(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	private void doStuff(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Command cmd = null;
		if ("view".equals(request.getParameter("action"))) {
			RequestDispatcher rd = request.getRequestDispatcher("/List.jspx");
			request.setAttribute("products", productList.getProductList());
			rd.forward(request, response);
		}
		if ("add".equals(request.getParameter("action"))) {
			cmd = new AddToCart();
			String returnPath = cmd.execute(request, response);
			if (returnPath != null) {
				RequestDispatcher rd = request.getRequestDispatcher(returnPath);
				request.setAttribute("products", productList.getProductList());
				rd.forward(request, response);
			}
		}
			if ("order".equals(request.getParameter("action"))){
				cmd= new MakeOrder();
				String returnPath = cmd.execute(request, response);
				if (returnPath != null) {
					RequestDispatcher rd = request.getRequestDispatcher(returnPath);
					request.setAttribute("products", productList.getProductList());
					rd.forward(request, response);
				}
			}
			if ("admin".equals(request.getParameter("action"))) {
			//	RequestDispatcher rd = request.getRequestDispatcher("/AdminControl");
				response.sendRedirect("/Webstore/AdminControl?action=new");
				//request.setAttribute("products", productList.getProductList());
				//rd.forward(request, response);
			}
	}
}
