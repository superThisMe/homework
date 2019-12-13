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

@WebServlet("/product/search.action")
public class ProductSearchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ProductService prService = new ProductService();
		
		String set = req.getParameter("searchSet");
		String word = req.getParameter("keyword");
		List<Product> prList = null;
		
		
		if (set.equals("productName")) {
			prList = prService.SearchByPrName(word);
		} else {
			prList = prService.SearchByPrContent(word);
		}
		
		req.setAttribute("prList", prList);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/product/searchlist.jsp");
		dispatcher.forward(req, resp);
		
	}
	
}
