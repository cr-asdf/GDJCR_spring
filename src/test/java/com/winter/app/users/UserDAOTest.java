package com.winter.app.users;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.winter.app.SampleTestCase;


public class UserDAOTest extends SampleTestCase {

	@Autowired
	private UserDAO userDAO;
	
	@Test
	public void getDetailTest()throws Exception{
		UserDTO userDTO = new UserDTO();
		userDTO.setUserName("id11111");
		userDTO = userDAO.getDetail(userDTO);
		
		assertNotNull(userDTO);
	}
	
	@Test(expected = Exception.class)
	public void joinTest()throws Exception{
		UserDTO userDTO = new UserDTO();
		userDTO.setUserName("id");
		userDTO.setPassword("pw");
		userDTO.setName("name");
		userDTO.setPhone("1234");
		userDTO.setEmail("email");
		
		int result = userDAO.join(userDTO);
		
		//단정문 assert
		assertNotEquals(1, result);
		
	}

}
