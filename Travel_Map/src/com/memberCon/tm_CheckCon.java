package com.memberCon;

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

@WebServlet("/tm_CheckCon")
public class tm_CheckCon extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		boolean check = false;

		String id = request.getParameter("id");

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524";
			String dbid = "cgi_7_6_1216";
			String dbpw = "smhrd6";

			conn = DriverManager.getConnection(url, dbid, dbpw);
			if (conn != null) {
				System.out.println("접속 성공");
			} else {
			}

			String sql = "SELECT * FROM t_member WHERE mb_id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);

			rs = psmt.executeQuery();
			if (rs.next()) {
				check = true;
				System.out.println("중복");
			} else {
				System.out.println("가능");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

		PrintWriter out = response.getWriter();

		out.print(check);

	}

}
