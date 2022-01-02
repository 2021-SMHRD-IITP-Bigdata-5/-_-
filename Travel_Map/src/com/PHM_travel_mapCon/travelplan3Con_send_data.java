package com.PHM_travel_mapCon;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.PHM_travel_mapDAO.PHM_travel_planDAO;
import com.PHM_travel_mapDTO.PHM_travel_mapDTO;
import com.PHM_travel_mapDTO.PHM_travel_planDTO;

@WebServlet("/travelplan3Con_send_data")
public class travelplan3Con_send_data extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			HttpSession session = request.getSession();
			PHM_travel_mapDTO travelplan2 = (PHM_travel_mapDTO)session.getAttribute("travelplan2");
			String id = "admin";
			String title = travelplan2.getTitle();
			String people = travelplan2.getPeople();
			String startDate = travelplan2.getStart_date();
			String endDate = travelplan2.getEnd_date();
			int start_date = Integer.parseInt(travelplan2.getStart_date().substring(8));
			int end_date = Integer.parseInt(travelplan2.getEnd_date().substring(8));
			int total_date = end_date - start_date + 1;
	
			String info = null;
			String day = null;
			String cnt = null;
			String map_name = null;
			String startTime = null;
			String endTime =null;
			String memo = null;
			PHM_travel_planDTO dto = null;
			ArrayList<PHM_travel_planDTO> arr = new ArrayList<PHM_travel_planDTO>();
			// 저장해야할 데이터 
			//테이블1 : 여행이름 / 가는날 / 오늘날 / 인원수							->>>>> session에 있음
			//테이블2 : 일차,  여행순서 , 여행지이름, 여행시간 도착시간, 출발시간,  메모	->>>>> input으로 각각 받아와야함 
			// 		+ 작성자 아이디 / 여행이름 									->>>>> session에 있음
	
			// 1_1_day / 1_1_cnt / 1_1_map_name / 1_1_startTime / 1_1_endTime / 1_1_memo
			for(int d =1; d < 5 ; d++) {
				for(int c = 1; c<5; c++) {
					info = d+"_"+c+"_"+"day";
					day = request.getParameter(info);
					
					if(day!=null) {
						cnt = request.getParameter(d+"_"+c+"_"+"cnt");
						map_name = request.getParameter(d+"_"+c+"_"+"map_name");
						startTime = request.getParameter(d+"_"+c+"_"+"startTime");
						endTime = request.getParameter(d+"_"+c+"_"+"endTime");
						memo = request.getParameter(d+"_"+c+"_"+"memo");
						dto = new PHM_travel_planDTO(day, cnt, map_name, startTime, endTime, memo);
						arr.add(dto);
					}
				}
			}
	
			
			PHM_travel_planDAO dao = new PHM_travel_planDAO();
			int suc1 = dao.insert_planData1(title,startDate,endDate,people,id);
			dao.insert_planData2(arr,id,title);
	
			response.sendRedirect("N3_mypage_login_1bookmark1.jsp");
	
	
	}

}
