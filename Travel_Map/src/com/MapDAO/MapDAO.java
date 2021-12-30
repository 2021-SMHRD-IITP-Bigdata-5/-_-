package com.MapDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.MapDTO.MapDTO;


public class MapDAO {
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

	public ArrayList<MapDTO> MapData(String division) {
		ArrayList<MapDTO> arr = new ArrayList<MapDTO>();
		int cnt = 0;
		try {
			getConn();
			String sql = "select * from t_map where map_type =?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, division);
			rs = psmt.executeQuery();
			cnt = 1;
			while (rs.next()) {
				String map_seq = rs.getString(1);
				String map_name = rs.getString(2);
				String map_latitude = rs.getString(3);
				String map_longtitude = rs.getString(4);
				String map_type = rs.getString(5);
				String map_stars = rs.getString(6);
				String map_info = rs.getString(7);
				String map_addr = rs.getString(8);
				String map_img = rs.getString(11);
				dto = new MapDTO(map_seq, map_name, map_latitude, map_longtitude, map_type, map_stars, map_info, map_addr, map_img);
				arr.add(dto);
				cnt++;
				if(cnt==150) {
					break;
				}
				
			}
		} catch (Exception e) {
			System.out.println("클래스파일 로딩실패");
			e.printStackTrace();
		} finally {
			close();
		}
		return arr;
	}
	
	public MapDTO MapData2(String map_name) {
		MapDTO dto = null;
		try {
			getConn();
			String sql = "select * from t_map where map_name =?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, map_name);
			rs = psmt.executeQuery();
			if (rs.next()) {
				String map_seq = rs.getString(1);
				String map_name2 = rs.getString(2);
				String map_latitude = rs.getString(3);
				String map_longtitude = rs.getString(4);
				String map_type = rs.getString(5);
				String map_stars = rs.getString(6);
				String map_info = rs.getString(7);
				String map_addr = rs.getString(8);
				String map_img = rs.getString(11);
				dto = new MapDTO(map_seq, map_name2, map_latitude, map_longtitude, map_type, map_stars, map_info, map_addr, map_img);
				
			}
		} catch (Exception e) {
			System.out.println("클래스파일 로딩실패");
			e.printStackTrace();
		} finally {
			close();
		}
		return dto;
	}

	public MapDTO bring_map(String map_name) {
		MapDTO dto = null;
		try {
			getConn();
			String sql = "select * from t_map where map_name = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, map_name);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				String map_addr = rs.getString(8);
					dto = new MapDTO(map_name, map_addr);
			}
				
			
		} catch (Exception e) {
			System.out.println("클래스파일 로딩실패");
			e.printStackTrace();
		} finally {
			close();
		}
		return dto;
	}
	
	public int insert_favorit(MapDTO dto) {
		int cnt = 0;
		try {
			getConn();
			String sql = "insert into T_MAP_FAVORITES values(?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getMap_name());
			psmt.setString(2, dto.getMap_addr());
			cnt = psmt.executeUpdate();
			
				
			
		} catch (Exception e) {
			System.out.println("클래스파일 로딩실패");
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}
	
	public ArrayList<MapDTO> bring_favorit() {
		ArrayList<MapDTO> arr = new ArrayList<MapDTO>();
		MapDTO dto = null;
		try {
			getConn();
			String sql = "select * from T_MAP_FAVORITES";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				String map_name = rs.getString(1);
				String map_addr = rs.getString(2);
				dto = new MapDTO(map_name, map_addr);
				arr.add(dto);
			}
				
			
		} catch (Exception e) {
			System.out.println("클래스파일 로딩실패");
			e.printStackTrace();
		} finally {
			close();
		}
		return arr;
	}
	
	public int delete_favorite(String map_name) {
		int cnt = 0;
		try {
			getConn();
			String sql = "delete from T_MAP_FAVORITES where FAV_MAP_NAME = ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, map_name);
			cnt = psmt.executeUpdate();
			
				
			
		} catch (Exception e) {
			System.out.println("클래스파일 로딩실패");
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}
	
	public ArrayList<MapDTO> MapSearch(String keyword) {
		
		ArrayList<MapDTO> MapArr = new ArrayList<MapDTO>();
		
		try {
			getConn();
			
			String sql = "select * from t_map where map_name like ? or map_addr like ?";
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, "%" + keyword + "%");
			psmt.setString(2, "%" + keyword + "%");
			rs = psmt.executeQuery();
			
			while (rs.next() == true) {
				String map_seq = rs.getString(1);
				String map_name = rs.getString(2);
				String map_latitude = rs.getString(3);
				String map_longtitude = rs.getString(4);
				String map_type = rs.getString(5);
				String map_stars = rs.getString(6);
				String map_info = rs.getString(7);
				String map_addr = rs.getString(8);
				String map_img = rs.getString(11);
				dto = new MapDTO(map_seq, map_name, map_latitude, map_longtitude, map_type, map_stars, map_info, map_addr, map_img);
				MapArr.add(dto);
			}
			
		} catch (Exception e) {
			System.out.println("클래스파일 로딩실패");
			e.printStackTrace();
		} finally {
			close();
		}
		return MapArr;
	}


}
