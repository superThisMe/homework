package oa.service;

import java.util.List;

import oa.dao.ProductDao;
import oa.vo.Product;
import oa.vo.ProductManual; 
import oa.vo.ProductPic;

public class ProductService {

	private ProductDao prDao = new ProductDao();
	
	public List<Product> findAll() {
		
		List<Product> prList = prDao.selectProduct();
		
		return prList;
	}

	public void writeProduct(Product pr) {
		int newProductNo = prDao.insertProduct(pr);
		
		for (ProductManual manual : pr.getProductManuals()) {
			manual.setProductNo(newProductNo);
			prDao.insertProductManual(manual);
		}
		for (ProductPic pic : pr.getProductPics()) {
			pic.setProductNo(newProductNo);
			prDao.insertProductPic(pic);
		}
		
	}

	public Product findByProductNo(int prNo) {
		Product pr = prDao.selectProductByPrNo(prNo);
		List<ProductManual> manuals = prDao.selectManualByPrNo(prNo);
		List<ProductPic> pics = prDao.selectPicByPrNo(prNo);
		pr.setProductManuals(manuals);
		pr.setProductPics(pics);
		
		return pr;
	}

}
