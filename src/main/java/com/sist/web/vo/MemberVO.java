package com.sist.web.vo;
/*
 *    프로젝트 구조
 *    ---------
 *    static
 *    ------
 *      |
 *     --- js
 *      |   |
 *      |  stores : pinia store
 *      |  app :
 *      |  axios.js
 *      |
 *    webapp
 *      |
 *    WEB-INF
 *      |
 *    views => jsp
 *      | main
 *      | food
 *      | recipe
 *    index
 *    
 *    ========================== 패키지
 *    com.sist.web => 실행파일
 *    vo
 *    mapper
 *    service
 *    controller
 *    restcontroller
 *    commons
 *    aop
 *    --------------- 입문 과정 (MVC)
 *    interceptor : 자동 로그인
 *    security : 인가/인증 => 권한부여
 *    task : batch => 실시간 처리
 *    manager : websocket / kafka
 *    ===========================
 *    => 기본    
 */
import lombok.Data;

@Data
public class MemberVO {
	private String id, pwd, name, sex, address, msg;
}
