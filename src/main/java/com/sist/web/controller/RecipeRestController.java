package com.sist.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.service.*;
import com.sist.web.vo.*;

import java.util.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RecipeRestController {
	private final RecipeService rService;
	
	@GetMapping("/recipe/list_vue/")
	public ResponseEntity<Map> recipe_list_vue(@RequestParam("page") int page) {
		Map map = new HashMap();
		try {
			List<RecipeVO> list = rService.recipeListData((page-1)*12);
			int totalpage = rService.recipeTotalPage();
			
			final int BLOCK = 10;
			int startPage = ((page-1)/BLOCK*BLOCK)+1;
			int endPage = ((page-1)/BLOCK*BLOCK)+BLOCK;
			if(endPage>totalpage) {
				endPage = totalpage;
			}
			
			map.put("list", list);
			map.put("curpage", page);
			map.put("totalpage", totalpage);
			map.put("startPage", startPage);
			map.put("endPage", endPage);
			// JSON
		} catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			// 500에러
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	// Vue로 값 전송 => router
	
	@GetMapping("/recipe/detail_vue/")
	public ResponseEntity<Map> recipe_detail(@RequestParam("no") int no) {
		Map map = new HashMap();
		try {
			RecipeDetailVO vo = rService.recipeDetailData(no);
			String[] datas = vo.getFoodmake().split("\n");
			List<String> tList = new ArrayList<String>();
			List<String> iList = new ArrayList<String>();
			for(String s : datas) {
				StringTokenizer st = new StringTokenizer(s, "^");
				tList.add(st.nextToken());
				iList.add(st.nextToken());
			}
			
			map.put("vo", vo);
			map.put("tList", tList);
			map.put("iList", iList);
		} catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	} 
}
