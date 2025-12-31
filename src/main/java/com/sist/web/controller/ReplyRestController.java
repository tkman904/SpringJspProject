package com.sist.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import com.sist.web.vo.*;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import com.sist.web.service.*;

@RestController
@RequiredArgsConstructor
public class ReplyRestController {
	private final ReplyService rService;
	
	@GetMapping("/reply/list_vue/")
	public ResponseEntity<Map> reply_list_vue(@RequestParam("cno") int cno, @RequestParam("type") int type) {
		Map map = new HashMap();
		try {
			List<ReplyVO> list = rService.replyListData(cno, type);
			
			map.put("list", list);
			map.put("cno", cno);
			map.put("type", type);
		} catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@PostMapping("/reply/insert_vue/")
	public ResponseEntity<Map> reply_insert(@RequestBody ReplyVO vo, HttpSession session) {
		Map map = new HashMap();
		try {
			String id = (String)session.getAttribute("id");
			String name = (String)session.getAttribute("name");
			vo.setId(id);
			vo.setName(name);
			rService.replyInsert(vo);
			
			List<ReplyVO> list = rService.replyListData(vo.getCno(), vo.getType());
			
			map.put("list", list);
			map.put("cno", vo.getCno());
			map.put("type", vo.getType());
		} catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@DeleteMapping("/reply/delete_vue/")
	public ResponseEntity<Map> reply_delete_vue(@RequestParam("cno") int cno, @RequestParam("type") int type, @RequestParam("no") int no) {
		Map map = new HashMap();
		try {
			rService.replyDelete(no);
			
			List<ReplyVO> list = rService.replyListData(cno, type);
			map.put("list", list);
			map.put("cno", cno);
			map.put("type", type);
		} catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@PutMapping("/reply/update_vue/")
	public ResponseEntity<Map> reply_update(@RequestBody ReplyVO vo) {
		Map map = new HashMap();
		try {
			rService.replyUpdate(vo);
			
			List<ReplyVO> list = rService.replyListData(vo.getCno(), vo.getType());
			
			map.put("list", list);
			map.put("cno", vo.getCno());
			map.put("type", vo.getType());
		} catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
