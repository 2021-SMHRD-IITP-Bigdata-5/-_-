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
import com.PHM_travel_mapDTO.PHM_travel_planDTO;

@WebServlet("/my_bookmarkMove")
public class my_bookmarkMove extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String t_title = request.getParameter("t_title");
		System.out.println(t_title);
		
		PHM_travel_planDAO dao = new PHM_travel_planDAO();
		ArrayList<PHM_travel_planDTO> arr = dao.bring_planData2(t_title);
		
		session.setAttribute("plan2", arr);
		response.sendRedirect("N3_mypage_login_1bookmark2.jsp");
	}

}
