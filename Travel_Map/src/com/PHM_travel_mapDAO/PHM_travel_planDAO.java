package com.PHM_travel_mapDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.MapDTO.MapDTO;
import com.PHM_travel_mapDTO.PHM_travel_mapDTO;
import com.PHM_travel_mapDTO.PHM_travel_planDTO;

public class PHM_travel_planDAO {
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	MapDTO dto = null;
	
	public void getConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524";
			String dbid = "cgi_7_6_1216";
			String dbpw = "smhrd6";

			conn = DriverManager.getConnection(url, dbid, dbpw);

		} catch (ClassNotFoundException|SQLException  e) {
			e.printStackTrace();
			System.out.println("con문제");
		}

		
	}
	
	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void insert_planData1(String title,String startDate,String endDate,String people,String id) {
		int cnt = 0;
		try {
			getConn();
			
			String sql = "insert into phm_travel_plan values (?,?,?,?,sysdate,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, startDate);
			psmt.setString(3, endDate);
			psmt.setString(4, people);
			psmt.setString(5, id);

			cnt = psmt.executeUpdate();
			
			
			sql = "insert into t_event_log values(?,?,?,?,?)";
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, "insert");
			psmt.setString(3, null);
			psmt.setString(4, null);
			psmt.setString(5, null);
			psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();

		}
	}
	
	public void insert_planData2(ArrayList<PHM_travel_planDTO> arr,String id, String title) {
		String sql =null;
		try {
			getConn();
			
			for(int i =0; i<arr.size();i++) {
				sql = "insert into phm_travel_plan2 values (?,?,?,?,?,?,?,?)";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, arr.get(i).getDay());
				psmt.setString(2, arr.get(i).getCnt());
				psmt.setString(3, arr.get(i).getMap_name());
				psmt.setString(4, arr.get(i).getStartTime());
				psmt.setString(5, arr.get(i).getEndTime());
				psmt.setString(6, arr.get(i).getMemo());
				psmt.setString(7, id);
				psmt.setString(8, title);
				
	
				psmt.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();

		}
	}

	public ArrayList<PHM_travel_mapDTO> bring_planData1(String mb_id) {
		PHM_travel_mapDTO dto = null;
		ArrayList<PHM_travel_mapDTO> arr = new ArrayList<PHM_travel_mapDTO>();
		try {
			getConn();
			
			String sql = "select * from phm_travel_plan where mb_id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,mb_id);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				String title = rs.getString(1);
				String start_date = rs.getString(2);
				String end_date = rs.getString(3);
				String people = rs.getString(4);
				String createDate = rs.getString(5);
				String mb_id2 = rs.getString(6);
				dto = new PHM_travel_mapDTO(title, start_date, end_date, people, createDate, mb_id2);
				arr.add(dto);
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();

		}
		return arr;
	}
	
	public ArrayList<PHM_travel_planDTO> bring_planData2(String t_title) {
		PHM_travel_planDTO dto = null;
		ArrayList<PHM_travel_planDTO> arr = new ArrayList<PHM_travel_planDTO>();
		try {
			getConn();
			
			String sql = "select * from phm_travel_plan2 where t_title=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, t_title);
	
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				String t_day = rs.getString(1);
				String days_cnt = rs.getString(2);
				String map_name = rs.getString(3);
				String startTime = rs.getString(4);
				String endTime = rs.getString(5);
				String memo = rs.getString(6);
				String mb_id =rs.getString(7);
				String t_title2 = rs.getString(8);
				dto = new PHM_travel_planDTO(t_day, days_cnt, map_name, startTime, endTime, memo, mb_id, t_title2);
				arr.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();

		}
		return arr;
	}



	public String bring_people(String t_title) {
		String people = null;
		try {
			getConn();
			
			String sql = "select * from phm_travel_plan where t_title=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, t_title);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				people = rs.getString(4);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();

		}
		return people;
	}

	public PHM_travel_mapDTO bring_travelplan1(String mb_id, String t_title) {
		PHM_travel_mapDTO dto = null;
		try {
			getConn();
			
			String sql = "select * from phm_travel_plan where mb_id=? and t_title=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, mb_id);
			psmt.setString(2, t_title);
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				String title = rs.getString(1);
				String start_date = rs.getString(2);
				String end_date = rs.getString(3);
				String people = rs.getString(4);
				String createDate = rs.getString(5);
				dto = new PHM_travel_mapDTO(title, start_date, end_date, people, createDate, mb_id);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();

		}
		return dto;
	}

	public ArrayList<PHM_travel_planDTO> bring_travelplan2(String mb_id, String t_title) {
		PHM_travel_planDTO dto = null;
		ArrayList<PHM_travel_planDTO> arr = new ArrayList<PHM_travel_planDTO>();
		try {
			getConn();
			
			String sql = "select * from phm_travel_plan2 where mb_id=? and t_title=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, mb_id);
			psmt.setString(2, t_title);
	
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				String t_day = rs.getString(1);
				String days_cnt = rs.getString(2);
				String map_name = rs.getString(3);
				String startTime = rs.getString(4);
				String endTime = rs.getString(5);
				String memo = rs.getString(6);
				String t_title2 = rs.getString(8);
				dto = new PHM_travel_planDTO(t_day, days_cnt, map_name, startTime, endTime, memo, mb_id, t_title2);
				arr.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();

		}
		return arr;
	}
}
