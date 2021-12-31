<%@page import="com.boardDAO.tm_boardDAO"%>
<%@page import="com.boardDTO.tm_boardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="tm_boardInsertCon" method="post">
		<table>
			<tr>
				<td>Tb_title <input type="text" name="tb_title"></td>
			</tr>
			<tr>
				<td>Tb_content <textarea cols=50 rows=10 name="tb_content"></textarea></td>
			</tr>

			<tr>
				<td><input type="submit" value="write"></td>
			</tr>
		</table>
	</form>
</body>
</html>



