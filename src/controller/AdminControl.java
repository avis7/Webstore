package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.AddNewProduct;
import command.AddToCart;
import command.ChangeProduct;
import command.Command;
import dao.OrdersDAO;

/**
 * Servlet implementation class AdminControl
 */
@WebServlet("/AdminControl")
public class AdminControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	private void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Command cmd;
		String returnPath = null;
		OrdersDAO orderBase = new OrdersDAO();
		if ("new".equals(request.getParameter("action"))) {
			 returnPath = "/NewProduct.jspx";
				
			}
		if ("addProduct".equals(request.getParameter("action"))) {
			cmd = new AddNewProduct();
			 returnPath = cmd.execute(request, response);
			
		}
			if ("changeProduct".equals(request.getParameter("action"))) {
				cmd = new ChangeProduct();
				 returnPath = cmd.execute(request, response);
				
			}
			if ("orders".equals(request.getParameter("action"))) {
				 returnPath = "/OrderList.jspx";
				 request.setAttribute("orders", orderBase.findAll());
				
			}
			if (returnPath != null) {
				RequestDispatcher rd = request.getRequestDispatcher(returnPath);
				rd.forward(request, response);
			}
	}
}
