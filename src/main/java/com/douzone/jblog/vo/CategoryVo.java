package com.douzone.jblog.vo;

public class CategoryVo {
	
	private long no;
	private String name;
	private String description;
	private String reg_date;
	private long user_no;
	private long post_value;
	public long getPost_value() {
		return post_value;
	}
	public void setPost_value(long post_value) {
		this.post_value = post_value;
	}
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public long getUser_no() {
		return user_no;
	}
	public void setUser_no(long user_no) {
		this.user_no = user_no;
	}
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", name=" + name + ", description=" + description + ", reg_date=" + reg_date
				+ ", user_no=" + user_no + ", post_value=" + post_value + "]";
	}
	

}
