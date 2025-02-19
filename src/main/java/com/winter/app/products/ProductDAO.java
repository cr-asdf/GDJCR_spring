package com.winter.app.products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAO {
	
	@Autowired
	private SqlSession sqlSession;
	private final String NAMESPACE="com.winter.app.products.ProductDAO.";
	
	//insert
	public int add(ProductDTO productDTO)throws Exception{
		return sqlSession.insert(NAMESPACE+"add", productDTO);
		
	}
	
	//detail
	public ProductDTO getDetail(ProductDTO productDTO)throws Exception{
		//statement => mapper의 id값
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("pn", pn);
//		map.put("name", name);
		
		return sqlSession.selectOne(NAMESPACE+"getDetail", productDTO);
		
		
		
		
		
	}
	
	//list
	public List<ProductDTO> getList()throws Exception{
			
		return sqlSession.selectList(NAMESPACE+"getList");
	}

}
