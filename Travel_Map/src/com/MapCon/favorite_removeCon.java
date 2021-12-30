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

@WebServlet("/favorite_removeCon")
public class favorite_removeCon extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String map_name = request.getParameter("map_name");
		MapDAO dao = new MapDAO();
		int cnt = dao.delete_favorite(map_name);
		ArrayList<MapDTO> arr = dao.bring_favorit();
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out= response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(arr));
	
	
	
	
	
	}

}
