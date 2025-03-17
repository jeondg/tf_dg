package com.example.TF.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.TF.dto.Movie_MemberDTO;
import com.example.TF.entity.Movie_Member;
import com.example.TF.service.Movie_MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class Movie_MemberController {
	@Autowired
	Movie_MemberService service;
	
	@GetMapping("/customerServiceMain")
	public String customerServiceMain(Model model, HttpSession session) {
		
		// 2. 데이터 공유
		model.addAttribute("req2", "none");
		
		// 3. view 파일명 리턴
		return "/main/customerServiceMain";
		
	}
	
	@GetMapping("/mypageMain")	// mypage가기
	public String mypageMain(Model model, Movie_MemberDTO dto, HttpSession session) {
		// 1. 데이터 처리
		String id = (String)session.getAttribute("memId");
		// 회원정보 읽기
		Movie_Member member = service.getMember(id);
		// 2. 데이터 공유
		model.addAttribute("member", member);
		model.addAttribute("req", "none");
		return "/main/mypageMain";
	}
	// http://localhost:8080/main
	@GetMapping("/main")	// 메인가기
	public String main() {
		return "/main/main";
	}
	
	// http://localhost:8080/member/loginForm
	@GetMapping("/member/loginForm")	// 로그인폼
	public String loginForm() {
		return "/member/loginForm";
	}
	
	@PostMapping("/member/login")
	public String login(Movie_MemberDTO dto, HttpSession session) {
		// db
		Movie_Member member = service.login(dto.getId(), dto.getPwd());
		// 2. 데이터 공유
		// 3. view 파일명 리턴
		if(member != null) { // 로그인 성공
			session.setAttribute("memId", member.getId());
			session.setAttribute("memName", member.getName());
			return "/member/loginOK";	// /member/loginOK.html
		} else {			// 로그인 실패
			
		}
		return "/member/loginFail";	// /member/loginFail.html
	}
	
	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		// 1. 데이터 처리
		session.removeAttribute("memId");
		session.removeAttribute("memName");
		// 2. 데이터 공유
		// 3. view 파일명 리턴
		return "/member/logout"; // /member/logout.html
	}	
	
	@GetMapping("/member/writeForm")
	public String writeForm() {
		// 1. 데이터 처리
		// 2. 데이터 공유
		// 3. view 파일명 리턴
		return "/member/writeForm"; // /member/writeForm.html
	}	
	
	@PostMapping("/member/write")
	public String write(Movie_MemberDTO dto, Model model) {
		// 1. 데이터 처리
		dto.setLogtime(new Date());
		dto.setGrade("basic");
		dto.setVippoint(0);
		dto.setPoint(0);
		// db
		Movie_Member member = service.write(dto);
		// 2. 데이터 공유
		model.addAttribute("member",member);
		// 3. view 파일명 리턴
		return "/member/write"; // /member/write.html
	}
	
	@GetMapping("/member/checkId")
	public String checkId(@RequestParam("id") String id, Model model) {
		// 1. 데이터 처리
		// db
		boolean result = service.isExistId(id);
		// 2. 데이터 공유
		model.addAttribute("id", id);
		model.addAttribute("result", result);
		// 3. view 파일명 리턴
		return "/member/checkId"; // /member/checkId
	}
	
	@GetMapping("/member/modifyForm")
	public String modifyForm(HttpSession session, Model model) {
		// 1. 데이터 처리
		String id = (String)session.getAttribute("memId");
		// 회원정보 읽기
		Movie_Member member = service.getMember(id);
		// 2. 데이터 공유
		model.addAttribute("member", member);
		model.addAttribute("req", "/member/modifyForm");
		// 3. view 파일명 리턴
		return "/main/mypageMain"; 
	}
	
	@PostMapping("/member/modify")
	public String modify(Movie_MemberDTO dto, Model model) {
		// 1. 데이터 처리		
		// db
		boolean result = service.modify(dto);
		System.out.println("result = "+ result);
		// 2. 데이터 공유
		model.addAttribute("result", result);
		// 3. view 파일명 리턴
		return "/member/modify"; // /member/modify.html
	}
	
	@PostMapping("/member/delete")
	public String delete(HttpSession session , Model model) {
	    // 1. 세션에서 id 먼저 가져오기
	    String id = (String) session.getAttribute("memId");
	    
	    if(id != null) {
		    // 서비스 호출해서 탈퇴 처리
		    String result = service.delete((String) session.getAttribute("memId"));
	        if ("삭제성공".equals(result)) {
	            session.invalidate(); // 세션 완전 초기화
				// 아래것들 한번에
				//session.removeAttribute("memId");
				//session.removeAttribute("memName");
			    model.addAttribute("result",result);
	        }	

			return "/member/delete";
	    }
	    // id가 없을 때 (예외 처리)
	    model.addAttribute("result", "탈퇴 실패: 로그인 정보가 없습니다.");
	    return "/member/delete";	    

	}
}
