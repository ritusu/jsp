package com.lti.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lti.dao.ProductDao;
import com.lti.exception.DataAccessException;
import com.lti.model.Product;

/**
 * Servlet implementation class ProductControllerServlet
 */
@WebServlet("/ProductControllerServlet")
public class ProductControllerServlet extends HttpServlet {
	int currentPosition =1;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			int pageSize =4;
		
			
			String page= request.getParameter("page");
			if(page!= null) {
				if(page.equals("next"))
				currentPosition += pageSize;
				else if (page.equals("prev"))
				currentPosition -= pageSize;
			}
			else
				currentPosition =1;
			
			ProductDao dao= new ProductDao();
			try {
					List<Product> products = dao.fetchAll(currentPosition,currentPosition +pageSize);
					HttpSession session = request.getSession();
					session.setAttribute("currentProduct",products);
					
					response.sendRedirect("viewProducts.jsp");

			} catch(DataAccessException e) {
			     throw new ServletException("ProductControllerServlet encountered problem while accessing the dao", e);
			
	}
}

	
	

}
