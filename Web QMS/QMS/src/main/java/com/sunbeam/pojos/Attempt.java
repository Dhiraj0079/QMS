package com.sunbeam.pojos;

import java.time.LocalDateTime;

public class Attempt {
	int id;
	int quiz_id;
	int user_id;
	int final_Score;
	int total_question;
	LocalDateTime created_at;
	
	public Attempt() {
				// TODO Auto-generated constructor stub
	}

	public Attempt(int id, int quiz_id, int user_id, int final_Score, int total_question, LocalDateTime created_at) {
		
		this.id = id;
		this.quiz_id = quiz_id;
		this.user_id = user_id;
		this.final_Score = final_Score;
		this.total_question = total_question;
		this.created_at = created_at;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuiz_id() {
		return quiz_id;
	}

	public void setQuiz_id(int quiz_id) {
		this.quiz_id = quiz_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getFinal_Score() {
		return final_Score;
	}

	public void setFinal_Score(int final_Score) {
		this.final_Score = final_Score;
	}

	public int getTotal_question() {
		return total_question;
	}

	public void setTotal_question(int total_question) {
		this.total_question = total_question;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	@Override
	public String toString() {
		return "Attempts [id=" + id + ", quiz_id=" + quiz_id + ", user_id=" + user_id + ", final_Score=" + final_Score
				+ ", total_question=" + total_question + ", created_at=" + created_at + "]";
	}
	
	
}
