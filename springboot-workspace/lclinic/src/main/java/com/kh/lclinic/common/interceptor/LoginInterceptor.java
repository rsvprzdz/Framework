package com.kh.lclinic.common.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
		
		if (session.getAttribute("user") != null) {
			// 기존방식대로 동작
			return true;
		} else {
			session.setAttribute("errorMsg", "로그인 후 이용 가능합니다.");		// 메시지 출력을 위해 데이터 저장
			response.sendRedirect(request.getContextPath()+"/errorPage");			// 에러페이지로 url 재요청
			
			return false;
		}
	}
}
