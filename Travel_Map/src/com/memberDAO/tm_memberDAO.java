package com.memberDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpSession;

import com.memberDTO.tm_memberDTO;

public class tm_memberDAO {

	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

	tm_memberDTO dto = null;

	int cnt = 0;

	boolean check = false;

	public void getConn() {

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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	public void close() {
		try {
			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int Join(tm_memberDTO dto) {

		getConn();

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
			String sql = "INSERT INTO t_member VALUES(?,?,?,?,?,?,?,?,?,?,?,sysdate,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPw());
			psmt.setString(3, dto.getName());
			psmt.setInt(4, dto.getAge());
			psmt.setString(5, dto.getGender());
			psmt.setString(6, dto.getEmail());
			psmt.setString(7, dto.getAddr());
			psmt.setString(8, dto.getImg());
			psmt.setString(9, dto.getNickname());
			psmt.setInt(10, dto.getMb_follow());
			psmt.setInt(11, dto.getMb_follower());
			psmt.setString(12, dto.getAdmin_yn());

			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		close();
		return cnt;

	}

	public boolean Login(tm_memberDTO dto) {

		getConn();

		try {
			String sql = "SELECT * FROM t_member WHERE mb_id = ? and mb_pw = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPw());
			rs = psmt.executeQuery();

			if (rs.next()) {
				check = true;
			} else {
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		close();
		return check;
	}
}
