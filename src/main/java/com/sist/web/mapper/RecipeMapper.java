package com.sist.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.*;

import com.sist.web.vo.*;

@Mapper
@Repository
public interface RecipeMapper {
	// 목록
	@Select("SELECT no, title, poster, chef "
			+ "FROM recipe "
			+ "WHERE no IN(SELECT no FROM recipe INTERSECT SELECT no FROM recipedetail) "
			+ "ORDER BY no ASC "
			+ "OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY")
	public List<RecipeVO> recipeListData(int start);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM recipe "
			+ "WHERE no IN(SELECT no FROM recipe INTERSECT SELECT no FROM recipedetail)")
	public int recipeTotalPage();
	
	// 상세보기
	// 댓글 : Mapper 따로 => Service에서 통합
}
