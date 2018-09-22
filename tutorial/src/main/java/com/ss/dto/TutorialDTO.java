package com.ss.dto;

public class TutorialDTO {

	private int tutorial_id;
	private int sub_id;
	private String heading;
	private String description;
	
	public int getTutorial_id() {
		return tutorial_id;
	}
	public void setTutorial_id(int tutorial_id) {
		this.tutorial_id = tutorial_id;
	}
	public int getSub_id() {
		return sub_id;
	}
	public void setSub_id(int sub_id) {
		this.sub_id = sub_id;
	}
	public String getHeading() {
		return heading;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "TutorialDTO [tutorial_id=" + tutorial_id + ", sub_id=" + sub_id
				+ ", heading=" + heading + ", description=" + description + "]";
	}
	
	
	
}
