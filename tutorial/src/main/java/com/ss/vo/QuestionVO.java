package com.ss.vo;

public class QuestionVO {

	private int question_id;
	private String description;
	private String option_a;
	private String option_b;
	private String option_c;
	private String option_d;
	private String answer;
	private SubjectVO subject;
	
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOption_a() {
		return option_a;
	}
	public void setOption_a(String option_a) {
		this.option_a = option_a;
	}
	public String getOption_b() {
		return option_b;
	}
	public void setOption_b(String option_b) {
		this.option_b = option_b;
	}
	public String getOption_c() {
		return option_c;
	}
	public void setOption_c(String option_c) {
		this.option_c = option_c;
	}
	public String getOption_d() {
		return option_d;
	}
	public void setOption_d(String option_d) {
		this.option_d = option_d;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public SubjectVO getSubject() {
		return subject;
	}
	public void setSubject(SubjectVO subject) {
		this.subject = subject;
	}
	@Override
	public String toString() {
		return "QuestionVO [question_id=" + question_id + ", description="
				+ description + ", option_a=" + option_a + ", option_b="
				+ option_b + ", option_c=" + option_c + ", option_d="
				+ option_d + ", answer=" + answer + ", subject=" + subject
				+ "]";
	}
	
	
}
