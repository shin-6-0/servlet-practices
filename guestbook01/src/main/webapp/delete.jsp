<%@page import="com.poscodx.guestbook01.dao.GuestBookDao"%>
<%@page import="com.poscodx.guestbook01.vo.GuestBookVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	String no = request.getParameter("no");
	String password = request.getParameter("password");
	
	if(password.equals(new GuestBookDao().findPwByNo(Integer.parseInt(no)))) {
		new GuestBookDao().deleteByNo(Integer.parseInt(no));	
	}
	
	response.sendRedirect("/guestbook01");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete GuestBook</title>
</head>
<body>
	<h1>성공적으로 삭제되었습니다.</h1>
</body>
</html>