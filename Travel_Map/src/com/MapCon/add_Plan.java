package com.MapCon;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add_Plan")
public class add_Plan extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String map_name = request.getParameter("map_name");
		System.out.println(map_name);
	
		response.setCharacterEncoding("utf-8");
		PrintWriter out= response.getWriter();
		out.print(map_name);
	
	
	
	
	
	
	
	
	
	
	}

}
