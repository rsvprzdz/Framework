package com.kh.myEditor.model.service;

import org.springframework.stereotype.Service;

import com.kh.myEditor.model.mapper.BoardMapper;
import com.kh.myEditor.model.vo.Board;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardMapper mapper;
	
	/*
	 * lombok 미사용 시
	 * 
	 * public BoardService(BoardMapper mapper){
	 * 	this.mapper = mapper
	 * }
	 */
	
	public int insertBoard(Board board) {
		return mapper.insertBoard(board);
	}

}
