<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./assets/css/N5_mypage_sns.css" />
    <title>N5_mypage_sns</title>
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
        <div id="side_two">
            <input type="text" id="search_keyword_query" placeholder=" 검색어 입력">
            <button id="search_keyword_query_button"><img src="./img/search.png"></button>
        </div>
        <div id="side_three">
        </div>
        <div id="side_four"></div>
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
				
					$('#side_three').html('');
					
					let table = "";
					
					for (let i = 0; i < res.length; i++) {
						
						table += '<br>'
						table += '<table width="300" height="100" align="center">';
						if (res[i].mb_img == null) {
							table += '<th rowspan="4"><img src="./phm_img/No_Image.png" width="100", height="100" align="left" onClick=' + "'snsDetail()'" + ' style="cursor:pointer; border:1px solid #c8c8c8;"></th>';
						} else {
							table += '<th rowspan="4"><img src="' + res[i].mb_img + '" width="100", height="100" align="left" onClick=' + "'snsDetail()'" + ' style="cursor:pointer"></th>';
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
					$('#side_three').append(table);
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
					
						$('#side_three').html('');	
						
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
								table += '<th><img src="' + res[i].tb_file + '" width="300", height="200" align="center" onClick=' + "'snsPost()'" + ' style="cursor:pointer;"></th>';
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
						$('#side_three').append(table);
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
				
				$('#side_three').html('');	
				
				$('#side_three').append(table);
				
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
	
<!-- --------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->    
	<div id="map" style="width: 73.3%; height: 100vh; float: right;"></div>
	
	
	<script src="./assets/js/jquery-3.6.0.min.js"></script>
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