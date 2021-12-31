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
	<a href="tm_Login.jsp"><button>내 여행계획</button></a>
	<a href="tm_Login.jsp"><button>게시판</button></a>
	<a href="tm_Login.jsp"><button>업로드 페이지</button></a>
	<a href="tm_Login.jsp"><button>로그인 페이지로 이동</button></a>
	<% }else{ %>
	<a href="tm_myTravelInsert.jsp"><button>내 여행계획</button></a>
	<a href="snsMain.jsp"><button>게시판</button></a>
	<a href="tm_UploadMain.jsp"><button>업로드 페이지</button></a>
	<a href="tm_LogoutCon"><button>로그아웃</button></a>
	<% } %>



<!-- 	<hr>
	회원 아이디 프로필 :
	<button onClick="followCheck()">내 팔로우수 팔로워수 체크하기</button>

	<script type="text/javascript" src="assets/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
		function followCheck() {
			$.ajax({
				url : "tm_followCheckCon",
				type : "get",
				data : {

				},
				dayaType : 'json',
				success : function(res) {
					console.log(res)

					$('hr').after(res);
					
				},
				error : function() {
					alert("요청 실패 !");
				}

			})
		}
	</script>



	<hr>
	
	<script type="text/javascript" src="assets/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
		function follow() {
			$.ajax({
				url : "tm_followCon",
				type : "get",
				data : {

				},
				dayaType : 'json',
				success : function(res) {
					console.log(res)

					$('hr').after(res);
					
				},
				error : function() {
					alert("요청 실패 !");
				}

			})
		}
	</script>  -->


</body>
</html>

