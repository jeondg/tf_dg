package com.example.TF.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TF.dao.Movie_MemberDAO;
import com.example.TF.dao.Movie_notice_board_DAO;
import com.example.TF.dao.Movie_question_boardDAO;
import com.example.TF.dao.Movie_user_qna_board_DAO;
import com.example.TF.dto.Movie_MemberDTO;
import com.example.TF.dto.Movie_question_boardDTO;
import com.example.TF.entity.Movie_Member;
import com.example.TF.entity.Movie_notice_board;
import com.example.TF.entity.Movie_question_board;
import com.example.TF.entity.Movie_user_qna_board;


@Service
public class Movie_MemberService {
	@Autowired
	Movie_MemberDAO member_dao;
	@Autowired
	Movie_notice_board_DAO notice_dao;
	@Autowired
	Movie_user_qna_board_DAO qna_dao;
	@Autowired
	Movie_question_boardDAO question_dao;
	
	// 로그인
	public Movie_Member login(String id, String pwd) {
		return member_dao.login(id, pwd);
	}
	// 회원 정보 저장
	public Movie_Member write(Movie_MemberDTO dto) {
		return member_dao.write(dto);
	}
	// id 중복검사
	public boolean isExistId(String id) {
		return member_dao.isExistId(id);
	}
	// 1명 회원 정보 읽기
	public Movie_Member getMember(String id) {
		return member_dao.getMember(id);
	}
	// 수정하기
	public boolean modify(Movie_MemberDTO dto) {
		return member_dao.modify(dto);
	}
	
	////////// 공지 ///////////
	
	// 목록 보기
	public List<Movie_notice_board> notice_board_list(int startNum, int endNum) {
		return notice_dao.notice_board_list(startNum, endNum);
	}
	
	// 총글수
	public int notice_get_count() {
		return notice_dao.get_count();
	}
		
	// 상세보기 
	public Movie_notice_board notice_board_view(int seq) {
		return notice_dao.notice_board_view(seq);
	}
	
	////////// 자주찾는 질문 /////////
	
	// 전체 목록 보기
	public List<Movie_user_qna_board> qna_list(int startNum, int endNum) {
		return qna_dao.qna_list(startNum, endNum);
	}
	
	// 총글수
	public int qna_get_count() {
		return qna_dao.get_count();
	}
	
	// 항목별 글수
	public int qna_get_count_section(String section) {
		return qna_dao.get_count_section(section);
	}
	
	// 개별 목록 보기
	public List<Movie_user_qna_board> qna_list_section(String section, int startNum, int endNum) {
		return qna_dao.qna_list_section(section, startNum, endNum);
	}
	
	// 글 보기
	public Movie_user_qna_board qna_board_view(int seq) {
		return qna_dao.qna_board_view(seq);
	}
	
	//////////// 문의하기 ///////////

	// 글 저장
	public Movie_question_board question_board_write(Movie_question_boardDTO dto) {
		return question_dao.question_board_write(dto);
	}
	
	// 상세보기
	public Movie_question_board question_board_view(int seq) {
		return question_dao.question_board_view(seq);
	}
	
	// 글 삭제
	public boolean question_board_delete(int seq) {
		return question_dao.question_board_delete(seq);
	}
	
	// 총 글수
	public int get_count() {
		return question_dao.get_count();
	}
	
	// 개인별 글수
	public int  get_count_qeustion_id(String question_id) {
		return question_dao.get_count_question_id(question_id);
	}
	
	// 전체 목록
	public List<Movie_question_board> question_list(int startNum, int endNum){
		return question_dao.question_list(startNum, endNum);
	}
	
	// 개별 목록
	public List<Movie_question_board> question_list_question_id(String question_id, int startNum, int endNum){
		return question_dao.question_list_question_id(question_id, startNum, endNum);
	}
}
