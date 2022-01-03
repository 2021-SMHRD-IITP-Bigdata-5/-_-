package com.snsCon;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.memberDTO.tm_memberDTO;
import com.snsDAO.tm_snsDAO;
import com.snsDTO.tm_snsDTO;

@WebServlet("/tm_selectMember")
public class tm_selectMember extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String mb_id = request.getParameter("mb_id");
		tm_memberDTO opdto = null;
		tm_snsDAO dao = new tm_snsDAO();
		opdto = dao.selectMember(mb_id);
		if (opdto != null) {
			session.setAttribute("opdto", opdto);
			System.out.println("셀렉트 멤버 성공");
		} else {
			System.out.println("셀렉트 멤버 실패!");
		}

		response.setCharacterEncoding("utf-8");
		response.sendRedirect("N4_mypage_sns.jsp");

	}

}
