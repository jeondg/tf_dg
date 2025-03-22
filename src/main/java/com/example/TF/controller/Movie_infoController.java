package com.example.TF.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.TF.dto.Movie_infoDTO;
import com.example.TF.entity.Movie_info;
import com.example.TF.service.Movie_MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class Movie_infoController {
	@Autowired
	Movie_MemberService service;
	
	// "D:/JDG/spring_boot/workspace/static/movie_poster"
	@Value("${project.upload.movie_poster.path}")
	private String uploadpath;
	
	// 영화정보보기
	@GetMapping("/movieinfo/moviedetail")
	public String moviedetail(@RequestParam(name = "moviecode", defaultValue = "0") int moviecode,
			Movie_infoDTO dto, Model model, HttpSession session) {

		Movie_info movie_info = service.movie_info_view(dto.getMoviecode());
		
		String memid = (String) session.getAttribute("memId");
		model.addAttribute("sessionMemid", memid);
		model.addAttribute("moviecode", moviecode);
		model.addAttribute("movie_info", movie_info);
		
		return "/movieinfo/moviedetail";
	}
}
