package com.douzone.jblog.vo;

public class CommentVo {
	private long no;
	private String content;
	private String reg_date;
	private long post_no;
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public long getPost_no() {
		return post_no;
	}
	public void setPost_no(long post_no) {
		this.post_no = post_no;
	}
	@Override
	public String toString() {
		return "ContentVo [no=" + no + ", content=" + content + ", reg_date=" + reg_date + ", post_no=" + post_no + "]";
	}
	
	
}
