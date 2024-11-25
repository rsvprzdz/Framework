package com.kh.lclinic.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.lclinic.model.mapper.CounselMapper;
import com.kh.lclinic.model.vo.Counsel;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class CounselService {
	private final CounselMapper mapper;
	
	public List<Counsel> selectAllCounselByUserId(String userId) {
	    return mapper.selectAllCounselByUserId(userId);
	}
	
}
