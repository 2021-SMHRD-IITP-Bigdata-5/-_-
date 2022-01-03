package com.memberDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
			String sql = "INSERT INTO t_member VALUES(?,?,?,?,?,?,?,?,?,?,?,sysdate,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getMb_id());
			psmt.setString(2, dto.getMb_pw());
			psmt.setString(3, dto.getMb_name());
			psmt.setString(4, dto.getMb_age());
			psmt.setString(5, dto.getMb_gender());
			psmt.setString(6, dto.getMb_email());
			psmt.setString(7, dto.getMb_addr());
			psmt.setString(8, dto.getMb_img());
			psmt.setString(9, dto.getMb_nickname());
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

	public tm_memberDTO Login(tm_memberDTO dto) {

		getConn();

		tm_memberDTO sessiondto = null;

		try {
			String sql = "SELECT * FROM t_member WHERE mb_id = ? and mb_pw = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getMb_id());
			psmt.setString(2, dto.getMb_pw());
			rs = psmt.executeQuery();

			if (rs.next()) {
				String session_id = rs.getString("mb_id");
				String session_pw = rs.getString("mb_pw");
				String session_name = rs.getString("mb_name");
				String session_age = rs.getString("mb_age");
				String session_gender = rs.getString("mb_gender");
				String session_email = rs.getString("mb_email");
				String session_addr = rs.getString("mb_addr");
				String session_img = rs.getString("mb_img");
				String session_nickname = rs.getString("mb_nickname");
				int session_follow = rs.getInt("mb_follow");
				int session_follower = rs.getInt("mb_follower");
				String session_joindate = rs.getString("mb_joindate");
				String session_admin_yn = rs.getString("admin_yn");

				sessiondto = new tm_memberDTO(session_id, session_pw, session_name, session_age, session_gender,
						session_email, session_addr, session_img, session_nickname, session_follow, session_follower,
						session_joindate, session_admin_yn);
			} else {
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		close();

		return sessiondto;
	}

	public boolean Check(String id) {

		getConn();
		try {
			String sql = "SELECT * FROM t_member WHERE mb_id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);

			rs = psmt.executeQuery();
			if (rs.next()) {
				check = true;
			} else {
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return check;
	}

	public void followIncrease(String mb_id) {

		getConn();

		try {
			String sql = "UPDATE t_member SET mb_follow = mb_follow+1 WHERE mb_id = ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, mb_id);

			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

		close();

	}

public void followerIncrease(String f_id, String conID) {
		
		String mb_img = "";
		String mb_nickname = "";
		
		getConn();

		try {
			String sql = "UPDATE t_member SET mb_follower = mb_follower+1 WHERE mb_id = ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, f_id);

			cnt = psmt.executeUpdate();
			
			sql = "select * from t_member where mb_id = ?";
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, conID);
			rs = psmt.executeQuery();
			while (rs.next() == true) {
				mb_nickname = rs.getString("mb_nickname");
				mb_img = rs.getString("mb_img");
			}
			
			sql = "insert into t_event_log values(?,?,?,?,?)";
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, f_id);
			psmt.setString(2, "follower");
			psmt.setInt(3, (Integer) null);
			psmt.setString(4, mb_nickname);
			psmt.setString(5, mb_img);
			psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

		close();

	}

	public void followDecrease(String mb_id) {

		getConn();

		try {
			String sql = "UPDATE t_member SET mb_follow = mb_follow-1 WHERE mb_id = ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, mb_id);

			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

		close();

	}

	public void followerDecrease(String f_id) {
		getConn();

		try {
			String sql = "UPDATE t_member SET mb_follower = mb_follower-1 WHERE mb_id = ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, f_id);

			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

		close();

	}

	public tm_memberDTO countFollow(String mb_id) {
		getConn();

		try {
			String sql = "SELECT mb_follow,mb_follower FROM t_member WHERE mb_id = ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, mb_id);

			rs = psmt.executeQuery();

			while (rs.next()) {
				int mb_follow = rs.getInt("mb_follow");
				int mb_follower = rs.getInt("mb_follower");
				dto = new tm_memberDTO(mb_follow, mb_follower);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

		close();
		return dto;
	}

	public ArrayList<tm_memberDTO> UserSearch(String keyword) {

		ArrayList<tm_memberDTO> UserArr = new ArrayList<tm_memberDTO>();

		if (keyword == "") {

			return null;

		} else {

			try {
				getConn();

				String sql = "select * from t_member where mb_nickname like ?";

				psmt = conn.prepareStatement(sql);
				psmt.setString(1, "%" + keyword + "%");
				rs = psmt.executeQuery();

				while (rs.next() == true) {
					String mb_id = rs.getString(1);
					String mb_img = rs.getString(8);
					String mb_nickname = rs.getString(9);
					int mb_follow = rs.getInt(10);
					int mb_follower = rs.getInt(11);
					dto = new tm_memberDTO(mb_id, mb_img, mb_nickname, mb_follow, mb_follower);
					UserArr.add(dto);
				}

			} catch (Exception e) {
				System.out.println("클래스파일 로딩실패");
				e.printStackTrace();
			} finally {
				close();
			}
		}
		return UserArr;
	}

	public String imgMemberSelect(String mb_id) {

		getConn();

		String mb_img = "";

		try {

			String sql = "select mb_img from t_member where mb_id = ?";

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, mb_id);
			rs = psmt.executeQuery();

			while (rs.next() == true) {
				mb_img = rs.getString(1);
			}

		} catch (Exception e) {
			System.out.println("클래스파일 로딩실패");
			e.printStackTrace();
		} finally {
			close();
		}

		return mb_img;

	}

	public void memberImgChange(String mb_id, String mb_img) {
		getConn();

		try {
			String sql = "UPDATE t_member SET mb_img = ? WHERE mb_id = ?";

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, mb_img);
			psmt.setString(2, mb_id);
			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("클래스파일 로딩실패");
			e.printStackTrace();
		} finally {
			close();
		}
	}

}
