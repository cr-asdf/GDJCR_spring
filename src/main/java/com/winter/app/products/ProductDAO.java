package com.winter.app.products;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class ProductDAO {
	
	//list
	public List<ProductDTO> getList()throws Exception{
		System.out.println("DAO LIST");
		return null;
	}

}
