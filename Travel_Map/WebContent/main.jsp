<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="./assets/css/mainstyle.css"/>
</head>
<body>
    <div id="blank"></div>
    <div><p id="web_name"><b>My Real Travel in GwangJu</b></p></div>
    <div id="header">
        <nav>
            <ul class="menu">
                <li><botton>검색</botton></li>
                <li><botton>여행계획</botton></li>
                <li><botton>My</botton></li>
                <li><botton>SNS</botton></li>
            </ul>
            <a class="cta" herf=""></a>
        </nav>
    </div>
    <div id='middle'>
        <input type="text" id="search_keyword_query" placeholder="검색어 입력" >
        <button id="search_keyword_query_button"><img src="./img/search.png"></button>
    </div>

        <div id = "info">
            <ul class = "list_around">
                <li>
                    <button class="btn_around">
                        <span class="ico_food"><img src="./img/restaurant.png"></span>
                        <span class="txt_around">맛집</span>
                    </button>
                </li>
                <li>
                    <button class="btn_around">
                        <span class="ico_tourist"><img src="./img/amusement-park.png"></span>
                        <span class="txt_around">관광지</span>
                    </button>
                </li>
                <li>
                    <button class="btn_around">
                        <span class="ico_cafe"><img src="./img/cafe.png"></span>
                        <span class="txt_around">카페</span>
                    </button>
                </li>
            </ul>
            


        </div>
        <div id="bottom">1</div>
        <div>1</div>

    

</body>
</html>