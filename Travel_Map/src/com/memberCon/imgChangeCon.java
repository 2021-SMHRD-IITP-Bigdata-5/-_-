package com.memberCon;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.memberDAO.tm_memberDAO;
import com.memberDTO.tm_memberDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/imgChangeCon")
public class imgChangeCon extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		tm_memberDAO memberdao = new tm_memberDAO();
		tm_memberDTO memberdto = null;
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		memberdto = (tm_memberDTO) session.getAttribute("dto");
		ServletContext context = getServletContext();
		String saveDir = context.getRealPath("tm_upload");
		System.out.println(saveDir);
		int maxSize = 50 * 1024 * 1024;
		MultipartRequest multi = new MultipartRequest(request, saveDir, maxSize, "utf-8",
				new DefaultFileRenamePolicy());

		String mb_id = memberdto.getMb_id();
		String mb_img = multi.getFilesystemName("imgChange");
		System.out.println(mb_id);
		System.out.println(mb_img);

		memberdao.memberImgChange(mb_id, mb_img);
		memberdto.put("mb_img", mb_img);

		response.sendRedirect("N3_mypage_login_3myinfo.jsp");

	}
}
