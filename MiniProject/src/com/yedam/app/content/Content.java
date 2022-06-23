package com.yedam.app.content;

public class Content {
	private int contentId;
	private String content;
	private int boardId;

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	@Override
	public String toString() {
		return "글 번호 : " + contentId + ", 내용 : " + content + ", 게시판 번호 : " + boardId;
	}

}
