package com.sist.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.*;

@Mapper
@Repository
public interface MemberMapper {
	@Select("SELECT COUNT(*) FROM member_1 WHERE id = #{id}")
	public int memberIdCheck(String id);
	
	@Select("SELECT id, name, pwd FROM member_1 "
			+ "WHERE id = #{id}")
	public MemberVO memberInfoData(String id);
}
