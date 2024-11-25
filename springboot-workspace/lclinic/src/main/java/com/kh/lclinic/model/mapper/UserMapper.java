package com.kh.lclinic.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kh.lclinic.model.vo.User;

// 추상메소드랑 연결이 된다고 보면됨
@Mapper
public interface UserMapper {
	
	User loginUser(String id, String password);
	
}
