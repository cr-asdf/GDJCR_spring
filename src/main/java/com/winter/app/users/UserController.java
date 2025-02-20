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

}









