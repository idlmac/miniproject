package com.yedam.app.reply;

import java.sql.Date;

public class Reply {
	private int rpId;
	private String memberId;
	private String content;
	private Date rpDate;
	private int boardId;

	public int getRpId() {
		return rpId;
	}

	public void setRpId(int rpId) {
		this.rpId = rpId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRpDate() {
		return rpDate;
	}

	public void setRpDate(Date rpDate) {
		this.rpDate = rpDate;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	@Override
	public String toString() {
		return "댓글번호 : " + rpId + ", 작성자 : " + memberId + ", 댓글내용 : " + content + ", 작성일자 : " + rpDate + ", 글번호 : "
				+ boardId;
	}

}