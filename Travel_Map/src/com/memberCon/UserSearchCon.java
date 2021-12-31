package com.memberCon;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.memberDAO.tm_memberDAO;
import com.memberDTO.tm_memberDTO;

@WebServlet("/UserSearchCon")
public class UserSearchCon extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String keyword = request.getParameter("keyword");
		
		tm_memberDAO dao = new tm_memberDAO();
		
		ArrayList<tm_memberDTO> UserList = dao.UserSearch(keyword);
		
		Gson gson = new Gson();
		
		String json = gson.toJson(UserList);
		
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.print(json);
	}

}
