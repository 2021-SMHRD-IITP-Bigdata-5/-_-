package com.boardCon;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.PHM_travel_mapDAO.PHM_travel_planDAO;
import com.PHM_travel_mapDTO.PHM_travel_mapDTO;
import com.PHM_travel_mapDTO.PHM_travel_planDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/boardInsert")
public class boardInsert extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		ServletContext context = getServletContext();
		String saveDir = context.getRealPath("board_upload");
		System.out.println(saveDir);
		int maxSize = 50 * 1024 * 1024;
		MultipartRequest multi = new MultipartRequest(request, saveDir, maxSize, "utf-8",
				new DefaultFileRenamePolicy());
		
		PHM_travel_planDAO dao = new PHM_travel_planDAO();
		
		ArrayList<PHM_travel_mapDTO> arr1 = (ArrayList<PHM_travel_mapDTO>)session.getAttribute("plan1");
		ArrayList<PHM_travel_planDTO> arr2 = (ArrayList<PHM_travel_planDTO>)session.getAttribute("plan2");
	
		String tb_title = multi.getParameter("tb_title");
		String tb_content = multi.getParameter("tb_content");
		String tb_file = multi.getFilesystemName("tb_file");
		int tb_cnt = 0;
		int tb_likes = 0;
		
		String tb_open = multi.getParameter("tb_open");
		String mb_id = "admin"; 
		String t_title = (String)session.getAttribute("t_title");
		int people = Integer.parseInt(dao.bring_people(t_title));
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	}

}
