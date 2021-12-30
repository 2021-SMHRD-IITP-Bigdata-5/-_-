package com.MapCon;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.MapDAO.MapDAO;
import com.MapDTO.MapDTO;

@WebServlet("/favorite_insertCon")
public class favorite_insertCon extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String map_name = request.getParameter("map_name");
		
		MapDAO dao = new MapDAO();
		MapDTO bring_dto = dao.bring_map(map_name);
		int insert_confirm = dao.insert_favorit(bring_dto);
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(insert_confirm);
	
	}

}
