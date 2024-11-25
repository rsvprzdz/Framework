package com.kh.lclinic.service;

import org.springframework.stereotype.Service;

import com.kh.lclinic.model.mapper.UserMapper;
import com.kh.lclinic.model.vo.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor	//롬복사용하고있으니 생성자주입방식으로.. di
public class UserService {
	private final UserMapper mapper;
	
	public User loginUser(User user) {
		return mapper.loginUser(user.getId(), user.getPassword());
	}
}
