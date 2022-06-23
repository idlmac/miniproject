package com.yedam.app.content;

public class NoticeContent {
	private int contentId;
	private String content;
	private int aBoardId;

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

	public int getaBoardId() {
		return aBoardId;
	}

	public void setaBoardId(int aBoardId) {
		this.aBoardId = aBoardId;
	}

	@Override
	public String toString() {
		return "글내용 번호 : " + contentId + ", 내용 : " + content + ", 게시글 id : " + aBoardId;
	}
}
