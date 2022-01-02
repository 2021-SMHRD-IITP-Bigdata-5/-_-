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

@WebServlet("/my_bookmarkMap")
public class my_bookmarkMap extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			String map_name1 = request.getParameter("map_name1");
			String map_name2 = request.getParameter("map_name2");
			String map_name3 = request.getParameter("map_name3");
			String map_name4 = request.getParameter("map_name4");
			String map_name5 = request.getParameter("map_name5");
			MapDTO dto = null;
			MapDAO dao = new MapDAO();
			ArrayList<MapDTO> list = new ArrayList<MapDTO>();
			
			if(map_name1!=null) {
				dto = dao.MapData2(map_name1);
				if(dto!=null) {
					list.add(dto);
				}
			}
			if(map_name2!=null) {
				dto = dao.MapData2(map_name2);
				if(dto!=null) {
					list.add(dto);
				}
			}
			if(map_name3!=null) {
				dto = dao.MapData2(map_name3);
				if(dto!=null) {
					list.add(dto);
				}
			}
			if(map_name4!=null) {
				dto = dao.MapData2(map_name4);
				if(dto!=null) {
					list.add(dto);
				}
			}
			if(map_name5!=null) {
				dto = dao.MapData2(map_name5);
				if(dto!=null) {
					list.add(dto);
				}
			}
	
			// 응답
			response.setCharacterEncoding("utf-8");
			PrintWriter out= response.getWriter();
			
			// Gson으로 json으로 변환해서 응답
			Gson gson = new Gson();
			out.print(gson.toJson(list));
		
	
	
	
	
	
	
	
	
	
	}

}
