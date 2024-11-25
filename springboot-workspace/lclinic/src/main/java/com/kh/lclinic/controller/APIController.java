package com.kh.lclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.lclinic.model.vo.User;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class APIController {

	@RequestMapping("/login/oauth/google")
	public String googleLoginCallback(@RequestParam(value="error", defaultValue="") String error
									, @RequestParam(value="code", defaultValue="") String code
									, @RequestParam(value="scope", defaultValue="") String scope
									, HttpSession session) {
		
		log.info("error : {}", error);
		log.info("code : {}", code);
		log.info("scope: {}", scope);
		
		if(code != null && !code.isEmpty()) {
			User user = new User();
			user.setId("구글 로그인 계정");
			
			// 인증을 받고, 인증받은 값으로 사용자의 정보를 요청해야함, 조회하고 DB에 저장해야함
			// 그리고 나서 로그인 처리
			
			session.setAttribute("user", user);
		}
		
		
		return "redirect:/";
	}
}
