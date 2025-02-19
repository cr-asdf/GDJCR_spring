package com.winter.app.products;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	
	@Autowired
	private ProductDAO productDAO;
	
	
	
	{
		productDAO = new ProductDAO();
	}
	
	public ProductService() {
		productDAO = new ProductDAO();
	}
	
	public ProductService(@Autowired ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
	
	public void setProductDAO(@Autowired ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
	
	

	
	//list
	public List<ProductDTO> getList()throws Exception{
		List<ProductDTO> ar = productDAO.getList();
		System.out.println("Service List");
		return ar;
	}
	
	//add
	public int add(ProductDTO productDTO)throws Exception{
		//dao 호출 코드
		
		return 1;
		
	}

}







