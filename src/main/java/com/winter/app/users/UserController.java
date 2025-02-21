package com.winter.app.users;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/users/*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="join", method = RequestMethod.GET)
	public void join()throws Exception{
		
	}
	
	@RequestMapping(value="join", method = RequestMethod.POST)
	public String join(UserDTO userDTO)throws Exception{
		int result = userService.join(userDTO);
		return "redirect:/";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public void login()throws Exception{}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(UserDTO userDTO, HttpSession session, Model model)throws Exception{
		userDTO = userService.login(userDTO);
		if(userDTO != null) {
			session.setAttribute("user", userDTO);
			return "redirect:../";
		}
		model.addAttribute("result", "로그인 실패");
		model.addAttribute("path","./login");
		return "commons/result";
	}
	
	@RequestMapping(value="logout", method = RequestMethod.GET)
	public String logout(HttpSession session)throws Exception{
		//1. user속성 null
		session.setAttribute("user", null);
		
		//2. user속성 삭제
		session.removeAttribute("user");
		
		//3. session 삭제(소멸), 유지시간을 0으로 세팅
		session.invalidate();
		
		return "redirect:../";
		
	}
	
	@RequestMapping(value = "mypage", method = RequestMethod.GET)
	public void mypage()throws Exception{
		//1. Session에 user정보
		
		//2. 유저정보를 다시 조회 해서 jsp로 보내는 방법
		
		
	}

}









