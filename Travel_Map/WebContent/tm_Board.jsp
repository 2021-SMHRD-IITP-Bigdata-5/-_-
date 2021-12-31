<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			<td>tb_seq</td>
			<td>tb_title</td>
			<td>te_content</td>
			<td>tb_file</td>
			<td>tb_cnt</td>
			<td>tb_likes</td>
			<td>tb_total</td>
			<td>tb_open</td>
			<td>mb_id</td>
			<td>travel_seq</td>
		</tr>

		<c:forEach items="${requestScope.list}" var="i">
			<tr>
				<td>${i.tb_seq }</td>
				<td>${i.tb_title }</td>
				<td>${i.tb_content }</td>
				<td>${i.tb_file }</td>
				<td>${i.tb_cnt }</td>
				<td>${i.tb_likes }</td>
				<td>${i.tb_total }</td>
				<td>${i.tb_open }</td>
				<td>${i.mb_id }</td>
				<td>${i.travel_seq }</td>
				<td><a href="tm_SelectOne?tb_seq=${i.tb_seq}"> <img src="tm_upload/${i.tb_file}" alt = "Not Found"> </a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>