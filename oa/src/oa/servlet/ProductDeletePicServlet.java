package oa.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oa.service.ProductService;

@WebServlet("/product/delete-pic.action")
public class ProductDeletePicServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String sPicNo = req.getParameter("picNo");
		int picNo = -1;
		try {
			picNo = Integer.parseInt(sPicNo);
		} catch (Exception ex) {
			resp.sendRedirect("prlist.action");
			return;
		}

		ProductService prService = new ProductService();
		prService.deletePic(picNo);

		resp.sendRedirect("prlist.action");

	}
}
