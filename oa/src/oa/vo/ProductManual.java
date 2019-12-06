package oa.vo;

import lombok.Data;

@Data
public class ProductManual {

	private int manualNo;
	private int productNo;
	private String savedManualName;
	private String userManualName;
	private boolean active;
	
}
