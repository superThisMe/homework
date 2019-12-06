package oa.vo;

import lombok.Data;

@Data
public class ProductPic {

	private int picNo;
	private int productNo;
	private String savedPicName;
	private String userPicName;
	private boolean active;
	
}
