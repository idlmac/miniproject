package com.yedam.app.board;

import java.sql.Date;

public class AnonyBoard {
	private int aBoardId;
	private String aBoardTitle;
	private String userId;
	private Date aboardDate;

	public int getaBoardId() {
		return aBoardId;
	}

	public void setaBoardId(int aBoardId) {
		this.aBoardId = aBoardId;
	}

	public String getaBoardTitle() {
		return aBoardTitle;
	}

	public void setaBoardTitle(String aBoardTitle) {
		this.aBoardTitle = aBoardTitle;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getAboardDate() {
		return aboardDate;
	}

	public void setAboardDate(Date aboardDate) {
		this.aboardDate = aboardDate;
	}

	@Override
	public String toString() {
		return "게시글 번호 : " + aBoardId + ", 제목 : " + aBoardTitle + ", 작성자 : " + userId + "작성일 : " + aboardDate;
	}
}
