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
		���̵� : <%= dto.getId() %><br />
		��й�ȣ : <input type="password" name="pwd" size="20"><br />
		��й�ȣ Ȯ�� : <input type="password" name="pw_check" size="20"><br />
		�̸� : <%= dto.getName() %><br />
		���� : <input type="text" name="email" size="20" value="<%= dto.getEmail() %>"><br />
		���� : <input type="text" name="dept" size="20" value="<%= dto.getDept() %>"><br />
		��ȭ��ȣ : <input type="text" name="tel" size="50" value="<%= dto.getTel() %>"><br />
		���� : <input type="text" name="sex" size="10" value="<%= dto.getSex() %>"><br />
		�Ұ� : <input type="text" name="introduction" size="100" value="<%= dto.getIntroduction() %>"><br />
		<input type="button" value="����" onclick="updateInfoConfirm()"> &nbsp;&nbsp;&nbsp; <input type="reset" value="���" onclick="javascript:window.location='login.jsp'">
	</form>
</body>
</html>