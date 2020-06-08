<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="Homework2.MemberDao"%>
<%@page import="Homework2.MemberDto"%>
<%
	

	String name = (String)session.getAttribute("name");
	String id = (String)session.getAttribute("id");
	
	
	
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>테이블 전체 조회</h3>
	<table width="700" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<td>아이디</td>
			<td>비밀번호</td>
			<td>이름</td>
			<td>전화번호</td>
			
		</tr>
		<c:forEach items="${show}" var="dto">
		<tr>
			<td>${dto.id }</td>
			<td>${dto.name}</td>
			<td>${dto.sex}</td>
			<td>${dto.tel}</td>
			<!--  
			<td>${dto.bHit}</td>
			<td>${dto.bGroup}</td>
			<td>${dto.bStep}</td>
			<td>${dto.bIndent}</td>
			-->
		</tr>
		</c:forEach>
	</table>
	<c:forEach var="fe" begin="0" end="100" step="5">
		${fe}<br/>
	</c:forEach>
	<tr>
			<td colspan="5"> <a href="login.jsp">이동</a> </td>
		</tr>
</body>
</html>