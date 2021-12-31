<%@page import="com.boardDTO.tm_boardDTO"%>
<%@page import="com.boardDAO.tm_boardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		response.setCharacterEncoding("utf-8");
	int tb_seq = Integer.parseInt(request.getParameter("tb_seq"));
	tm_boardDAO dao = new tm_boardDAO();
	tm_boardDTO dto = dao.boardDetail(tb_seq);
	%>

	<tr>
		<td>tb_seq = <%=dto.getTb_seq()%></td>
		<td>tb_date = <%=dto.getTb_date()%></td>
		<td>mb_id = <%=dto.getMb_id()%></td>
		<td>tb_cnt = <%=dto.getTb_cnt()%></td>
	</tr>

	<tr>
		<td>tb_title = <%=dto.getTb_title()%></td>
	</tr>
	<tr>
		<td>tb_content = <%=dto.getTb_content()%></td>
	</tr>
	<a href="tm_BoardList.jsp">목록 </a>
</body>
</html>