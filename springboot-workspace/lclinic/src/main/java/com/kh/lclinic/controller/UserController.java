package com.kh.lclinic.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.kh.lclinic.model.vo.User;
import com.kh.lclinic.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

	private final UserService uService;
	
	@PostMapping("/login")
	public String loginUser(User user, HttpSession session) {
		
		System.out.println(user);
		// DB에서 조회했다고 가정 => session 영역에 로그인 정보 저장
		//session.setAttribute("user", user);
		User loginUser = uService.loginUser(user);
		
		if(loginUser != null) {
			session.setAttribute("user", loginUser);
		}
		
		return "redirect:/";
	}

//	@PostMapping("/social-login")
//	public ResponseEntity<LoginResponse> doSocialLogin(@RequestBody @Validated SocialLoginRequest request){
//	
//		return ResponseEntity.created(URI.create("/social-login")).body(uService.doSocialLogin(request));
//	}
//	
//	@FeignClient(value = "googleAuth", url="https://oauth2.googleapis.com", configuration = {FeignConfiguration.class})
//	public interface GoogleAuthApi {
//	    @PostMapping("/token")
//	    ResponseEntity<String> getAccessToken(@RequestBody GoogleRequestAccessTokenDto requestDto);
//	}
//	
//	@GetMapping("/errorPage")
//	public String errorPage() {
//	    return "common/errorPage"; // errorPage.html의 경로
//	}
}
