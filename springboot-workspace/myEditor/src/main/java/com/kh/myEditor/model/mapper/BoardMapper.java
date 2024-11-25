package com.kh.myEditor.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kh.myEditor.model.vo.Board;

@Mapper
public interface BoardMapper {
	int insertBoard(Board b);
}
