package com.yedam.app.board;

import java.sql.Date;

public class Board {
	private int boardId;
	private String boardTitle;
	private String memberId;
	private Date boardDate;

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Date getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}

	@Override
	public String toString() {
		return "글 번호 : " + boardId + ", 제목 : " + boardTitle + ", 작성자 : " + memberId + ", 작성일 : " + boardDate;
	}
}
