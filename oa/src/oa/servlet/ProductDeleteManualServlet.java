package oa.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oa.service.ProductService;

@WebServlet("/product/delete-manual.action")
public class ProductDeleteManualServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String sManualNo = req.getParameter("manualNo");
		int manualNo = -1;
		try {
			manualNo = Integer.parseInt(sManualNo);
		} catch (Exception ex) {
			resp.sendRedirect("prlist.action");
			return;
		}

		ProductService prService = new ProductService();
		prService.deleteManual(manualNo);

		resp.sendRedirect("prlist.action");

	}
}
