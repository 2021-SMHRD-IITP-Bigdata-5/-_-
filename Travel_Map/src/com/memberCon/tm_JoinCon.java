package com.memberCon;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.followDAO.tm_followDAO;
import com.memberDAO.tm_memberDAO;
import com.memberDTO.tm_memberDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/tm_JoinCon")
public class tm_JoinCon extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		ServletContext context = getServletContext();
		String saveDir = context.getRealPath("tm_upload");
		System.out.println(saveDir);
		int maxSize = 50 * 1024 * 1024;
		MultipartRequest multi = new MultipartRequest(request, saveDir, maxSize, "utf-8",
				new DefaultFileRenamePolicy());

		String mb_id = multi.getParameter("id");
		String mb_pw = multi.getParameter("pw");
		String mb_name = multi.getParameter("name");
		String mb_age = multi.getParameter("age");
		String mb_gender = multi.getParameter("gender");
		String mb_email = multi.getParameter("email");
		String mb_addr = multi.getParameter("addr");
		String mb_img = multi.getFilesystemName("file");
		System.out.println(mb_img);
		String mb_nickname = multi.getParameter("nickname");
		int mb_follow = 0;
		int mb_follower = 0;
		String admin_yn = "n";

		tm_memberDTO dto = new tm_memberDTO(mb_id, mb_pw, mb_name, mb_age, mb_gender, mb_email, mb_addr, mb_img,
				mb_nickname, mb_follow, mb_follower, admin_yn);
		tm_memberDAO dao = new tm_memberDAO();
		tm_followDAO follow_dao = new tm_followDAO();

		int member_cnt = dao.Join(dto);

		int follow_cnt = follow_dao.Join(mb_id);

		System.out.println(dto.getMb_id());

		if (member_cnt > 0 && follow_cnt > 0) {
			System.out.println("가입성공");
			response.sendRedirect("JoinComplete.jsp");
		} else {
			System.out.println("가입실패");

		}
	}
}
