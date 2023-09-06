package com.poscodx.guestbook01.vo;

import java.time.LocalDateTime;

public class GuestBookVo {
	private long no;
	private String name;
	private String password;
	private String contents;
	private LocalDateTime regDate;
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public LocalDateTime getRegDate() {
		return regDate; 
	}
	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "EmaillistVo [no=" + no + ", name=" + name + ", password=" + password + ", contents=" + contents
				+ ", regDate=" + regDate + "]";
	}
	
	
}