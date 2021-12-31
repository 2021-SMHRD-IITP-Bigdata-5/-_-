<%@page import="com.memberDTO.tm_memberDTO"%>
<%@page import="com.boardDTO.tm_boardDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.boardDAO.tm_boardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table, td {
	border: 1px solid black;
	border-collapse: collapse;
}
</style>
</head>
<body>


	<table>
		<tr>
			<td><a href="tm_BoardInsert.jsp"> 글 작성</a></td>
		</tr>
	</table>

	<table>
		<tr>
			<td>tb_seq</td>
			<td>tb_title</td>
			<td>tb_content</td>
			<td>tb_file</td>
			<td>tb_cnt</td>
			<td>tb_date</td>
			<td>tb_likes</td>
			<td>tb_total</td>
			<td>tb_open</td>
			<td>mb_id</td>
			<td>travel_seq</td>
		</tr>
	</table>

	<%
		tm_boardDAO dao = new tm_boardDAO();
	ArrayList<tm_boardDTO> list = dao.boardList();
	for (tm_boardDTO dto : list) {
	%>

	<table>

		<tr>
			<td><%=dto.getTb_seq()%></td>
			<td><a href="tm_BoardDetail.jsp?tb_seq=<%=dto.getTb_seq()%>">
					<%=dto.getTb_title()%></a></td>
			<td><%=dto.getTb_content()%></td>
			<td><%=dto.getTb_file()%></td>
			<td><%=dto.getTb_cnt()%></td>
			<td><%=dto.getTb_date()%></td>
			<td><%=dto.getTb_likes()%></td>
			<td><%=dto.getTb_total()%></td>
			<td><%=dto.getTb_open()%></td>
			<td><%=dto.getMb_id()%></td>
			<td><%=dto.getTravel_seq()%></td>
		</tr>

	</table>
	<%
		}
	%>

	<!-- 기능만 추가 -->
	<table>
		<tr>
			<td>검색기능 <select>
					<option>Mb_id</option>
					<option>Title</option>
					<option>Content</option>
			</select> <input type="text" class name="input-sm"> <input
				type="button" name="search" value="search">
			<td>
			<td><a href="">이전</a> <a href="">다음</a></td>
		</tr>
	</table>



</body>
</html>