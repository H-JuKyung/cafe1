package com.shop.cafe.controller;

import java.util.HashMap; 
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shop.cafe.dto.Member;
import com.shop.cafe.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
//@CrossOrigin("http://127.0.0.1:8080/")
public class MemberController { // 브라우저에서 요청을 할때 제일 먼저 받는 곳
	
	@Autowired
	MemberService memberService;
	
	@PostMapping("login")
	public Map<String, String> login(@RequestBody Member m, HttpServletRequest request) { // request == 내장객체
		System.out.println(m);
		Map<String, String> responseData = new HashMap();
		try {
			m = memberService.login(m);
			if(m!=null) { // login ok
				HttpSession session = request.getSession(); // 처음엔 빈 session이 만들어짐
				System.out.println(session.getId());
				session.setAttribute("member", m); // 1번 세션을 할당 받았다면 키,값 형태로 저장되는데 key = member / value = 100번지(주소값)
				responseData.put("msg", "ok");
			} else { // login fail
				responseData.put("msg", "다시 로그인 해주세요");
			}
		} catch (Exception e) { // login error
			e.printStackTrace();
			responseData.put("msg", "다시 로그인 해주세요");
		}
		return responseData;
	}

	@PostMapping("insertMember")
	public Map<String, String> insertMember(@RequestBody Member m) {
		Map<String, String> responseData = new HashMap();
		try {
			memberService.insertMember(m);
			responseData.put("msg", "ok");
		} catch (Exception e) {
			e.printStackTrace();
			responseData.put("msg", e.getMessage());
		}
		return responseData;
	}
}
