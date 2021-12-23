<%@page import="com.memberDTO.tm_memberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	메인 페이지

	<% tm_memberDTO dto = (tm_memberDTO) session.getAttribute("dto"); %>
	<a href="tm_Main.jsp"><button>로고 클릭 메인페이지 이동</button></a>
	<% if(dto==null){ %>
	<a href="tm_Login.jsp"><button>로그인 페이지로 이동</button></a>
	<% }else{ %>
	<a href="tm_LogoutCon"><button>로그아웃</button></a>
	<% } %>
</body>
</html>

