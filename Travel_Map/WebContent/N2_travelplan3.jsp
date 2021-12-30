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
				<colgroup >
					<col style="width: 5%;">
					<col style="width: 30%;">
					<col style="width: 32.5%;">
					<col style="width: 18.5%;">
					<col style="width: 14%;">
				</colgroup>
				<tbody id="after"></tbody>
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
					<col style="width: 80px;">
					<col style="width: 60px;">
				</colgroup>
				<tr>
					<th>순서</th>
					<th>여행지명</th>
					<th>도착시간</th>
					<th>출발시간</th>
					<th>메모</th>
				</tr>
				<tbody id="plan_table">
					
					
					<!--  <tr>
						<td colspan="6" id="plan_table_last" ><a href="#" onClick="temporary_storage()">임시저장</a></td>
					</tr>-->
				</tbody>
			
			</table>
		</div>
		<div id="side_five" align="center">
		</div>
		<div id="side_six" align="center" style="margin-top:20px;">
				<button class="side_button" style="width:100px; margin-right:10px;"><a href="#">지도 미리보기</a></button>
				<button class="side_button" style="width:100px; margin-right:10px;"><a href="#">경로 미리보기</a></button>
				<button class="side_button" style="width:100px;"><a href="#">계획 미리보기</a></button>
		</div>
   	</div>
	
<!-- --------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->    
	<div id="map" style="width: 73.3%; height: 100vh; float: right;"></div>
	
	
	<script src="./assets/js/jquery-3.6.0.min.js"></script>
	
	<script type="text/javascript">	
		//메모기능 파트
		function memo(){
			$('#textarea').html("<textarea id=\"text\" style=\" width:400px;\"></textarea>");
			$('#memodo').html("<button onClick=\"close_memo()\" >메모닫기</button>")
		}
		function close_memo(){
			$('#text').hide();
			$('#memodo').html("<button onClick=\"memo2()\" >메모하기</button>")
		}
		function memo2(){
			$('#text').show();
			$('#memodo').html("<button onClick=\"close_memo()\" >메모닫기</button>")
		}
		function remove_memo(){
			
		}
	</script>
	<script type="text/javascript">
		//여행지 즐겨찾기 목록 파트
		
		$.ajax({
				url : "favorite_bringCon",
				type : "get",
				data : {
				},
				dataType : "json",
				success : function(res){ 
					//console.log(res)
					
					let add_tag ="";
					for(let i = 0; i < res.length ; i++){
						add_tag += "<tr><td>"+(i+1)+"</td>";
						add_tag += "<td id=\"removedata\">"+res[i].map_name+"</td>";
						add_tag += "<td>"+res[i].map_addr+"</td>";
						add_tag += "<td><button onClick=\"add_plan(\'"+res[i].map_name+"\')\">계획에 넣기</button></td>";
						add_tag += "<td><button onClick=\"favorite_remove()\">삭제하기</button></td></tr>";
					}
					$('#after').append(add_tag);
					
				},
				error : function(){
					alert("요청 실패!")
				}
			
			})
		function favorite_remove(){
			$.ajax({
				url : "favorite_removeCon",	//맵핑
				type : "get",
				data : {
					map_name:$('#removedata').text()
				},
				dataType:"json",
				success : function(res){ 
					
					//console.log(res)
					$('#after').html('');
					add_tag ="";
					for(let i = 0; i < res.length ; i++){
						add_tag += "<tr><td>"+(i+1)+"</td>";
						add_tag += "<td class=\"removedata\">"+res[i].map_name+"</td>";
						add_tag += "<td>"+res[i].map_addr+"</td>";
						add_tag += "<td><button onClick=\"add_plan(\'"+res[i].map_name+"\')\">계획에 넣기</button></td>";
						add_tag += "<td><button onClick=\"favorite_remove()\">삭제하기</button></td></tr>";
					}	
					$('#after').append(add_tag);
					
				},
				error : function(){
					alert("요청 실패!")
				}
			
			})
		}
	</script>
	<script>
		// 계획에 넣기 파트
		
		let plan_list=[];
		function add_plan(map_name){
			$.ajax({
				url : "add_Plan",	
				type : "get",
				data : {
					"map_name":map_name
				},
				//dataType : "json",
				success : function(res){ 
					plan_list.push(res);
					console.log(plan_list);
					$('#plan_table').html('');
					let add_tag2 = "";
					for(let i = 0; i<plan_list.length ; i++){
						add_tag2+="<tr><td>"+(i+1)+"</td>";
						add_tag2+="<td>"+plan_list[i]+"</td>";
						add_tag2+="<td><input type=\"time\"></td>";
						add_tag2+="<td><input type=\"time\"></td>";
						add_tag2+="<td id=\"memodo\"><button onClick=\"memo()\" >메모하기</button></td>";
						add_tag2+="<td><button onClick=\"remove_plan()\">삭제</button></td></tr>";
						add_tag2+="<tr align=\"right\">";
						add_tag2+="<td colspan=\"6\" id=\"textarea\"></td></tr>";
					}
					$('#plan_table').append(add_tag2)
					
				},
				error : function(){
					alert("요청 실패!")
				}
			
			})
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