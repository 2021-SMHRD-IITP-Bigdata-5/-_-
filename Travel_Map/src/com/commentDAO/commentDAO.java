package com.commentDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.commentDTO.commentDTO;
import com.snsDTO.tm_snsDTO;

public class commentDAO {

	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

	commentDTO commdto = null;

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

	public ArrayList<commentDTO> allComment(int tb_seq) {

		getConn();
		ArrayList<commentDTO> list = new ArrayList<commentDTO>();

		try {
			if (conn != null) {
			} else {
			}

			String sql = "SELECT * FROM t_travel_comment WHERE tb_seq = ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, tb_seq);

			rs = psmt.executeQuery();

			while (rs.next() == true) {

				int comm_seq = rs.getInt("comm_seq");
				int tb_seq1 = rs.getInt("tb_seq");
				String comm_content = rs.getString("comm_content");
				String reg_date = rs.getString("reg_date");
				int comm_likes = rs.getInt("comm_likes");
				String mb_id = rs.getString("mb_id");

				commdto = new commentDTO(comm_seq, tb_seq1, comm_content, reg_date, comm_likes, mb_id);

				list.add(commdto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public int addComment(int tb_seq, String commContent, String mb_id) {

		getConn();

		try {
			String sql = "INSERT INTO t_travel_comment(tb_seq,comm_content,mb_id) VALUES(?,?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, tb_seq);
			psmt.setString(2, commContent);
			psmt.setString(3, mb_id);
			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return cnt;
	}

}
