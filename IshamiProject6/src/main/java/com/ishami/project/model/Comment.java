package com.ishami.project.model;

import java.time.LocalDateTime;

public class Comment {

	private String id;
	private String userId;
	private String content;
	private LocalDateTime regdate;
	
	public Comment(String id, String userId, String content, LocalDateTime regdate) {
		this.id = id;
		this.userId = userId;
		this.content = content;
		this.regdate = regdate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public LocalDateTime getRegdate() {
		return regdate;
	}

	public void setRegdate(LocalDateTime regdate) {
		this.regdate = regdate;
	}
	
	
}
