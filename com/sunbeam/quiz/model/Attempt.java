package com.sunbeam.quiz.model;
import java.time.*;
import java.util.*;

public class Attempt {
	Scanner sc= new Scanner(System.in);
	public int id;
	public  int quizId;
	public int studentId;
	public int  finalscore;
	public int totalquestion;
	public  LocalDateTime attemptTime;
	
	
	public Attempt(int id, int quizId, int studentId, int finalscore, int totalquestion, LocalDateTime attemptTime) {
		super();
		this.id = id;
		this.quizId = quizId;
		this.studentId = studentId;
		this.finalscore = finalscore;
		this.totalquestion = totalquestion;
		this.attemptTime = attemptTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuizId() {
		return quizId;
	}
	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getFinalscore() {
		return finalscore;
	}
	public void setFinalscore(int finalscore) {
		this.finalscore = finalscore;
	}
	public int getTotalquestion() {
		return totalquestion;
	}
	public void setTotalquestion(int totalquestion) {
		this.totalquestion = totalquestion;
	}
	public LocalDateTime getAttemptTime() {
		return attemptTime;
	}
	public void setAttemptTime(LocalDateTime attemptTime) {
		this.attemptTime = attemptTime;
	}
	public void acceptA(Scanner sc) {
		System.out.println("attempt id");
		 id=sc.nextInt();
		 System.out.println("quiz Id");
		 quizId=sc.nextInt();
		 System.out.println("student id");
		 studentId=sc.nextInt();
		 System.out.println(" final score");
		 finalscore=sc.nextInt();
		 System.out.println("total question");
		 totalquestion=sc.nextInt();
		
	}
	@Override
	public
	String toString() {
		return "[id=" + id + ", Quizid=" + quizId + ", studentId=" +studentId  + ", finalscore=" + finalscore + ",totalquestion="+totalquestion+"]";
	}	
	
 
}
