package oa.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oa.service.ProductService;
import oa.vo.Product;

@WebServlet("/product/prlist.action")
public class ProductListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ProductService prService = new ProductService();
		List<Product> prList = prService.findAll();
		
		req.setAttribute("prList", prList);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/product/prlist.jsp");
		dispatcher.forward(req, resp);
		
	}
	
}
