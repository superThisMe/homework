package oa.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import oa.service.ProductService;
import oa.util.Util;
import oa.vo.Product;
import oa.vo.ProductManual;
import oa.vo.ProductPic;

@WebServlet("/product/update.action")
public class ProductUpdateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String sPrNo = req.getParameter("productNo");
		int prNo = -1;
		try {
			prNo = Integer.parseInt(sPrNo);
		} catch (Exception ex) { // 잘못된 자료번호인 경우
			resp.sendRedirect("prlist.action"); // 다시 목록으로 이동
			return;
		}

		ProductService prService = new ProductService();
		Product pr = prService.findByProductNo(prNo);

		if (pr == null) { // 조회 실패
			resp.sendRedirect("prlist.action");
			return;
		}

		// 3. JSP에서 사용할 수 있도록 조회된 데이터를 request 객체에 저장
		req.setAttribute("product", pr);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/product/prupdate.jsp");
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		if (ServletFileUpload.isMultipartContent(req) == false) {
			resp.sendRedirect("prlist.action");
			return;
		}

		ServletContext application = req.getServletContext();
		String path = application.getRealPath("/upload-files");
		String tempPath = application.getRealPath("/upload-temp");

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024 * 1024 * 5);
		factory.setRepository(new File(tempPath));

		ServletFileUpload uploader = new ServletFileUpload(factory);
		uploader.setFileSizeMax(1024 * 1024 * 50);

		Product pr = new Product();
		ArrayList<ProductPic> pics = new ArrayList<>();
		ArrayList<ProductManual> manuals = new ArrayList<>();

		try {
			List<FileItem> items = uploader.parseRequest(req);

			for (FileItem item : items) {
				if (item.isFormField()) {
					if (item.getFieldName().equals("productName")) {
						pr.setProductName(item.getString("UTF-8"));
					} else if (item.getFieldName().equals("productNo")) {
						String sPrNo = item.getString("UTF-8");
						pr.setProductNo(Integer.parseInt(sPrNo));
					} else if (item.getFieldName().equals("price")) {
						pr.setPrice(Integer.parseInt(item.getString("UTF-8")));
					} else if (item.getFieldName().equals("sales")) {
						pr.setSales(Integer.parseInt(item.getString("UTF-8")));
					} else if (item.getFieldName().equals("content")) {
						pr.setContent(item.getString("UTF-8"));
					}
				} else { // file인 경우
					String fileName = item.getName();
					if (fileName != null && fileName.length() > 0) {
						if (fileName.contains("\\")) {
							fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
						}

						String uniqueFileName = Util.makeUniqueFileName(fileName);
						item.write(new File(path, uniqueFileName));
						item.delete();

						if (fileName.contains(".pdf")) {
							ProductManual prManual = new ProductManual();
							prManual.setUserManualName(fileName);
							prManual.setSavedManualName(uniqueFileName);

							manuals.add(prManual);
						} else {
							ProductPic prPic = new ProductPic();
							prPic.setUserPicName(fileName);
							prPic.setSavedPicName(uniqueFileName);

							pics.add(prPic);
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		pr.setProductManuals(manuals);
		pr.setProductPics(pics);

		ProductService prService = new ProductService();
		prService.updateProduct(pr);

		resp.sendRedirect("prlist.action");

	}
}
