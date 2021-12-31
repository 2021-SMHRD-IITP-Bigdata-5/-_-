package com.snsDTO;

public class follower {

	private boolean check;
	private int follow;

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public int getFollow() {
		return follow;
	}

	public void setFollow(int follow) {
		this.follow = follow;
	}

	public follower(boolean check, int follow) {
		super();
		this.check = check;
		this.follow = follow;
	}

}
