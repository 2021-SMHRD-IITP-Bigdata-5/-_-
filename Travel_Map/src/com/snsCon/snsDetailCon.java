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

@WebServlet("/snsDetailCon")
public class snsDetailCon extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String user_id = request.getParameter("user_id");
		
		tm_snsDAO dao = new tm_snsDAO();
		
		ArrayList<tm_snsDTO> DetailList = dao.snsDetail(user_id);
		
		Gson gson = new Gson();
		
		String json = gson.toJson(DetailList);
		
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.print(json);
		
	}

}
