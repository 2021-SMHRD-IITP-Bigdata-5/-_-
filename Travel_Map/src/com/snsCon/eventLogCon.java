package com.snsCon;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.snsDAO.tm_snsDAO;
import com.snsDTO.tm_snsDTO;

@WebServlet("/eventLogCon")
public class eventLogCon extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String conID = request.getParameter("conID");
		
		tm_snsDAO dao = new tm_snsDAO();
		
		ArrayList<tm_snsDTO> logList = dao.eventLog(conID);
		
		Gson gson = new Gson();
		
		String json = gson.toJson(logList);
		
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.print(json);
	}

}