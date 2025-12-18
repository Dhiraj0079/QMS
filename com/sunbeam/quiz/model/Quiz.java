package com.sunbeam.quiz.model;

public class Quiz {
	public int id;
	public String title;
	public int creator_id;
	public Quiz(int id, String title, int creator_id) {
		super();
		this.id = id;
		this.title = title;
		this.creator_id = creator_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCreator_id() {
		return creator_id;
	}
	public void setCreator_id(int creator_id) {
		this.creator_id = creator_id;
	}
	@Override
	public String toString() {
		return "Quiz [id=" + id + ", title=" + title + ", creator_id=" + creator_id + "]";
	}
	
}
