package com.winter.app.notice;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.winter.app.users.UserDTO;

@Controller
@RequestMapping(value="/notice/*")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping(value="list", method = RequestMethod.GET)
	public void getList(Model model)throws Exception{
		List<NoticeDTO> ar= noticeService.getList();
		model.addAttribute("list", ar);
	}
	
	@RequestMapping(value="detail", method = RequestMethod.GET)
	public void getDetail(NoticeDTO noticeDTO, Model model)throws Exception{
		noticeDTO= noticeService.getDetail(noticeDTO);
		model.addAttribute("dto", noticeDTO);
	}
	@RequestMapping(value="add", method = RequestMethod.GET)
	public void add()throws Exception{
		
	}
	
	@RequestMapping(value="add", method = RequestMethod.POST)
	public String add(NoticeDTO noticeDTO, HttpSession session)throws Exception{
		
		UserDTO userDTO = (UserDTO)session.getAttribute("user");
		noticeDTO.setUserName(userDTO.getUserName());
		int result = noticeService.add(noticeDTO);
		
		return "redirect:./list";
	}
	
	@RequestMapping(value="update", method = RequestMethod.GET)
	public void update(NoticeDTO noticeDTO, Model model)throws Exception{
		noticeDTO = noticeService.getDetail(noticeDTO);
		model.addAttribute("dto", noticeDTO);
	}
	
	@RequestMapping(value="update", method = RequestMethod.POST)
	public String update(NoticeDTO noticeDTO)throws Exception{
		int result =  noticeService.update(noticeDTO);
		
		//return "redirect:./list";
		return "redirect:./detail?boardNum="+noticeDTO.getBoardNum();
		
	}
	
	@RequestMapping(value="delete", method = RequestMethod.GET)
	public String delete(NoticeDTO noticeDTO, Model model)throws Exception{
		int result = noticeService.delete(noticeDTO);
		String s = "삭제 실패";
		if(result>0) {
			s = "삭제 성공";
		}
		model.addAttribute("result", s);
		model.addAttribute("path", "./list");
		
		return "commons/result";
		
	}

}
