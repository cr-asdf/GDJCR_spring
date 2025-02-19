package com.winter.app.products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class ProductDAO {
	
	//insert
	public int add(ProductDTO productDTO)throws Exception{
		Connection con = null;
		String sql = "INSERT INTO PRODUCTS VALUES (PRODUCTS_SEQ.NEXTVAL, ?,?,?,?)";
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, productDTO.getProductName());
		st.setDouble(2, productDTO.getProductRate());
		
		int result = st.executeUpdate();
		
		return result;
		
	}
	
	//detail
	public ProductDTO getDetail(ProductDTO productDTO)throws Exception{
		Connection con = null;
		String sql = "SELECT * FROM PRODUCTS WHERE PRODUCTNUM=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setLong(1, productDTO.getProductNum());
		
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			productDTO.setProductNum(rs.getLong("PRODUCTNUM"));
			productDTO.setProductName(rs.getString("PRODUCTNAME"));
		}else {
			productDTO = null;
		}
		
		return productDTO;
		
	}
	
	//list
	public List<ProductDTO> getList()throws Exception{
		List<ProductDTO> ar = new ArrayList<ProductDTO>();
		
		//1. Connection
		Connection con = null;
		//2. SQL 문
		String sql = "SELECT * FROM PRODUCTS ORDER BY PRODUCTNUM DESC";
		
		//3. 미리보내기
		PreparedStatement st = con.prepareStatement(sql);
		
		//4. ? 처리
		
		//5. 최종전송 및 결과 처리
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			ProductDTO productDTO = new ProductDTO();
			productDTO.setProductNum(rs.getLong("PRODUCTNUM"));
			productDTO.setProductName(rs.getString("PRODUCTNAME"));
			
			ar.add(productDTO);
		}
		
		
		//6. 연결 해제
		
		return ar;
	}

}
