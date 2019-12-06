package oa.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Product {

	private int productNo;
	private String productName;
	private String price;
	private Date regDate;
	private String content;
	private int sales;
	private boolean active;
	
	private List<ProductPic> productPics;
	private List<ProductManual> productManuals;
	
}
