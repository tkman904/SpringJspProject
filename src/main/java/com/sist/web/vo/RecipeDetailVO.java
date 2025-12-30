package com.sist.web.vo;

import lombok.Data;

/*
    NO              NUMBER         
	POSTER          VARCHAR2(500)  
	TITLE           VARCHAR2(1000) 
	CHEF            VARCHAR2(300)  
	CHEF_POSTER     VARCHAR2(500)  
	CHEF_PROFILE    VARCHAR2(500)  
	INFO1           VARCHAR2(100)  
	INFO2           VARCHAR2(100)  
	INFO3           VARCHAR2(100)  
	CONTENT         CLOB           
	FOODMAKE        CLOB
	
	VO : 데이터를 모아서 전송
	Mapper : 데이터베이스 연동
	Service : Controller와 연동
	Controller : 사용자 요청 => 요청처리 (브라우저 연동)
		| JavaScript와 연동 (X) : 화면 이동
		  ---------------
		   | RestController : JSON / 문자열
	ControllerAdvice : 공통 예외처리
	Interceptor : Controller를 찾기 전에 => 자동 로그인 (remember-me)
	              ------------------
	              ViewResolver로 전송 전에
	              ------------------
	              JSP로 전송 전에 => 권한
	AOP : 공통 모듈 => Callback
	WebSocket : 실시간 처리
	------------------------------------------------------------
	Security : 일반 / JWT
	Batch : Task (실시간) => 알림
	------
	카프카
	------
	Spring Data
 */

@Data
public class RecipeDetailVO {
	private int no;
	private String poster, title, chef, chef_poster, chef_profile, info1, info2, info3, content, foodmake;
}
