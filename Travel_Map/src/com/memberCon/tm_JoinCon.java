package com.memberCon;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.memberDAO.tm_memberDAO;
import com.memberDTO.tm_memberDTO;

@WebServlet("/tm_JoinCon")
public class tm_JoinCon extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String addr = request.getParameter("addr");
		String img = ""; 
		String nickname = request.getParameter("nickname");
		int mb_follow = 0;
		int mb_follower = 0;
		String admin_yn = "n";

		tm_memberDTO dto = new tm_memberDTO(id, pw, name, age, gender, email, addr, img, nickname, mb_follow,
				mb_follower, admin_yn);
		tm_memberDAO dao = new tm_memberDAO();

		int cnt = dao.Join(dto);

		request.setCharacterEncoding("utf-8");

		if (cnt > 0) {
			System.out.println("가입성공");
			response.sendRedirect("tm_Main.jsp");
		} else {
			System.out.println("가입실패");

		}

	}

}
