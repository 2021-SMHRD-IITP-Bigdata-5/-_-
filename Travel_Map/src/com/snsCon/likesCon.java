package com.snsCon;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.snsDAO.tm_snsDAO;

@WebServlet("/likesCon")
public class likesCon extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		int tb_seq = Integer.parseInt(request.getParameter("tb_seq"));

		tm_snsDAO dao = new tm_snsDAO();

		int tb_likes = dao.likesInc(tb_seq);

		PrintWriter out = response.getWriter();

		out.print(tb_likes);
		
	}
}
