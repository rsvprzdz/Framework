package com.kh.mybatis.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mybatis.member.model.vo.Member;
import com.kh.mybatis.member.service.MemberServiceImpl;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login.me")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 전달된 데이터 => 아이디, 비밀번호
		String id = request.getParameter("userId");
		String pwd = request.getParameter("userPwd");
		
		Member m = new Member();
		m.setUserId(id);
		m.setUserPwd(pwd);
		
		//MemberService mService = new MemberServiceImpl();
		Member loginUser = new MemberServiceImpl().loginMember(m);
		
		if(loginUser != null) {
		// 로그인 성공 => 로그인 성공했습니다.
		//			=> session 영역에 로그인 정보 저장
		//			=> url 재요청 (/mabatis)
		HttpSession session = request.getSession();
		session.setAttribute("alertMsg", "로그인 성공했습니다.");
		session.setAttribute("loginUser", loginUser);
		
		response.sendRedirect( request.getContextPath() );
		
		} else {
			// 로그인 실패	=> 에러페이지로 응답(포워딩)
			//			=> request 영역에 에러메시지 저
			request.setAttribute("errorMsg", "로그인 실패했습니다.");
			request.getRequestDispatcher("WEB-INF/views/common/erorPage.jsp").forward(request, response);
			
		}
	}

}
