<%@page import="com.snsDTO.tm_snsDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.snsDAO.tm_snsDAO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="./assets/css/N5_sns.css" />
<title>N5_sns</title>

</head>

<body>
	<div class="header">
		<b>My Real Travel in GwangJu </b>
		<button>회원가입</button>
		<button>로그인</button>
	</div>

	<%
		tm_snsDAO dao = new tm_snsDAO();
	ArrayList<tm_snsDTO> list = dao.selectAll();
	request.setAttribute("list", list);
	%>
	<div class="side_all">
		<div class="side_one">
			<nav>
				<ul class="side_menu">
					<!-- onClick="window.location.reload()" -->
					<li><button class="side_button"
							onClick="location.href='N1_main.jsp'">
							<a href="#">검색</a>
						</button></li>
					<li><button class="side_button"
							onClick="location.href='N2_travelplan1.jsp'">
							<a href="#">여행계획</a>
						</button></li>
					<li><button class="side_button"
							onClick="location.href='N3_mypage_login_1bookmark1.jsp'">
							<a href="#">My</a>
						</button></li>
					<li><button class="side_button"
							onClick="location.href='N4_mypage_sns.jsp'">
							<a href="#">SNS</a>
						</button></li>
				</ul>
			</nav>
		</div>
		<div class="side_two" style="display: inline-flex" style="background-color: #CDFBE4" >

			<select id="type" style="height: 36px">
				<option value="title">제목</option>
				<option value="user">사용자</option>
			</select> <input type="text" id="search_keyword_query" placeholder=" 검색어 입력" style= "margin-left: 0px">
			<button id="search_keyword_query_button" onClick='snsSearch()'>
				<img src="./img/search.png">
			</button>
		</div>
		<div class="side_three">
			<%
				for (tm_snsDTO i : list) {
			%>
			<div class="sns_feed">
				<div class="profile">
					<a href="#" class="profile_img"><img src="./img/details.png>"></a>
					<a href="tm_selectMember?mb_id=<%=i.getMb_id()%>" class="user_nick"><%=i.getMb_id()%></a>
				</div>
				<div class="trvel_pictures">
					<a href="tm_SelectOne?tb_seq=<%=i.getTb_seq()%>"> <img
						class="item" src="tm_upload/<%=i.getTb_file()%>">
					</a>
				</div>
				<div class="mini">
					<button>
						<img src="./img/heart_tung.png">
					</button>
					<div><%=i.getTb_likes()%></div>
					<a href="#"><img src="./img/details.png"></a>
				</div>
				<div class="trvel_writing">
					<p><%=i.getTb_content()%></p>
				</div>
			</div>
			<%
				}
			%>

		</div>
	</div>
		<script src="./assets/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
	function snsSearch() {
		
		if ($('#type').val() == 'user') {
			
		$.ajax({
			url : 'UserSearchCon',
			type : 'get',
			data : {
				'keyword' : $('#search_keyword_query').val()
			},
			dataType : 'json',
			success : function(res) {
				
				console.log(res)
				
				if (res == null) {
					
					alert('검색어를 입력하세요.');
					
				} else {
				
					$('.side_three').html('');
					
					let table = "";
					
					for (let i = 0; i < res.length; i++) {
						
						table += '<br>'
						table += '<table width="300" height="100" align="center">';
						if (res[i].mb_img == null) {
							table += '<th rowspan="4"><img src="./phm_img/No_Image.png" width="100", height="100" align="left" onClick=' + "'snsDetail()'" + ' style="cursor:pointer; border:1px solid #c8c8c8;"></th>';
						} else {
							table += '<th rowspan="4"><img src="tm_upload/' + res[i].mb_img + '" width="100", height="100" align="left" onClick=' + "'snsDetail()'" + ' style="cursor:pointer"></th>';
						}
						table += '<tr>';
						table += '<td>닉네임</td>'
						table += '<td><b id="user_nickname">' + res[i].mb_nickname + '</b></td>';
						table += '</tr>';
						table += '<tr>';
						table += '<td>팔로우</td>'
						table += '<td><b id="user_follow">' + res[i].mb_follow + '</b></td>';
						table += '</tr>';
						table += '<tr>';
						table += '<td>팔로워</td>'
						table += '<td><b id="user_follower">' + res[i].mb_follower + '</b></td>';
						table += '</tr>';
						table += '</table>';
						table += '<br>';
						table += '<hr width="400" align="center">';
						table += '<a id="user_id" style="display:none;">' + res[i].mb_id + '</a>';
						if (res[i].mb_img == null) {
							table += '<a id="user_image" style="display:none;"><img src="./phm_img/No_Image.png" width="100", height="100" align="left" style="border:1px solid #c8c8c8;"></a">';
						} else {
							table += '<a id="user_image" style="display:none;"><img src="' + res[i].mb_img + '" width="100", height="100" align="left"></a>';
						}
						
					}
					$('.side_three').append(table);
					}
			},
			error : function() {
				alert('검색실패');
			}
		});
		} else {
			
			$.ajax({
				url : 'TitleSearchCon',
				type : 'get',
				data : {
					'keyword' : $('#search_keyword_query').val()
				},
				dataType : 'json',
				success : function(res) {
					
					console.log(res)
					
					if (res == null) {
						
						alert('검색어를 입력하세요.');
						
					} else {
					
						$('.side_three').html('');	
						
						let table = "";
						
						for (let i = 0; i < res.length; i++) {
							
							table += '<br>';
							table += '<table width="450" height="100" align="center">';
							table += '<tr>';
							table += '<td><h3>' + res[i].tb_title + '<h3></td>';
							table += '</tr>';
							if (res[i].tb_file == null) {
								table += '<th><img src="./phm_img/No_Image.png" width="300", height="200" align="center" onClick=' + "'snsPost()'" + ' style="cursor:pointer; border:1px solid #c8c8c8;"></th>';
							} else {
								table += '<th><a href="tm_SelectOne?tb_seq='+res[i].tb_seq+'"><img src="tm_upload/' + res[i].tb_file + '" width="300", height="200" align="center" style="cursor:pointer;"></a></th>';
							}
							table += '<tr>';
							table += '<td style="color:grey">조회수 : ' + res[i].tb_cnt + '</td>';
							table += '</tr>';
							table += '<tr>';
							table += '<td style="color:grey">좋아요 : ' + res[i].tb_likes + '</td>'
							table += '</tr>';
							table += '</table>';
							table += '<br>';
							table += '<hr width="450" align="center">';
							
						}
						$('.side_three').append(table);
						}
				},
				error : function() {
					alert('검색실패');
				}
			});
		}
	}
	
	
	function snsDetail() {
		$.ajax({
			url : 'snsDetailCon',
			type : 'get',
			data : {
				'user_id' : $('#user_id').text()
			},
			dataType : 'json',
			success : function(res) {
						
				image = $('#user_image').html();
				nickname = $('#user_nickname').text();
				follow = $('#user_follow').text();
				follower = $('#user_follower').text();
				console.log($('#user_id').text())
				
				let table = "";
				
				table += '<br>';
				table += '<table width="300" height="100" align="center">';
				table += '<th rowspan="4">' + image + '</th>';
				table += '<tr>';
				table += '<td>닉네임</td>'
				table += '<td><b>' + nickname + '</b></td>';
				table += '</tr>';
				table += '<tr>';
				table += '<td>팔로우</td>'
				table += '<td><b>' + follow + '</b></td>';
				table += '</tr>';
				table += '<tr>';
				table += '<td>팔로워</td>'
				table += '<td><b>' + follower + '</b></td>';
				table += '</tr>';
				table += '</table>';
				table += '<br>';
				table += '<hr width="450" align="center">';
				table += '<br>';
				console.log(res);
				let cnt = 0;
				let max = -1;
				
				if (res == "") {
					
					table += '<h3 align="center">등록된 게시물이 없습니다.</h3>'
					
				} else {
				
				table += '<table width="390" height="100" align="center">';
				for(let i = 0; i < res.length; i++) {
					if ((i % 3) == 0) {
						table += '<tr>';
						max+=3;
					}
					table += '<td><img src="' + res[i].mb_id + '" width="130" height="130"></td>';
					if (cnt == max) {
						table += '</tr>';
					}
					cnt+=1;
				}
				table += '</table>';
				}
				
				$('.side_three').html('');	
				
				$('.side_three').append(table);
				
			},
			error : function() {
				alert('진입실패');
			}
		});
	}
	
	function snsPost() {
		$.ajax({
			url : 'snsPostCon',
			type : 'get',
			data : {
				
			},
			dataType : 'json',
			success : function(res) {
				
			},
			error : function() {
				alert('진입실패');
			}
		});
	}
	</script>
	<!-- ---------------------------------------- -->

	<script src="js/jquery-3.6.0.min.js"></script>

	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=31e189d0d305a85663770a625b11688d&libraries=services"></script>
	<script>
		// 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
		var infowindow = new kakao.maps.InfoWindow({
			zIndex : 1
		});

		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = {
			center : new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
			level : 3
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
					displayMarker(data[i]); //마커 찍어주는 부분
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
				map : map,
				position : new kakao.maps.LatLng(place.y, place.x)
			});

			// 마커에 클릭이벤트를 등록합니다
			kakao.maps.event.addListener(marker, 'mouseover', function() {
				// 마커위에 커서 올렸을 때  장소명이 인포윈도우에 표출됩니다
				infowindow
						.setContent('<div style="padding:5px;font-size:12px;">'
								+ place.place_name + '</div>');
				infowindow.open(map, marker);
			});
			kakao.maps.event.addListener(marker, 'mouseout', function() {
				// 마커 위에서 커서를 내렸을때 인포윈도우 삭제(안보이게)
				infowindow.close();

			});
			kakao.maps.event.addListener(marker, 'mouseout', function() {
				// 마커클릭했을 때 해당 장소 정보 보여주는 페이지로 이동
				location.href = "info.jsp?place=" + txt
			});
		}
	</script>


</body>

</html>