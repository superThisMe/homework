package oa.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oa.service.ProductService;

@WebServlet("/product/delete.action")
public class ProductDeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String sPrNo = req.getParameter("productNo");
		int prNo = -1;
		try {
			prNo = Integer.parseInt(sPrNo);
		} catch (Exception ex) { // 잘못된 자료번호인 경우
			resp.sendRedirect("prlist.action"); // 다시 목록으로 이동
			return;
		}

		// 3. 서비스 객체에 삭제 요청 (UploadService)
		ProductService prService = new ProductService();
		prService.delete(prNo);

		// 4. 목록으로 이동
		resp.sendRedirect("prlist.action");

	}
}
