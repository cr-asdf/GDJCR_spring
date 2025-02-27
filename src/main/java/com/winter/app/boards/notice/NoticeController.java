package com.winter.app.boards.notice;

import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.winter.app.boards.BoardDTO;
import com.winter.app.pages.Pager;
import com.winter.app.users.UserDTO;

@Controller
@RequestMapping(value="/notice/*")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping(value="list", method = RequestMethod.GET)
	public void getList(Pager pager, Model model)throws Exception{
		List<BoardDTO> ar= noticeService.getList(pager);
		model.addAttribute("list", ar);
	}
	
	@RequestMapping(value="detail", method = RequestMethod.GET)
	public void getDetail(BoardDTO boardDTO, Model model, HttpSession session)throws Exception{
		//"board" : set(글번호들,,)
		Object obj = session.getAttribute("board");
		boolean check=false;
		if(obj != null) {
			HashSet<Long> ar = (HashSet<Long>)obj;
			if(!ar.contains(boardDTO.getBoardNum())) {
				check=true;
			}else {
				ar.add(boardDTO.getBoardNum());
			}
		}else {
			HashSet<Long> num = new HashSet<Long>();
			num.add(boardDTO.getBoardNum());
			session.setAttribute("board", num);
			check=true;
		}
		
		boardDTO= noticeService.getDetail(boardDTO, check);
		model.addAttribute("dto", boardDTO);
	}
	@RequestMapping(value="add", method = RequestMethod.GET)
	public void add()throws Exception{
		
	}
	
	@RequestMapping(value="add", method = RequestMethod.POST)
	public String add(BoardDTO boardDTO, HttpSession session)throws Exception{
		
		UserDTO userDTO = (UserDTO)session.getAttribute("user");
		boardDTO.setUserName(userDTO.getUserName());
		int result = noticeService.add(boardDTO);
		
		return "redirect:./list";
	}
	
	@RequestMapping(value="update", method = RequestMethod.GET)
	public void update(BoardDTO boardDTO, Model model)throws Exception{
		boardDTO = noticeService.getDetail(boardDTO, false);
		model.addAttribute("dto", boardDTO);
	}
	
	@RequestMapping(value="update", method = RequestMethod.POST)
	public String update(BoardDTO boardDTO)throws Exception{
		int result =  noticeService.update(boardDTO);
		
		//return "redirect:./list";
		return "redirect:./detail?boardNum="+boardDTO.getBoardNum();
		
	}
	
	@RequestMapping(value="delete", method = RequestMethod.GET)
	public String delete(BoardDTO boardDTO, Model model)throws Exception{
		int result = noticeService.delete(boardDTO);
		String s = "삭제 실패";
		if(result>0) {
			s = "삭제 성공";
		}
		model.addAttribute("result", s);
		model.addAttribute("path", "./list");
		
		return "commons/result";
		
	}

}
