<%@page import="com.PHM_travel_mapDTO.PHM_travel_mapDTO"%>
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
    
<style type="text/css">
	.plan{
		width : 10%;
	}

</style>
    
</head>

<body>
	<%
		PHM_travel_mapDTO travelplan2 = (PHM_travel_mapDTO)session.getAttribute("travelplan2");
		int start_date = Integer.parseInt(travelplan2.getStart_date().substring(8));
		int end_date = Integer.parseInt(travelplan2.getEnd_date().substring(8));
		int total_date = end_date - start_date + 1;
	%>

	<div id="day_location" style="display:none;"></div>
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
        	<%for(int i=0; i<total_date; i++) {%>
			 	<button class="side_button" onClick="day_plan(<%=total_date %>,<%=i+1%>)"><%=i+1%>일차</button>
        	<%}%>
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
				<thead>
				<tr>
					<td>순서</td>
					<td>여행지명</td>
					<td>도착시간</td>
					<td>출발시간</td>
					<td>메모</td>
				</tr>
				</thead>
				<tbody id="plan_table1">
					<tr><td>1</td></tr>
					<!--  <tr>
						<td colspan="6" id="plan_table_last" ><a href="#" onClick="temporary_storage()">임시저장</a></td>
					</tr>-->
				</tbody>
				<%for(int j = 0; j<total_date-1; j++) { %>
					<tbody id="plan_table<%=j+2%>" style="display:none;">
					</tbody>
				<%} %>
			</table>
		</div>
		<div id="side_five" align="center">
		</div>
		<div id="side_six" align="center" style="margin-top:20px;">
				<button class="side_button" onClick="kakao_route()" style="width:100px; margin-right:10px;"><a href="#">지도 미리보기</a></button>
				<button class="side_button" onClick="tmap_route()" style="width:100px; margin-right:10px;"><a href="#">경로 미리보기</a></button>
				<button class="side_button" style="width:100px;"><a href="#">계획 세우기</a></button>
		</div>
   	</div>
	
<!-- --------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->    
	<div id="kakao_map" style="width: 73.3%; height: 100vh; float: right;"></div>
	<div id="tmap_map" style="width: 73.3%; height: 100vh; float: right; display:none;"></div>
	
	<script src="./assets/js/jquery-3.6.0.min.js"></script>
	
	<script type="text/javascript">
		//여행지 즐겨찾기 목록 파트
		var day = 1;
		
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
		//var loc = $('#day_location').text();
		/*for(let i=0; i<location.length ; i++){
			let plan_list=[];
		}*/
		var cnt=0;
		let plan_list=[];
		let plan_data=[];
		var positions=[];
		let plan_list_position=[];
		let latitude = "";
		let longtitude = "";
		function add_plan(map_name){
			$.ajax({
				url : "add_Plan",	
				type : "get",
				data : {
					"map_name":map_name
				},
				dataType : "json",
				success : function(res){ 
					cnt++;
					console.log("res :",res);
					
					plan_list.push({day:day , 
									data:res,
									cnt:cnt});
					console.log(plan_list)
					positions.push({title: res.map_name, 
				        latlng: new kakao.maps.LatLng(res.map_latitude, res.map_longtitude)});
					
					latitude = res.map_latitude;
					longtitude = res.map_longtitude;
					console.log("latitude: ",latitude);
					console.log("longtitude: ",longtitude);
				    console.log(positions);
					//console.log(plan_list);
					$('#plan_table'+day+'').html('');
					let add_tag2 = "";
					for(let i = 0; i<plan_list.length ; i++){
						add_tag2+="<tr width='100%'><td class='plan'>"+(i+1)+"</td>";
						add_tag2+="<td class='plan'>"+plan_list[i].data.map_name+"</td>";
						add_tag2+="<td class='plan'><input type=\"time\"></td>";
						add_tag2+="<td class='plan'><input type=\"time\"></td>";
						add_tag2+="<td class=\"memodo"+(i+1)+" plan\"><button onClick=\"memo("+(i+1)+")\" >메모하기</button></td>";
						add_tag2+="<td class='plan'><button onClick=\"remove_plan('"+plan_list[i].map_name+"')\">삭제</button></td></tr>";
						add_tag2+="<tr align=\"right\">";
						add_tag2+="<td colspan=\"6\" class=\"textarea"+(i+1)+"\"></td></tr>";
						
					}
					$('#plan_table'+day+'').append(add_tag2);
					
					panTo();
					displayMarker(positions);
					
				},
				error : function(){
					alert("요청 실패!")
				}
			
			})
		}
	
	</script>
	<script type="text/javascript">
	 	
		// 일차별로 계획 만들기 파트
		function day_plan(total_length, i){
			console.log('#plan_table'+i+'')
			
			for(let j=1; j<total_length+1 ; j++){
				if(j==i){
					$('#plan_table'+i+'').css('display','inline');
				}else{
					$('#plan_table'+j+'').css('display','none');
				}
			}
			//$('#day_location').text(i); 
			day=i;
		}
	
	</script>
	<script type="text/javascript">	
		//메모기능 파트
		function memo(i){
			$('.textarea'+i+'').html("<textarea class=\"text"+i+"\" style=\" width:400px;\"></textarea>");
			$('.memodo'+i+'').html("<button onClick=\"close_memo("+i+")\" >메모닫기</button>")
		}
		function close_memo(i){
			$('.text'+i+'').hide();
			$('.memodo'+i+'').html("<button onClick=\"memo2("+i+")\" >메모하기</button>")
		}
		function memo2(i){
			$('.text'+i+'').show();
			$('.memodo'+i+'').html("<button onClick=\"close_memo("+i+")\" >메모닫기</button>")
		}
		function remove_plan(map_name){
			for(let i = 0; i < plan_list.length; i++) {
			  if(plan_list[i].map_name === map_name)  {
				    plan_list.splice(i, 1);
			  }
			}
			for(let i = 0; i < positions.length; i++) 	{
			  if(positions[i].map_name === map_name){
				   	posiotions.splice(i, 1);
			  }
			}
			$('#plan_table').html('');
			let add_tag2 = "";
			for(let i = 0; i<plan_list.length ; i++){
				add_tag2+="<tr><td>"+(i+1)+"</td>";
				add_tag2+="<td>"+plan_list[i].map_name+"</td>";
				add_tag2+="<td><input type=\"time\"></td>";
				add_tag2+="<td><input type=\"time\"></td>";
				add_tag2+="<td class=\"memodo"+(i+1)+"\"><button onClick=\"memo("+(i+1)+")\" >메모하기</button></td>";
				add_tag2+="<td><button onClick=\"remove_plan('"+plan_list[i].map_name+"')\">삭제</button></td></tr>";
				add_tag2+="<tr align=\"right\">";
				add_tag2+="<td colspan=\"6\" class=\"textarea"+(i+1)+"\"></td></tr>";
			}
			$('#plan_table').append(add_tag2)
		}
	</script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=31e189d0d305a85663770a625b11688d&libraries=services"></script>
	<script type="text/javascript">
	
		var mapContainer = document.getElementById('kakao_map'); // 지도를 표시할 div 
		mapOption = {
			center : new kakao.maps.LatLng(35.11070531999631,
					126.87780481325912), // 지도의 중심좌표 - 스마트인재개발원
			level : 3,	// 지도의 확대 레벨
		};
		// 지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption);
		
		function panTo() {
			// 이동할 위도 경도 위치를 생성합니다 
			var moveLatLon = new kakao.maps.LatLng(latitude, longtitude);

			// 지도 중심을 부드럽게 이동시킵니다
			// 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
			map.panTo(moveLatLon);
		}
		
		// 마커 이미지의 이미지 주소입니다
		var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
		
		function displayMarker(positions) {
			for (var i = 0; i < positions.length; i ++) {
			    
			    // 마커 이미지의 이미지 크기 입니다
			    var imageSize = new kakao.maps.Size(24, 35); 
			    
			    // 마커 이미지를 생성합니다    
			    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
			    
			    // 마커를 생성합니다
			    var marker = new kakao.maps.Marker({
			        map: map, // 마커를 표시할 지도
			        position: positions[i].latlng, // 마커를 표시할 위치
			        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
			        image : markerImage, // 마커 이미지 
			        level:3
			    });
			}
		}
	</script>
	
	<script type="text/javascript">
		// 지도 미리보기 파트(카카오맵) 
		function kakao_route(){
			document.getElementById('kakao_map').style.display="block";
			document.getElementById('tmap_map').style.display="none";
			var distanceOverlay;
			var linePath;
			var lineLine = new kakao.maps.Polyline();
			var distance;
			for(var i = 0; i<positions.length; i++){
				if(i!=0){
					linePath = [positions[i-1].latlng, positions[i].latlng]
				}
				lineLine.setPath(linePath) // 선을 그릴 라인을 세팅합니다.
				
				
				var drawLine = new kakao.maps.Polyline({
					map:map,
					path : linePath,
					strokeWeight : 3, 			// 선의 두께입니다
					strokeColor : '#db4040',	// 선의 색깔입니다
					strokeOpacity : 1, 			// 선의 불투명도입니다. 0에서 1사이값이며 0에 가까울수록 투명합니다.
					strokeStyle : 'solid'		// 선의 스타일입니다.
				})
				
				distance = Math.round(lineLine.getLength());
				displayCircleDot(positions[i].latlng, distance);
			}
			function displayCircleDot(position, distance){
				if(distance>0){
					//클릭한 지점까지의 그려진 선의 총 거리를 표시할 커스텀 오버레이를 생성합니다
					var distanceOverlay = new kakao.maps.CustomOverlay({
						content : '<div class="dotOverlay">거리<span class="number">'
								+ distance + '</span>m</div>',
						position : position,
						yAnchor : 1,
						zIndex : 2,
					
					})	;
					
					distanceOverlay.setMap(map);
				}
			}
			
			var moveLatLon = new kakao.maps.LatLng(35.1599801229349, 126.85227886055003)
			map.setLevel(7);
			map.setCenter(moveLatLon);
		}
	</script>
	
	<script src="https://apis.openapi.sk.com/tmap/jsv2?version=1&appKey=l7xxefc4aaf819ab46d09bfedeef6adff714"></script>
	<script>
		// 지도 경로미리보기 파트(티맵)
		function tmap_route(){
			document.getElementById('kakao_map').style.display="none";
			document.getElementById('tmap_map').style.display="block";
			// 1. 지도 띄우기
			map1 = new Tmapv2.Map("tmap_map", {
				center: new Tmapv2.LatLng(35.1599801229349, 126.85227886055003),
			});
			map1.setZoom(14);
			
			var new_polyLine = [];
			var new_Click_polyLine = [];
			
			function drawData(data){
				// 지도위에 선은 다 지우기
				routeData = data;
				var resultStr = "";
				var distance = 0;
				var idx = 1;
				var newData = [];
				var equalData = [];
				var pointId1 = "-1234567";
				var ar_line = [];
				
				for (var i = 0; i < data.features.length; i++) {
					var feature = data.features[i];
					//배열에 경로 좌표 저잘
					if(feature.geometry.type == "LineString"){
						ar_line = [];
						for (var j = 0; j < feature.geometry.coordinates.length; j++) {
							var startPt = new Tmapv2.LatLng(feature.geometry.coordinates[j][1],feature.geometry.coordinates[j][0]);
							ar_line.push(startPt);
							pointArray.push(feature.geometry.coordinates[j]);
						}
						var polyline = new Tmapv2.Polyline({
					        path: ar_line,
					        strokeColor: "#ff0000", 
					        strokeWeight: 6,
					        map: map1
					    });
						new_polyLine.push(polyline);
					}
					var pointId2 = feature.properties.viaPointId;
					if (pointId1 != pointId2) {
						equalData = [];
						equalData.push(feature);
						newData.push(equalData);
						pointId1 = pointId2;
					}
					else {
						equalData.push(feature);
					}
				}
				geoData = newData;
				var markerCnt = 1;
				for (var i = 0; i < newData.length; i++) {
					var mData = newData[i];
					var type = mData[0].geometry.type;
					var pointType = mData[0].properties.pointType;
					var pointTypeCheck = false; // 경유지 일때만 true
					if (mData[0].properties.pointType == "S") {
						var img = 'http://tmapapi.sktelecom.com/upload/tmap/marker/pin_r_m_s.png';
						var lon = mData[0].geometry.coordinates[0];
						var lat = mData[0].geometry.coordinates[1];
					}
					else if (mData[0].properties.pointType == "E") {
						var img = 'http://tmapapi.sktelecom.com/upload/tmap/marker/pin_r_m_e.png';
						var lon = mData[0].geometry.coordinates[0];
						var lat = mData[0].geometry.coordinates[1];
					}
					else {
						markerCnt=i;
						var lon = mData[0].geometry.coordinates[0];
						var lat = mData[0].geometry.coordinates[1];
					}	
				}
			}
			// 2. 시작, 도착 심볼찍기
			
			var markerList = [];
			var pointArray = [];
			let plan_list_cnt = plan_list.length-1
			// 시작
			addMarker("llStart",plan_list[0].map_longtitude, plan_list[0].map_latitude,1);
			// 도착 
			addMarker("llEnd",plan_list[plan_list_cnt].map_longtitude, plan_list[plan_list_cnt].map_latitude,2);
			
			function addMarker(status, lon, lat, tag) {
				//출도착경유구분
				//이미지 파일 변경.
				var markerLayer;
				switch (status) {
					case "llStart":
						imgURL = 'http://tmapapi.sktelecom.com/upload/tmap/marker/pin_r_m_s.png';
						break;
					case "llPass":
						imgURL = 'http://tmapapi.sktelecom.com/upload/tmap/marker/pin_b_m_p.png';
						break;
					case "llEnd":
						imgURL = 'http://tmapapi.sktelecom.com/upload/tmap/marker/pin_r_m_e.png';
						break;
					default:
				};
				var marker = new Tmapv2.Marker({
					position: new Tmapv2.LatLng(lat,lon),
					icon: imgURL,
					map: map1
				});
				// 마커 드래그 설정
				marker.tag = tag;
				marker.addListener("dragend", function (evt) {
				markerListenerEvent(evt);
			    });
			    marker.addListener("drag", function (evt) {    	
			    	markerObject = markerList[tag];
			    });
			    markerList[tag] = marker;
				return marker;
			}
		
			// 3. 경유지 심볼 찍기
			for(let i=1; i<plan_list.length-1; i++){
				addMarker("llPass",plan_list[i].map_longtitude,plan_list[i].map_latitude,i+2);
			}
			
			// 4. 경로탐색 API 사용요청
			var startX = plan_list[0].map_longtitude;
			var startY = plan_list[0].map_latitude;
			var endX = plan_list[plan_list_cnt].map_longtitude;
			var endY = plan_list[plan_list_cnt].map_latitude;
			var passList = "";
			for(let i = 1; i<plan_list.length-1;i++){
				passList +=plan_list[i].map_longtitude+','+plan_list[i].map_latitude+'_';
			}
			var prtcl;
			var headers = {};
			headers["appKey"]="l7xxefc4aaf819ab46d09bfedeef6adff714";
			$.ajax({
					method:"POST", 
					headers : headers, 
					url:"https://apis.openapi.sk.com/tmap/routes?version=1&format=json",//
					async:false,
					data:{ 
						startX : startX,
						startY : startY,
						endX : endX,
						endY : endY,
						passList : passList,
						reqCoordType : "WGS84GEO",
						resCoordType : "WGS84GEO",
						angle : "172",
						searchOption : "0",
						trafficInfo : "Y"
					},
					success:function(response){
					prtcl = response;
					
					// 5. 경로탐색 결과 Line 그리기 
					var trafficColors = {
						extractStyles:true,
						/* 실제 교통정보가 표출되면 아래와 같은 Color로 Line이 생성됩니다. */
						trafficDefaultColor:"#636f63", //Default
						trafficType1Color:"#19b95f", //원할
						trafficType2Color:"#f15426", //지체
						trafficType3Color:"#ff970e"  //정체		
					};    			
					var style_red = {
						fillColor:"#FF0000",
						fillOpacity:0.2,
						strokeColor: "#FF0000",
						strokeWidth: 3,
						strokeDashstyle: "solid",
						pointRadius: 2,
						title: "this is a red line"
					};
					drawData(prtcl);
				
				
					// 6. 경로탐색 결과 반경만큼 지도 레벨 조정
					var newData = geoData[0];
					PTbounds = new Tmapv2.LatLngBounds();
							for (var i = 0; i < newData.length; i++) {
								var mData = newData[i];
								var type = mData.geometry.type;
								var pointType = mData.properties.pointType;
								if(type == "Point"){
									var linePt = new Tmapv2.LatLng(mData.geometry.coordinates[1],mData.geometry.coordinates[0]);
									console.log(linePt);
									PTbounds.extend(linePt);
								}
								else{
									var startPt,endPt;
									for (var j = 0; j < mData.geometry.coordinates.length; j++) {
										var linePt = new Tmapv2.LatLng(mData.geometry.coordinates[j][1],mData.geometry.coordinates[j][0]);
										PTbounds.extend(linePt);
									}
							}
						}
						map1.fitBounds(PTbounds);
				
					},
					error:function(request,status,error){
					console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});
		}
	</script>

</body>

</html>