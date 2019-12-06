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

@WebServlet("/product/reg.action")
public class ProductRegServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/product/reg.jsp");
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
					} else if (item.getFieldName().equals("price")) {
						pr.setPrice(item.getString("UTF-8"));
					} else if (item.getFieldName().equals("content")) {
						pr.setContent(item.getString("UTF-8"));
					}
				} else {
					String fileName = item.getName();
					if (fileName != null && fileName.length() > 0) {
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
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		pr.setProductManuals(manuals);
		pr.setProductPics(pics);
		
		ProductService prService = new ProductService();
		prService.writeProduct(pr);
		
		resp.sendRedirect("prlist.action");
	}
	
}
