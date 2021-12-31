package com.PHM_travel_mapCon;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.PHM_travel_mapDTO.PHM_travel_mapDTO;

@WebServlet("/travelplan2Con")
public class travelplan2Con extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		
		String title = request.getParameter("travel_title");
		String start_date = request.getParameter("start_date");
		String end_date = request.getParameter("end_date");
		String people = request.getParameter("people");
	
		
		PHM_travel_mapDTO dto = new PHM_travel_mapDTO(title, start_date, end_date, people);
		session.setAttribute("travelplan2", dto);
		response.sendRedirect("N2_travelplan3.jsp");
		
	
	
	
	
	}

}
