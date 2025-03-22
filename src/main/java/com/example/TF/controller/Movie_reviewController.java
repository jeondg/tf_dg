package com.example.TF.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TF.dto.Movie_reviewDTO;
import com.example.TF.entity.Movie_review;
import com.example.TF.service.Movie_MemberService;

@RestController
@RequestMapping("/movieinfo")
public class Movie_reviewController {

    @Autowired
    private Movie_MemberService memberService;
    
    //답글
    @GetMapping("/review")
    public List<Movie_review> getReviewList(@RequestParam("start") int startNum,
                                            @RequestParam("end") int endNum) {
        return memberService.review_list(startNum, endNum);
    }
    
    @GetMapping("/review/count")
    public int getReviewCount() {
        return memberService.review_count();
    }
    
    @PostMapping("/review")
    public Movie_review writeReview(@RequestBody Movie_reviewDTO dto) {
    	dto.setLogtime(new Date());
    	System.out.println("제발 " + dto.getLogtime());
        return memberService.writereview(dto);
    }    
}
