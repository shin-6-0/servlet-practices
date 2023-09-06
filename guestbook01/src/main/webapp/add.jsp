<%@page import="com.poscodx.guestbook01.vo.GuestBookVo"%>
<%@page import="com.poscodx.guestbook01.dao.GuestBookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String contents = request.getParameter("contents");
	String name = request.getParameter("name");
	String password = request.getParameter("password");
	GuestBookVo vo = new GuestBookVo();
	// 현재 시간을 LocalDateTime으로 가져오기
    java.time.LocalDateTime regDate = java.time.LocalDateTime.now();
    
    vo.setName(name);
    vo.setPassword(password);
    vo.setContents(contents);
    
    // 현재 시간을 regDate 필드에 설정
    vo.setRegDate(regDate);
	
	new GuestBookDao().insert(vo);
	
	response.sendRedirect("/guestbook01");
%>