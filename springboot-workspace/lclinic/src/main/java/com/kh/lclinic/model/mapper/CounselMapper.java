package com.kh.lclinic.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.lclinic.model.vo.Counsel;

@Mapper
public interface CounselMapper {
	
	// 사용자가 등록한 상담 내역 조회
	List<Counsel> selectAllCounselByUserId(String userId);
}
