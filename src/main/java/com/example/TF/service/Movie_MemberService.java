package com.example.TF.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TF.dao.Movie_MemberDAO;
import com.example.TF.dao.Movie_infoDAO;
import com.example.TF.dao.Movie_notice_board_DAO;
import com.example.TF.dao.Movie_question_boardDAO;
import com.example.TF.dao.Movie_reviewDAO;
import com.example.TF.dao.Movie_user_qna_board_DAO;
import com.example.TF.dto.Movie_MemberDTO;
import com.example.TF.dto.Movie_question_boardDTO;
import com.example.TF.dto.Movie_reviewDTO;
import com.example.TF.entity.Movie_Member;
import com.example.TF.entity.Movie_info;
import com.example.TF.entity.Movie_notice_board;
import com.example.TF.entity.Movie_question_board;
import com.example.TF.entity.Movie_review;
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
	@Autowired
	Movie_infoDAO info_dao;
	@Autowired
	Movie_reviewDAO review_dao;
	
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
	public String delete(String id) {
		return member_dao.deleteMember(id);
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
	
	///////////////////영화정보////////////////////
	
	// 총영화수
	public int getCount() {
		return info_dao.get_count();
	}
	// 장르별 영화개수
	public int getgenreCount(String genre) {
		return info_dao.get_count_genre(genre);
	}
	// 국내영화수
	public int getcountryCount() {
		return info_dao.get_country();
	}
	// 해외영화수
	public int getforeignCount() {
		return info_dao.get_foreign();
	}
	
	
	// 장르별목록
	public List<Movie_info> info_list_genre(String genre, int startNum, int endNum){
		return info_dao.genrerank_list(genre, startNum, endNum);
	}
	// 국내별목록
	public List<Movie_info> info_list_country(int startNum, int endNum){
		return info_dao.countryrank_list(startNum, endNum);
	}
	// 해외별목록
	public List<Movie_info> info_list_foreign(int startNum, int endNum){
		return info_dao.foreignrank_list(startNum, endNum);
	}
	// 박스오피스목록
	public List<Movie_info> info_list_boxoffice(int startNum, int endNum){
		return info_dao.boxoffice_list(startNum, endNum);
	}
	// 누적관객수목록
	public List<Movie_info> info_list_total(int startNum, int endNum){
		return info_dao.total_list(startNum, endNum);
	}
	// 개봉일순목록
	public List<Movie_info> info_list_releasedate(int startNum, int endNum){
		return info_dao.releasedate_list(startNum, endNum);
	}
	// 현재상영작목록
	public List<Movie_info> info_list_nowshowing(int startNum, int endNum){
		return info_dao.nowshowing_list(startNum, endNum);
	}
	// 상영예정작목록
	public List<Movie_info> willshow_country(int startNum, int endNum){
		return info_dao.willshow_list(startNum, endNum);
	}
	
	// 상세보기
	public Movie_info movie_info_view(int moviecode) {
		return info_dao.movie_info_view(moviecode);
	}
	
	///////////////// 영화 리뷰 ////////////////////
	
	// 전체 목록 보기
	public List<Movie_review> review_list(int startNum, int endNum) {
		return review_dao.review_list(startNum, endNum);
	}
	
	// 총답글수
	public int review_count() {
		return review_dao.getreviewcount();
	}
	
	// 답글저장
	public Movie_review writereview(Movie_reviewDTO dto) {
		return review_dao.insertreview(dto);
	}

}
