package com.PHM_travel_mapDTO;

public class PHM_travel_mapDTO {
	String title;
	String start_date;
	String end_date;
	String people;
	String createDate;
	String mb_id;
	
	
	
	
	public PHM_travel_mapDTO(String title, String start_date, String end_date, String people, String createDate,
			String mb_id) {
		super();
		this.title = title;
		this.start_date = start_date;
		this.end_date = end_date;
		this.people = people;
		this.createDate = createDate;
		this.mb_id = mb_id;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public PHM_travel_mapDTO(String title, String start_date, String end_date, String people) {
		super();
		this.title = title;
		this.start_date = start_date;
		this.end_date = end_date;
		this.people = people;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getPeople() {
		return people;
	}
	public void setPeople(String people) {
		this.people = people;
	}
	
	
	
}
