package oa.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oa.service.ProductService;
import oa.vo.Product;

@WebServlet("/product/prdetail.action")
public class ProductDatailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String sPrNo = req.getParameter("productNo");
		int prNo = -1;
		try {
			prNo = Integer.parseInt(sPrNo);
		} catch (Exception e) {
			resp.sendRedirect("prlist.action");
			return;
		}
		
		ProductService prService = new ProductService();
		Product pr = prService.findByProductNo(prNo);
		
		if (pr == null) {
			resp.sendRedirect("prlist.action");
			return;
		}
		
		req.setAttribute("Product", pr);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/product/prdetail.jsp");
		dispatcher.forward(req, resp);		
		
	}
	
}
