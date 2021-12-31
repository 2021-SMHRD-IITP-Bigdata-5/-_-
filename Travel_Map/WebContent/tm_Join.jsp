<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	회원가입 페이지

	<form action="tm_JoinCon" method="post" enctype="multipart/form-data">

		ID : <input type="text" name="id">
			 <button type="button" id="Check" onClick="idCheck()">중복체크</button>
			 <p id="result"></p> 
		PW : <input type="password" name="pw"><br> 
		NAME : <input type="text" name="name"><br>
		AGE : <input type="date" name = "age"><br>
		GENDER : 남<input type="radio" name="gender" value="m">
				  여<input type="radio" name="gender" value="w"><br>
	    PROFILE : <label for="file">이미지 첨부</label> 
			 	  <input type="file" name="file" accept=".jpg, .jpeg, .png" multiple><br>
		EMAIL : <input type="text" name="email"><br>
		ADDR : <input type="text" name="addr"><br>
		NICKNAME : <input type="text" name="nickname"><br>
		           <input type="submit" name="회원가입">
			
			
	</form>

	<script type="text/javascript" src="assets/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
		function idCheck() {
			$.ajax({
				url : "tm_CheckCon",
				type : "get",
				data : {
					"id" : $('input[name=id]').val()
				},
				success : function(res) { 
					console.log(res)

					if (res == "true") {
						$('#result').html("중복된 아이디 입니다.");
					} else {
						$('#result').html("사용가능한 아이디 입니다.");
					}

				},
				error : function() {
					alert("요청 실패 !");
				}

			})

		}
	</script>

</body>
</html>