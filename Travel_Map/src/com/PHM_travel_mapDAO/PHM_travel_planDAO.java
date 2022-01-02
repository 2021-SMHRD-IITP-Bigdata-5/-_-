package com.PHM_travel_mapDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.MapDTO.MapDTO;
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
	public int insert_planData1(String title,String startDate,String endDate,String people,String id) {
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

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();

		}
		return cnt;
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




}
