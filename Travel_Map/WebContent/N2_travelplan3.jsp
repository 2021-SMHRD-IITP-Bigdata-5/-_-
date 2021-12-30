<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./assets/css/N2_travelplan3.css" />
    <title>travelplan3</title>
</head>

<body>
	<div id="side_bar">
		<b>My Real Travel in GwangJu </b>
		<button onClick="location.href='Join.jsp'">회원가입</button>
		<button onClick="location.href='Login.jsp'">로그인</button>
	</div> 
    <div id="side_all">
        <div id="side_one">
            <nav>
                <ul class="side_menu">
                    <!-- onClick="window.location.reload()" -->
                    <li><button class = "side_button" onClick="location.href='N1_main.jsp'"><a href="#" >검색</a></button></li>
                    <li><button class = "side_button" onClick="location.href='N2_travelplan1.jsp'"><a href="#" >여행계획</a></button></li>
                    <li><button class = "side_button" onClick="location.href='N3_mypage_login_1bookmark1.jsp'"><a href="#" >My</a></button></li>
                    <li><button class = "side_button" onClick="location.href='N4_mypage_sns.jsp'"><a href="#" >SNS</a></button></li>
                </ul>
            </nav>
        </div>
        <div id="side_two" style="height:10%;">
        	
        	<input type="text" id="search_keyword_query" placeholder="검색어 입력">
            <button id="search_keyword_query_button"><img src="./img/search.png"></button>
        </div>
        <div id="side_three" style="height:25%;">
			<table id="plan_table_one" align="center">
				<caption><h3>여행지 즐겨찾기 목록</h3></caption>
				<colgroup>
					<col style="width: 5%;">
					<col style="width: 30%;">
					<col style="width: 32.5%;">
					<col style="width: 18.5%;">
					<col style="width: 14%;">
				</colgroup>
				<tr>
					<td>1</td>
					<td>map_name</td>
					<td>map_addr</td>
					<td><button>계획에 넣기</button></td>
					<td><button>삭제하기</button></td>
				</tr>
				<tr>
					<td>2</td>
				</tr>
				<tr>
					<td>3</td>
				</tr>
			</table>

        </div>
        <div id="day_add" align = "center" style="margin:10px;">
			 	<button class="side_button" ><a href="#">1일차</a></button>
			 	<button class="side_button" ><a href="#">2일차</a></button>
        </div>
        <div id="side_four" style="height:42%;">
			<table id="plan_table_one">
				<colgroup>
					<col style="width: 40px;">
					<col style="width: 150px;">
					<col style="width: 62.5px;">
					<col style="width: 62.5px;">
					<col style="width: 70px;">
					<col style="width: 70px;">
				</colgroup>
				<tr>
					<th>순서</th>
					<th>여행지명</th>
					<th>도착시간</th>
					<th>출발시간</th>
					<th>메모</th>
				</tr>
				<tr>
					<td>1</td>
					<td>충장로구경</td>
					<td>13:00</td>
					<td>15:00</td>
					<td id="memodo"><button onClick="memo()" >메모하기</button></td>
					<td><button>삭제하기</button></td>
				</tr>
				<tr align="right">
					<td colspan="6" id="textarea"></td>
				</tr>
				<tr>
					<td colspan="6" id="plan_table_last" ><a href="#" onClick="temporary_storage()">임시저장</a></td>
				</tr>
				
			
			</table>
		</div>
		<div id="side_five" align="center">
		</div>
		<div id="side_six" align="center" style="margin-top:20px;">
				<button class="side_button" style="width:100px; margin-right:10px;"><a href="#">지도 미리보기</a></button>
				<button class="side_button" style="width:100px; margin-right:10px;"><a href="#">경로 미리보기</a></button>
				<button class="side_button" style="width:100px;"><a href="#">계획 만들기</a></button>
		</div>
   	</div>
	
<!-- --------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->    
	<div id="map" style="width: 73.3%; height: 100vh; float: right;"></div>
	
	
	<script src="./assets/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
		function memo(){
			$('#textarea').html("<textarea style=\"width:400px;\"></textarea>");
			$('#memodo').html("<button onClick=\"close_memo()\" >메모닫기</button>")
		}
		function close_memo(){
			
		}
	</script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=31e189d0d305a85663770a625b11688d&libraries=services"></script>
	<script type="text/javascript">
		var mapContainer = document.getElementById('map'); // 지도를 표시할 div 
		mapOption = {
			center : new kakao.maps.LatLng(35.11070531999631,
					126.87780481325912), // 지도의 중심좌표 - 스마트인재개발원
			level : 3	// 지도의 확대 레벨
		};
		// 지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption);
		
	</script>

</body>

</html>