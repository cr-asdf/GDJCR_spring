package com.winter.app.boards.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winter.app.boards.BoardDTO;
import com.winter.app.boards.BoardService;
import com.winter.app.pages.Pager;

@Service
public class QnaService implements BoardService{
	
	@Autowired
	private QnaDAO qnaDAO;
	
	public List<BoardDTO> getList(Pager pager)throws Exception{
		return qnaDAO.getList(pager);
	}
	
	public BoardDTO getDetail(BoardDTO boardDTO, boolean check)throws Exception{
		if(check) {
			qnaDAO.updateHit(boardDTO);
		}
		return qnaDAO.getDetail(boardDTO);
	}
	
	public int add(BoardDTO boardDTO)throws Exception{
		return qnaDAO.add(boardDTO);
	}
	
	public int update(BoardDTO boardDTO)throws Exception{
		return qnaDAO.update(boardDTO);
	}
	
	public int delete(BoardDTO boardDTO)throws Exception{
		return qnaDAO.delete(boardDTO);
	}
	
	public int reply(QnaDTO boardDTO)throws Exception{
		//boardDTO 답글 : 이름, 제목, 내용, 부모글 : 글번호
		QnaDTO parent = (QnaDTO) qnaDAO.getDetail(boardDTO);
		
		//ref 부모의 ref
		boardDTO.setBoardRef(parent.getBoardRef());
		//step 부모의 step+1
		boardDTO.setBoardStep(parent.getBoardStep()+1);
		//depth 부모의 depth+1
		boardDTO.setBoardDepth(parent.getBoardDepth()+1);
		
		//step update
		int result = qnaDAO.updateStep(parent);
		
		result = qnaDAO.reply(boardDTO);
		
		return result;
	}

}
