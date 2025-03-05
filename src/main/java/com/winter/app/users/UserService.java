package com.winter.app.users;

import java.io.File;
import java.util.Calendar;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	
	public int join(UserDTO userDTO, MultipartFile proFile, ServletContext context)throws Exception{
		int result = userDAO.join(userDTO);
		if(proFile.isEmpty()) {
			return result;
			
		}
		
		
		//1. 어디에 저장 할 것인가
		String path = context.getRealPath("/resources/images/profiles/");
		System.out.println(path);
		
		File file = new File(path);
		
		if(!file.exists()) {
			file.mkdirs();	
		}
		//2. 어떤 파일을 어떤 이름으로 저장
		// 	시간
//		Calendar ca = Calendar.getInstance();
//		 long mil = ca.getTimeInMillis(); 
//		String f = proFile.getOriginalFilename();
//		f = f.substring(f.lastIndexOf("."));
//		f = mil+f;
		
		// 2) 객체 사용
		String f = UUID.randomUUID().toString();
		f = f+" "+proFile.getOriginalFilename();
		
		//3. HDD에 저장
				//1) transferTo
		file = new File((file, f);
		//proFile.transferTo(file);
		
				//2) FileCopyUtils
		FileCopyUtils.copy(proFile.getBytes(), file);
		
	
		
		UserFileDTO userFileDTO = new UserFileDTO();
		userFileDTO.setUserName(userDTO.getUserName());
		userFileDTO.setFileName(f);
		userFileDTO.setOldName(proFile.getOriginalFilename());
		userDAO.upload(userFileDTO);
		return result;
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
