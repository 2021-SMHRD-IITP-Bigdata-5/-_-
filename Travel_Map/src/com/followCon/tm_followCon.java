package com.followCon;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.followDAO.tm_followDAO;
import com.followDTO.tm_followDTO;
import com.google.gson.Gson;
import com.memberDAO.tm_memberDAO;
import com.memberDTO.tm_memberDTO;
import com.snsDAO.tm_snsDAO;
import com.snsDTO.follower;

@WebServlet("/tm_followCon")
public class tm_followCon extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		tm_followDAO follow_dao = new tm_followDAO();
		tm_memberDAO dao = new tm_memberDAO();
		tm_snsDAO snsdao = new tm_snsDAO();
		int follow_cnt = 0;

		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		tm_memberDTO dto = (tm_memberDTO) session.getAttribute("dto");
		String f_id = request.getParameter("f_id");
		String mb_id = dto.getMb_id();

		tm_followDTO follow_dto = new tm_followDTO(f_id, mb_id);

		boolean check = follow_dao.checkFollow(follow_dto);

		// check = true 로 넘어올 때, 팔로우 가능

		if (check) {

			follow_cnt = follow_dao.follow(follow_dto);

			// 팔로우 테이블 데이터 저장 후

			if (follow_cnt > 0) {

				String conID = (String) session.getAttribute("conID");
				
				// 멤버 테이블 카운트
				dao.followIncrease(mb_id);
				dao.followerIncrease(f_id, conID);

			}
		} // check = false 로 넘어올 때 , 언팔
		else {
			follow_cnt = follow_dao.unFollow(follow_dto);

			if (follow_cnt > 0) {

				// 멤버 테이블 카운트 다운

				dao.followDecrease(mb_id);
				dao.followerDecrease(f_id);

			}
		}

		tm_memberDTO opdto = snsdao.selectMember(f_id);

		session.setAttribute("opdto", opdto);

		PrintWriter out = response.getWriter();

		Gson gson = new Gson();

		String json = gson.toJson(new follower(check, opdto.getMb_follower()));

		out.print(json);

	}

}
