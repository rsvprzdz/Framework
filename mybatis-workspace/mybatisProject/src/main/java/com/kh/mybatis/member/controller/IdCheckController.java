package com.kh.mybatis.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.member.service.MemberServiceImpl;

/**
 * Servlet implementation class IdCheckController
 */
@WebServlet("/idCheck.me")
public class IdCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdCheckController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청 시 전달된 데이터 추출 => 아이디
		String userId = request.getParameter("userId");
		 
		System.out.println(userId);
		
		//MemberService에게 아이디를 전달하여 중복되는 데이터인지 확인 요청
		int count = new MemberServiceImpl().idCheck(userId);
		
		// 중복되는 데이터인 경우 "NNN" 응답
		if (count > 0) {
			response.getWriter().print("NNN");
		} else {
			response.getWriter().print("NNY");
		}
		// 중복되지 않는 데이터인 경우 "NNY" 응답
		
//		response.getWriter().print("ok");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
