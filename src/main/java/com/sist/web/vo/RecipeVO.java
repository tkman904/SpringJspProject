package com.sist.web.vo;

import lombok.Data;

/*
    NO            NUMBER(38)     
	TITLE         VARCHAR2(4000) 
	POSTER        VARCHAR2(4000) 
	CHEF          VARCHAR2(4000) 
	LINK          VARCHAR2(26)   
	HIT           NUMBER(38)     
	LIKECOUNT     NUMBER(38)     
	JJIMCOUNT     NUMBER(38)     
	REPLYCOUNT    NUMBER(38)
 */

@Data
public class RecipeVO {
	private int no, hit, likecount, jjimcount, replycount;
	// ================= 자동 증가 : 트리거 => 다른 테이블 연결시에만 사용이 가능
	// ================= 공통 댓글 : 프로시저 , JOIN : Function
	private String title, poster, chef, link;
}
