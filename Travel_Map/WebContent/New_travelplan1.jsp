<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./assets/css/New_travelplan1.css" />
    <title>travelplan1</title>
</head>

<body>
	<div class="header">
		<b>My Real Travel in GwangJu </b>
		<button>회원가입</button>
		<button>로그인</button>
	</div> 
    <div class="side_all">
        <div class="side_one" style="line-height: inherit;">
            <nav>
                <ul class="side_menu">
                    <li><button class = "side_button">검색</button></li>
                    <li><button class = "side_button">여행계획</button></li>
                    <li><button class = "side_button">My</button></li>
                    <li><button class = "side_button">SNS</button></li>
                </ul>
            </nav>
        </div>
        <div class="side_two">
			<ul>
				<li style="margin-top: 0px;">
					<button class="travel_plan">
						<h3>가족들과 광주여행</h3>
						2021년 01월 01일부터<br>
						2021년 01월 03일까지
					</button>	
				</li>
				<li>
					<button class="travel_plan">
						<h3>친구들과 광주여행</h3>
						2021년 03월 02일부터<br>
						2021년 03월 03일까지
					</button>
				</li>
				<li>
					<button class="travel_plan">
						<h3>취업기념 여행가즈아</h3>
						2021년 01월 05일부터<br>
						2021년 02월 08일까지
					</button>
				</li>
				<li>
					<button class="travel_plan">
						+
					</button>
				</li>
			</ul>
        </div>
    </div>





	<script src="js/jquery-3.6.0.min.js"></script>

	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=31e189d0d305a85663770a625b11688d&libraries=services"></script>
	<script>
		// 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
		var infowindow = new kakao.maps.InfoWindow({
			zIndex: 1
		});

		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
			mapOption = {
				center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
				level: 3
				// 지도의 확대 레벨
			};

		// 지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption);

		// 장소 검색 객체를 생성합니다
		var ps = new kakao.maps.services.Places();

		var txt;
		function search() {
			txt = $('input[type=text]').val()
			// 키워드로 장소를 검색합니다
			ps.keywordSearch(txt, placesSearchCB);
		}


		// 키워드 검색 완료 시 호출되는 콜백함수 입니다
		function placesSearchCB(data, status, pagination) {
			if (status === kakao.maps.services.Status.OK) {

				// 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
				// LatLngBounds 객체에 좌표를 추가합니다
				var bounds = new kakao.maps.LatLngBounds();

				for (var i = 0; i < data.length; i++) {
					displayMarker(data[i]);	//마커 찍어주는 부분
					bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x)); //범위설정
				}

				// 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
				map.setBounds(bounds);
			}
		}

		// 지도에 마커를 표시하는 함수입니다
		function displayMarker(place) {

			// 마커를 생성하고 지도에 표시합니다
			var marker = new kakao.maps.Marker({
				map: map,
				position: new kakao.maps.LatLng(place.y, place.x)
			});

			// 마커에 클릭이벤트를 등록합니다
			kakao.maps.event.addListener(marker, 'mouseover', function () {
				// 마커위에 커서 올렸을 때  장소명이 인포윈도우에 표출됩니다
				infowindow.setContent('<div style="padding:5px;font-size:12px;">'
					+ place.place_name + '</div>');
				infowindow.open(map, marker);
			});
			kakao.maps.event.addListener(marker, 'mouseout', function () {
				// 마커 위에서 커서를 내렸을때 인포윈도우 삭제(안보이게)
				infowindow.close();

			});
			kakao.maps.event.addListener(marker, 'mouseout', function () {
				// 마커클릭했을 때 해당 장소 정보 보여주는 페이지로 이동
				location.href = "info.jsp?place=" + txt
			});
		}
	</script>


</body>

</html>