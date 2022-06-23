package com.yedam.app.board;

import java.sql.Date;

public class FreeBoard {
	private int fBoardId;
	private String fBoardTitle;
	private String userId;
	private Date boardDate;

	public int getfBoardId() {
		return fBoardId;
	}

	public void setfBoardId(int fBoardId) {
		this.fBoardId = fBoardId;
	}

	public String getfBoardTitle() {
		return fBoardTitle;
	}

	public void setfBoardTitle(String fBoardTitle) {
		this.fBoardTitle = fBoardTitle;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}

	@Override
	public String toString() {
		return "게시글 번호 : " + fBoardId + ", 제목 : " + fBoardTitle + ", 작성자 : " + userId + "작성일 : " + boardDate;
	}

}
