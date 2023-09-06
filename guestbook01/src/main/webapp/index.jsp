<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.poscodx.guestbook01.vo.GuestBookVo"%>
<%@page import="com.poscodx.guestbook01.dao.GuestBookDao"%>
<%@page import="java.util.List"%>
<%@page import="java.time.LocalDateTime" %>
<%@page import="java.time.format.DateTimeFormatter"%>

<%
	List<GuestBookVo> list = new GuestBookDao().findAll();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
</head>
<body>
	<h1>------------ 방명록 등록 ------------</h1>
	<form action="/guestbook01/add.jsp" method="post">
		<table border=1 width=500>
			<tr>
				<td>이름</td><td><input type="text" name="name"></td>
				<td>비밀번호</td><td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td colspan=4><textarea name="contents" cols=60 rows=5></textarea></td>
			</tr>
			<tr>
				<td colspan=4 align=right><input type="submit" VALUE=" 등록 "></td>
			</tr>
		</table>
	</form>
	<h1>---------------- 목록 ----------------</h1>
	<%
		for(GuestBookVo vo :list){
	%>
	<table width=510 border=1>
		<tr>
		<script>
		var getNo ='<%=vo.getNo()%>';
		var noAsInt = parseInt(getNo); 
		</script>
			<td >[<%=(list.size() -list.indexOf(vo)) %>]</td>
			<td><%=vo.getName() %></td>
			<pre>
			<% 	LocalDateTime regDate = vo.getRegDate();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm:ss");
				String formattedDateTime = regDate.format(formatter); 
			%></pre>
			<td><%=formattedDateTime %></td>	
			<td><a href="deleteform.jsp?no=<%=vo.getNo()%>">삭제</a></td>
		</tr>
		<tr>
			<td colspan=4><%=vo.getContents().replaceAll("\n", "<br>") %></td>
		</tr>
	</table>
	<% } %>

</body>
</html>