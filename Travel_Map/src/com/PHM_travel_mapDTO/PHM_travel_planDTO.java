package com.PHM_travel_mapDTO;

public class PHM_travel_planDTO {
	String day;
	String cnt;
	String map_name;
	String startTime;
	String endTime;
	String memo;
	public PHM_travel_planDTO(String day, String cnt, String map_name, String startTime, String endTime, String memo) {
		super();
		this.day = day;
		this.cnt = cnt;
		this.map_name = map_name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.memo = memo;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getCnt() {
		return cnt;
	}
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}
	public String getMap_name() {
		return map_name;
	}
	public void setMap_name(String map_name) {
		this.map_name = map_name;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
	
}
