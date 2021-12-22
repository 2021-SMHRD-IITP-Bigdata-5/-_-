package com.memberDTO;

public class tm_memberDTO {

	private String id;
	private String pw;
	private String name;
	private int age;
	private String gender;
	private String email;
	private String addr;
	private String img;
	private String nickname;
	private int mb_follow;
	private int mb_follower;
	private String admin_yn;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getMb_follow() {
		return mb_follow;
	}

	public void setMb_follow(int mb_follow) {
		this.mb_follow = mb_follow;
	}

	public int getMb_follower() {
		return mb_follower;
	}

	public void setMb_follower(int mb_follower) {
		this.mb_follower = mb_follower;
	}

	public String getAdmin_yn() {
		return admin_yn;
	}

	public void setAdmin_yn(String admin_yn) {
		this.admin_yn = admin_yn;
	}

	public tm_memberDTO(String id, String pw, String name, int age, String gender, String email, String addr,
			String img, String nickname, int mb_follow, int mb_follower, String admin_yn) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.email = email;
		this.addr = addr;
		this.img = img;
		this.nickname = nickname;
		this.mb_follow = mb_follow;
		this.mb_follower = mb_follower;
		this.admin_yn = admin_yn;
	}

	public tm_memberDTO() {
	}

	public tm_memberDTO(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}

}
