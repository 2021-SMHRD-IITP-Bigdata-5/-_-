<%@page import="com.snsDAO.tm_snsDAO"%>
<%@page import="com.snsDTO.tm_snsDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.container {
	overflow: hidden;
	margin: 0 auto;
	/*	display: grid;
	grid-template-columns: repeat(auto-fill, minmax(25%, auto));
	grid-template-rows: repeat(5, minmax(50px, auto));
	grid-auto-flow: dense;
	align-items: start;
	place-self: start center;*/
	display: flex;
	flex-flow: row wrap;
	justify-content: flex-start;
	align-items: flex-start;
	align-content: flex-start; 
}

.item {
	float: left;
	padding: 3px;
	margin: 3px;
	width: 200px;
	height: 200px;
	object-fit: cover;
	/* flex-basis: 250px;
	width: 250px;
	align-self: start;
	flex-shrink: 0; */
}
</style>
</head>
<body>

	<%
	tm_snsDAO dao = new tm_snsDAO();
	ArrayList<tm_snsDTO> list = dao.selectAll();
	request.setAttribute("list", list);
	%>

	<div class="container">
		<c:forEach items="${requestScope.list}" var="i">
			<div> 
				<a href="tm_selectMember?mb_id=${i.mb_id}">${i.mb_id}</a>
				<div>${i.tb_likes }</div>
				<a href="tm_SelectOne?tb_seq=${i.tb_seq}"> <img class="item"
					src="tm_upload/${i.tb_file}">
				</a>
			</div>
			

		</c:forEach>
	</div>

	<script type="text/javascript" src="assets/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
		
	</script>

</body>
</html>