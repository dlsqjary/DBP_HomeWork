<%@page import="Homework2.MemberDto"%>
<%@page import="Homework2.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%
	String id = (String)session.getAttribute("id");
	MemberDao dao = MemberDao.getInstance();
	MemberDto dto = dao.getMember(id);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script language="JavaScript" src="members.js" ></script>
</head>
<body>
	<form action="modifyOk.jsp" method="post" name="reg_frm">
		아이디 : <%= dto.getId() %><br />
		비밀번호 : <input type="password" name="pwd" size="20"><br />
		비밀번호 확인 : <input type="password" name="pw_check" size="20"><br />
		이름 : <%= dto.getName() %><br />
		메일 : <input type="text" name="email" size="20" value="<%= dto.getEmail() %>"><br />
		전공 : <input type="text" name="dept" size="20" value="<%= dto.getDept() %>"><br />
		전화번호 : <input type="text" name="tel" size="50" value="<%= dto.getTel() %>"><br />
		성별 : <input type="text" name="sex" size="10" value="<%= dto.getSex() %>"><br />
		소개 : <input type="text" name="introduction" size="100" value="<%= dto.getIntroduction() %>"><br />
		<input type="button" value="수정" onclick="updateInfoConfirm()"> &nbsp;&nbsp;&nbsp; <input type="reset" value="취소" onclick="javascript:window.location='login.jsp'">
	</form>
</body>
</html>