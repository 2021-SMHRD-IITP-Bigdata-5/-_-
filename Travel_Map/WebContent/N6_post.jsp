<%@page import="com.snsDTO.tm_snsDTO"%>
<%@page import="com.snsDAO.tm_snsDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="./assets/css/N6_post.css">
</head>
<body>
	<%
		tm_snsDAO dao = new tm_snsDAO();
	tm_snsDTO dto = new tm_snsDTO();
	dto = (tm_snsDTO) request.getAttribute("dto");
	%>
	<div class="container">       	
		<div class="item1">
            <div><img src="tm_upload/<%=dto.getTb_file()%>"></div>
        </div>
        <div class="item2">
            <div class="item3">
                <a href="#" clase="profile_img"><img src="img/sns6.jpg"></a>
				<a href="#" class="user_nick"><%=dto.getMb_id() %></a>
				<a href="#" class="travel_plan"><%=dto.getTb_title() %></a>
            </div>
            <div class="item4"><%=dto.getTb_content() %></p></div>
            <div class="item5">
                <div><a href="#">user_nick</a> : 오</div><br>
                <div><a href="#">user_nick2</a> : 오2 </div><br>
                <div><a href="#">user_nick3</a> : 오3 </div><br>
                <div><a href="#">user_nick4</a> : 오4 </div><br>
                <div><a href="#">user_nick5</a> : 오5 </div><br>
                <div><a href="#">user_nick6</a> : 오6 </div><br>
                <div><a href="#">user_nick7</a> : 오7 </div><br>
                <div><a href="#">user_nick8</a> : 오8 </div><br>
                <div><a href="#">user_nick9</a> : 오9 </div><br>
                <div><a href="#">user_nick10</a> : 오10 </div><br>
                <div><a href="#">user_nick11</a> : 오11 </div><br>
                <div><a href="#">user_nick12</a> : 오12 </div><br>
                <div><a href="#">user_nick13</a> : 오13 </div><br>
                <div><a href="#">user_nick14</a> : 오14 </div><br>
            </div>
            <div class="item6">
                <button id="likesButton" onclick="likes()"><img src="img/heart_tung.png"></button>
                <div id ="likes" style="align-self: center"></div>
                <button><img src="img/share.png"></button>
            </div>
            <div class="item7">
            <input type="text">
            <button>댓글달기</button>
            </div>
        </div>
    </div>
    
    	<script type="text/javascript" src="assets/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
		function likes() {
			$.ajax({
				url : "likesCon",
				type : "post",
				data : {
					"tb_seq" : <%=dto.getTb_seq()%>
				},
				success : function(res) {
					console.log(res);
					$('#likes').text(res);
				},
				error : function() {
					alert("요청 실패 !");
				},
			})
		}
	</script>
</body>
</html>