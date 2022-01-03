package com.commentDTO;

public class commentDTO {

	private int comm_seq;
	private int tb_seq;
	private String comm_content;
	private String reg_date;
	private int comm_likes;
	private String mb_id;

	public int getComm_seq() {
		return comm_seq;
	}

	public void setComm_seq(int comm_seq) {
		this.comm_seq = comm_seq;
	}

	public int getTb_seq() {
		return tb_seq;
	}

	public void setTb_seq(int tb_seq) {
		this.tb_seq = tb_seq;
	}

	public String getComm_content() {
		return comm_content;
	}

	public void setComm_content(String comm_content) {
		this.comm_content = comm_content;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public int getComm_likes() {
		return comm_likes;
	}

	public void setComm_likes(int comm_likes) {
		this.comm_likes = comm_likes;
	}

	public String getMb_id() {
		return mb_id;
	}

	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}

	public commentDTO(String comm_content, String mb_id) {
		this.comm_content = comm_content;
		this.mb_id = mb_id;
	}

	public commentDTO(int comm_seq, int tb_seq, String comm_content, String reg_date, int comm_likes, String mb_id) {
		this.comm_seq = comm_seq;
		this.tb_seq = tb_seq;
		this.comm_content = comm_content;
		this.reg_date = reg_date;
		this.comm_likes = comm_likes;
		this.mb_id = mb_id;
	}

}