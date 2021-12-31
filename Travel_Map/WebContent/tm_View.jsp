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

	<button name="likes" onclick="likes()">좋아요</button>
	<button>
		<a href="snsMain.jsp">목록으로 돌아가기</a>
	</button>
	<table>
		<tr>
			<td>tb_seq</td>
			<td>${requestScope.dto.tb_seq}</td>
		</tr>
		<tr>
			<td>tb_title</td>
			<td>${requestScope.dto.tb_title}</td>
		</tr>
		<tr>
			<td>tb_content</td>
			<td>${requestScope.dto.tb_content}</td>
		</tr>
		<tr>
			<td>tb_file</td>
			<td>${requestScope.dto.tb_file}</td>
		</tr>
		<tr>
			<td>tb_cnt</td>
			<td>${requestScope.dto.tb_cnt}</td>
		</tr>
		<tr>
			<td>tb_likes</td>
			<td id="tb_likes">${requestScope.dto.tb_likes}</td>
		</tr>
		<tr>
			<td>tb_total</td>
			<td>${requestScope.dto.tb_total}</td>
		</tr>
		<tr>
			<td>tb_open</td>
			<td>${requestScope.dto.tb_open}</td>
		</tr>
		<tr>
			<td>mb_id</td>
			<td>${requestScope.dto.mb_id}</td>
		</tr>
		<tr>
			<td>travel_seq</td>
			<td>${requestScope.dto.travel_seq}</td>
		</tr>
		<tr>
			<td colspan="2"><img src="tm_upload/${requestScope.dto.tb_file}"></td>
		</tr>
	</table>

	<script type="text/javascript" src="assets/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
		function likes() {
			$.ajax({
				url : "likesCon",
				type : "post",
				data : {
					"tb_seq" : ${requestScope.dto.tb_seq}
				},
				success : function(res) {
					console.log(res);
					$('#tb_likes').html(res);
				},
				error : function() {
					alert("요청 실패 !");
				},
			})
		}
	</script>
</body>
</html>