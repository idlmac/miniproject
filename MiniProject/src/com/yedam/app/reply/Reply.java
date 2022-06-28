package com.yedam.app.reply;

public class Reply {
	private int rpId;
	private String memberId;
	private String content;
	private String rpDate;
	private int boardId;
	private int rpId2;

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

	public String getRpDate() {
		return rpDate;
	}

	public void setRpDate(String rpDate) {
		this.rpDate = rpDate;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public int getRpId2() {
		return rpId2;
	}

	public void setRpId2(int rpId2) {
		this.rpId2 = rpId2;
	}

	@Override
	public String toString() {
		return "| 댓글번호 : " + rpId + " | 작성자 : " + memberId + " | \t작성일자 : " + rpDate + ", 글번호 : " + boardId
				+ " \n댓글내용 : " + content;
	}
}
