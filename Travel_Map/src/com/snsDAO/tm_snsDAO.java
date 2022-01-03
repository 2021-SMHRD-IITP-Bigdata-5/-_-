package com.snsDAO;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.memberDTO.tm_memberDTO;
import com.snsDTO.tm_snsDTO;

public class tm_snsDAO {

	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	tm_snsDTO dto = null;

	tm_memberDTO memberdto = null;

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
			String sql = "INSERT INTO phm_travel_board(tb_title,tb_content,tb_file,mb_id)" //
					+ "VALUES(?,?,?,?)";
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

			String sql = "SELECT * FROM phm_travel_board  "; // ORDER BY tb_seq DESC
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
				String t_title = rs.getString("t_title");

				dto = new tm_snsDTO(tb_seq, tb_title, tb_content, tb_file, tb_cnt, //
						tb_likes, tb_total, tb_open, mb_id, t_title);
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

			String sql = "SELECT * FROM phm_travel_board where tb_seq = ? ";
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
				String t_title = rs.getString("t_title");

				dto = new tm_snsDTO(tb_seq1, tb_title, tb_content, tb_file, tb_cnt, //
						tb_likes, tb_total, tb_open, mb_id, t_title);
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
			String sql = "UPDATE phm_travel_board SET tb_cnt = tb_cnt + 1 WHERE tb_seq = ? ";
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
			String sql = "UPDATE phm_travel_board SET tb_likes = tb_likes + 1 WHERE tb_seq = ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, tb_seq);

			psmt.executeUpdate();

			sql = "SELECT * FROM phm_travel_board WHERE tb_seq = ?";
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

	public tm_memberDTO selectMember(String f_id) {

		getConn();

		try {
			if (conn != null) {

			} else {
			}

			String sql = "SELECT * FROM t_member WHERE mb_id = ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, f_id);
			rs = psmt.executeQuery();

			while (rs.next() == true) {

				String mb_img = rs.getString("mb_img");
				String mb_nickname = rs.getString("mb_nickname");
				int mb_follow = rs.getInt("mb_follow");
				int mb_follower = rs.getInt("mb_follower");

				memberdto = new tm_memberDTO(f_id, mb_img, mb_nickname, mb_follow, mb_follower);
			} // 선택 상대 mb_id

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return memberdto;

	}

	public ArrayList<tm_snsDTO> searchMy(String mb_id) {

		getConn();

		ArrayList<tm_snsDTO> list = new ArrayList<tm_snsDTO>();

		try {
			if (conn != null) {
			} else {
			}

			String sql = "SELECT * FROM phm_travel_board WHERE mb_id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, mb_id);

			rs = psmt.executeQuery();

			while (rs.next() == true) {

				String tb_file = rs.getString("tb_file");
				String tb_open = rs.getString("tb_open");
				String t_title = rs.getString("t_title");

				dto = new tm_snsDTO(mb_id, tb_file, tb_open, t_title);
				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public ArrayList<tm_snsDTO> TitleSearch(String keyword) {

		ArrayList<tm_snsDTO> TitleArr = new ArrayList<tm_snsDTO>();

		if (keyword == "") {

			return null;

		} else {

			try {

				getConn();

				String sql = "select * from phm_travel_board where tb_title like ?";

				psmt = conn.prepareStatement(sql);
				psmt.setString(1, "%" + keyword + "%");
				rs = psmt.executeQuery();

				while (rs.next() == true) {
					int tb_seq = rs.getInt("tb_seq");
					String tb_title = rs.getString(2);
					String tb_file = rs.getString(4);
					int tb_cnt = rs.getInt(5);
					int tb_likes = rs.getInt(7);
					dto = new tm_snsDTO(tb_seq, tb_title, tb_file, tb_cnt, tb_likes);
					TitleArr.add(dto);
				}

			} catch (Exception e) {
				System.out.println("클래스파일 로딩실패");
				e.printStackTrace();
			} finally {
				close();
			}
		}
		return TitleArr;
	}

	public ArrayList<tm_snsDTO> snsDetail(String user_id) {

		ArrayList<tm_snsDTO> DetailArr = new ArrayList<tm_snsDTO>();

		try {

			getConn();

			String sql = "select * from phm_travel_board where mb_id = ?";

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, user_id);
			rs = psmt.executeQuery();

			while (rs.next() == true) {
				int tb_seq = rs.getInt(1);
				String tb_file = rs.getString(4);
				dto = new tm_snsDTO(tb_seq, tb_file);
				DetailArr.add(dto);

			}

		} catch (Exception e) {
			System.out.println("클래스파일 로딩실패");
			e.printStackTrace();
		} finally {
			close();
		}
		return DetailArr;
	}
}
