package com.yedam.app.board;

import java.sql.Date;

public class NoticeBoard {
	private int nboardId;
	private String nBoardTitle;
	private String userId;
	private Date boardDate;

	public int getNboardId() {
		return nboardId;
	}

	public void setNboardId(int nboardId) {
		this.nboardId = nboardId;
	}

	public String getnBoardTitle() {
		return nBoardTitle;
	}

	public void setnBoardTitle(String nBoardTitle) {
		this.nBoardTitle = nBoardTitle;
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
		return "게시글 번호 : " + nboardId + ", 제목 : " + nBoardTitle + ", 작성자 : " + userId + "작성일 : " + boardDate;
	}

}
