package com.PHM_travel_mapCon;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.snsDTO.tm_snsDTO;

@WebServlet("/N_travelplanCon")
public class N_travelplanCon extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			HttpSession session = request.getSession();
			String mb_id = request.getParameter("mb_id");
			String t_title = request.getParameter("t_title");
			tm_snsDTO dto = new tm_snsDTO(mb_id, t_title);
			
			session.setAttribute("id_title", dto);
			response.sendRedirect("N7_travelplan.jsp");
	
	}

}
