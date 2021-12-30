package com.MapCon;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.MapDAO.MapDAO;
import com.MapDTO.MapDTO;
import com.google.gson.Gson;

@WebServlet("/add_Plan")
public class add_Plan extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String map_name = request.getParameter("map_name");
		MapDAO dao = new MapDAO();
		MapDTO dto = dao.MapData2(map_name);
		
		
		Gson gson = new Gson();
		response.setCharacterEncoding("utf-8");
		PrintWriter out= response.getWriter();
		out.print(gson.toJson(dto));
	
	
	
	}

}
