package com.myTravelDTO;

import java.sql.Date;

public class tm_myTravelDTO {

	private int travel_seq;
	private int map_seq;
	private String travel_memo;
	private String travel_character;
	private String travel_file1;
	private String travel_file2;
	private String reg_date;
	private String mb_id;
	private int travel_order;

	public int getTravel_seq() {
		return travel_seq;
	}

	public void setTravel_seq(int travel_seq) {
		this.travel_seq = travel_seq;
	}

	public int getMap_seq() {
		return map_seq;
	}

	public void setMap_seq(int map_seq) {
		this.map_seq = map_seq;
	}

	public String getTravel_memo() {
		return travel_memo;
	}

	public void setTravel_memo(String travel_memo) {
		this.travel_memo = travel_memo;
	}

	public String getTravel_character() {
		return travel_character;
	}

	public void setTravel_character(String travel_character) {
		this.travel_character = travel_character;
	}

	public String getTravel_file1() {
		return travel_file1;
	}

	public void setTravel_file1(String travel_file1) {
		this.travel_file1 = travel_file1;
	}

	public String getTravel_file2() {
		return travel_file2;
	}

	public void setTravel_file2(String travel_file2) {
		this.travel_file2 = travel_file2;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public String getMb_id() {
		return mb_id;
	}

	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}

	public int getTravel_order() {
		return travel_order;
	}

	public void setTravel_order(int travel_order) {
		this.travel_order = travel_order;
	}

	public tm_myTravelDTO(int travel_seq, int map_seq, String travel_memo, String travel_character, String travel_file1,
			String travel_file2, String reg_date, String mb_id, int travel_order) {
		this.travel_seq = travel_seq;
		this.map_seq = map_seq;
		this.travel_memo = travel_memo;
		this.travel_character = travel_character;
		this.travel_file1 = travel_file1;
		this.travel_file2 = travel_file2;
		this.reg_date = reg_date;
		this.mb_id = mb_id;
		this.travel_order = travel_order;
	}

	public tm_myTravelDTO() {

	}

	public tm_myTravelDTO(String travel_memo, String travel_character, String travel_file1, String travel_file2,
			String mb_id, int travel_order) {
		this.travel_memo = travel_memo;
		this.travel_character = travel_character;
		this.travel_file1 = travel_file1;
		this.travel_file2 = travel_file2;
		this.mb_id = mb_id;
		this.travel_order = travel_order;
	}

	public tm_myTravelDTO(String travel_memo, String travel_character, String mb_id, int travel_order) {
		this.travel_memo = travel_memo;
		this.travel_character = travel_character;
		this.mb_id = mb_id;
		this.travel_order = travel_order;
	}

	public tm_myTravelDTO(int map_seq, String travel_memo, String travel_character, String mb_id, int travel_order) {
		this.map_seq = map_seq;
		this.travel_memo = travel_memo;
		this.travel_character = travel_character;
		this.mb_id = mb_id;
		this.travel_order = travel_order;
	}

}
