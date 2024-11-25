package com.kh.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class PracticeController {

	@GetMapping("/practice")
	public String practicePage(Model m, HttpSession session) {
		
		// request scope에 데이터 저장 => Model 
		m.addAttribute("name", "허완");
		m.addAttribute("title", "<h3>Hello, Thymeleaf~</h3>");
		
		// session scope에 데이터 저장 => HttpSession
		session.setAttribute("age", 34);
		
		
		return "practice";	// templates/"practice".html
	}
	
	@GetMapping("/test")
	public String testPage() {
		return "main";
	}
}
