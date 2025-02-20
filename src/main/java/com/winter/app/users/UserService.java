package com.winter.app.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	
	public int join(UserDTO userDTO)throws Exception{
		return userDAO.join(userDTO);
	}
	
	public UserDTO login(UserDTO userDTO)throws Exception{
		//result는 username(id) 만 비교 함
		UserDTO result = userDAO.getDetail(userDTO);
		
		if(result != null) {
			if(result.getPassword().equals(userDTO.getPassword())) {
				return result;
			}
		}
		
		return null;
		
	}

}
