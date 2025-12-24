package com.sunbeam.quiz.entity;

public class Question {
	
	public int id;
	public int quiz_id;
	public String text;
	public String a;
	public String b;
	public String c;
	public String d;
	public char correct;
	
	public Question() {
		// TODO Auto-generated constructor stub
	}

	public Question(int id, int quiz_id, String text, String a, String b, String c, String d, char correct) {
		
		this.id = id;
		this.quiz_id = quiz_id;
		this.text = text;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.correct = correct;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public char getCorrect() {
		return correct;
	}

	public void setCorrect(char correct) {
		this.correct = correct;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", quiz_id=" + quiz_id + ", text=" + text + ", a=" + a + ", b=" + b + ", c=" + c
				+ ", d=" + d + ", correct=" + correct + "]";
	}
	
	
}

