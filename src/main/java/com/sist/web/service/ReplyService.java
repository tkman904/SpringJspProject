package com.sist.web.service;

import java.util.*;

import com.sist.web.vo.*;

public interface ReplyService {
	/*
	   	@Select("SELECT no, cno, type, name, id, msg, TO_CHAR(regdate, 'yyyy-mm-dd hh24:mi:ss') AS dbday "
				+ "FROM comment_1 "
				+ "WHERE cno = #{cno}"
				+ "AND type = #{type}")
		public List<ReplyVO> replyListData(@Param("cno") Integer cno, @Param("type") Integer type);
		
		@Insert("INSERT INTO comment_1 "
				+ "VALUES(SELECT NVL(MAX(no)+1, 1) FROM comment_1),"
				+ "#{cno}, #{type}, #{id}, #{name}, #{msg}, SYSDATE")
		public void replyInsert(ReplyVO vo);
	 */
	public List<ReplyVO> replyListData(int cno, int type);
	public void replyInsert(ReplyVO vo);
	public void replyDelete(int no);
	public void replyUpdate(ReplyVO vo);
}
