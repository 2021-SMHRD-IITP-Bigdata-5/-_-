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

@WebServlet("/MapSearchCon")
public class MapSearchCon extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String keyword = request.getParameter("keyword");
		
		MapDAO dao = new MapDAO();
		
		ArrayList<MapDTO> SearchList = dao.MapSearch(keyword);
		
		Gson gson = new Gson();
		
		String json = gson.toJson(SearchList);
		
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.print(json);
	}

}
