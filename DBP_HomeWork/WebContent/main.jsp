<%@page import="Homework2.MemberDto"%>
<%@page import="Homework2.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	if(session.getAttribute("ValidMem") == null) {
%>
	<jsp:forward page="login.jsp" />
<%
	}

	String name = (String)session.getAttribute("name");
	String id = (String)session.getAttribute("id");
	MemberDao dao = MemberDao.getInstance();
	MemberDto dto = dao.getMember(id);
	
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	
	<h1><%= name %>님 안녕하세요.</h1>
	<h3>id: <%= id %></h3>
	<h3>tel: <%= dto.getId() %></h3>
	<h3>dept: <%= dto.getDept() %></h3>
	<h3>sex: <%= dto.getSex() %></h3>
	<h3>birth: <%= dto.getBirth() %></h3>
	<h3>introduction: <%= dto.getIntroduction() %></h3>
	<h3>email: <%= dto.getEmail() %></h3>
	
	
	<form action="logout.jsp" method="post">
		<input type="submit" value="로그아웃">&nbsp;&nbsp;&nbsp;<input type="button" value="정보수정" onclick="javascript:window.location='modify.jsp'">
	</form>

</body>
</html>