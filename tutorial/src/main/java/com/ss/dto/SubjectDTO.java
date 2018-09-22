package com.ss.dto;

public class SubjectDTO {
	
	private int sub_id;
	private String sub_name;
	public int getSub_id() {
		return sub_id;
	}
	public void setSub_id(int sub_id) {
		this.sub_id = sub_id;
	}
	public String getSub_name() {
		return sub_name;
	}
	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}
	@Override
	public String toString() {
		return "SubjectDTO [sub_id=" + sub_id + ", sub_name=" + sub_name + "]";
	}
}
