package com.yedam.app.members;

public class Member {
	private String memberId;
	private String memberPw;
	// role 번호 의미 = 0 : 일반 사용자, 1 : 관리자
	private int role;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	@Override
	public String toString() {
		String info = "";

		if (role == 0) {
			info = "일반 사용자 : " + memberId;
		} else {
			info = "관리자 : " + memberId;
		}
		return info;
	}
}
