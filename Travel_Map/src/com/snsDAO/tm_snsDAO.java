package com.snsDAO;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.snsDTO.tm_snsDTO;

public class tm_snsDAO {

	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	tm_snsDTO dto = null;

	int cnt = 0;

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

	public int uploadFile(tm_snsDTO dto) {

		getConn();
		try {
			String sql = "INSERT INTO t_travel_board(tb_title,tb_content,tb_file,mb_id,travel_seq)" //
					+ "VALUES(?,?,?,?,13)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getTb_title());
			psmt.setString(2, dto.getTb_content());
			psmt.setString(3, dto.getTb_file());
			psmt.setString(4, dto.getMb_id());

			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		close();

		return cnt;
	}

	public ArrayList<tm_snsDTO> selectAll() {

		getConn();

		ArrayList<tm_snsDTO> list = new ArrayList<tm_snsDTO>();

		try {
			if (conn != null) {
			} else {
			}

			String sql = "SELECT * FROM t_travel_board ORDER BY tb_seq DESC ";
			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();

			while (rs.next() == true) {

				int tb_seq = rs.getInt("tb_seq");
				String tb_title = rs.getString("tb_title");
				String tb_content = rs.getString("tb_content");
				String tb_file = rs.getString("tb_file");
				int tb_cnt = rs.getInt("tb_cnt");
				int tb_likes = rs.getInt("tb_likes");
				int tb_total = rs.getInt("tb_total");
				String tb_open = rs.getString("tb_open");
				String mb_id = rs.getString("mb_id");
				int travel_seq = rs.getInt("travel_seq");

				dto = new tm_snsDTO(tb_seq, tb_title, tb_content, tb_file, tb_cnt, //
						tb_likes, tb_total, tb_open, mb_id, travel_seq);
				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public tm_snsDTO SelectOne(int tb_seq) {

		getConn();

		try {
			if (conn != null) {
			} else {
			}

			String sql = "SELECT * FROM t_travel_board where tb_seq = ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, tb_seq);
			rs = psmt.executeQuery();

			while (rs.next() == true) {

				int tb_seq1 = rs.getInt("tb_seq");
				String tb_title = rs.getString("tb_title");
				String tb_content = rs.getString("tb_content");
				String tb_file = rs.getString("tb_file");
				int tb_cnt = rs.getInt("tb_cnt");
				int tb_likes = rs.getInt("tb_likes");
				int tb_total = rs.getInt("tb_total");
				String tb_open = rs.getString("tb_open");
				String mb_id = rs.getString("mb_id");
				int travel_seq = rs.getInt("travel_seq");

				dto = new tm_snsDTO(tb_seq1, tb_title, tb_content, tb_file, tb_cnt, //
						tb_likes, tb_total, tb_open, mb_id, travel_seq);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return dto;
	}

	public void CountInc(int tb_seq) {

		getConn();

		try {
			String sql = "UPDATE t_travel_board SET tb_cnt = tb_cnt + 1 WHERE tb_seq = ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, tb_seq);

			psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

		close();

	}

	public int likesInc(int tb_seq) {

		int tb_likes = 0;
		getConn();
		try {
			String sql = "UPDATE t_travel_board SET tb_likes = tb_likes + 1 WHERE tb_seq = ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, tb_seq);

			psmt.executeUpdate();

			sql = "SELECT * FROM t_travel_board WHERE tb_seq = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, tb_seq);
			rs = psmt.executeQuery();
			while (rs.next() == true) {
				tb_likes = rs.getInt("tb_likes");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

		close();
		return tb_likes;

	}

	public void selectMember(String mb_id) {

		getConn();
		
		close();

	}
}
