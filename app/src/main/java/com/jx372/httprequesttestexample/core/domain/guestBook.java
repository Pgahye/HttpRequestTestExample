package com.jx372.httprequesttestexample.core.domain;

public class guestBook {
	
	
	private Long no;
	private String name;
	private String pwd;
	private String message;
	private String date;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "guestBook [no=" + no + ", name=" + name + ", pwd=" + pwd + ", message=" + message + ", date=" + date
				+ "]";
	}
	
	
	
	

}
