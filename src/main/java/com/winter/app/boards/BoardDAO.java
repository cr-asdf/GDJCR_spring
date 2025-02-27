package com.winter.app.boards;

import java.util.List;

import com.winter.app.pages.Pager;

public interface BoardDAO {
	//상수, 추상메서드
	
	
	//totalCount
	public abstract Long getTotalCount(Pager pager)throws Exception;	
	
	//list
	public List<BoardDTO> getList(Pager pager)throws Exception;
	
	
	//detail
	BoardDTO getDetail(BoardDTO boardDTO)throws Exception;
	
	//hitupdate
	public int updateHit(BoardDTO boardDTO)throws Exception;
	
	//add
	public int add (BoardDTO boardDTO)throws Exception;
	
	//update
	public int update (BoardDTO boardDTO) throws Exception;
	
	//delete
	public int delete(BoardDTO boardDTO)throws Exception;
	

}
