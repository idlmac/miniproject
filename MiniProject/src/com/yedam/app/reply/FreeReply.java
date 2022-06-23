package com.yedam.app.reply;

import java.sql.Date;

public class FreeReply {
	private int rpId;
	private String userId;
	private String content;
	private Date rpDate;
	private int aBoardId;

	public int getRpId() {
		return rpId;
	}

	public void setRpId(int rpId) {
		this.rpId = rpId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public int getaBoardId() {
		return aBoardId;
	}

	public void setaBoardId(int aBoardId) {
		this.aBoardId = aBoardId;
	}
}
