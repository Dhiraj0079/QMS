package com.sunbeam.quiz.service;

import java.sql.SQLException;
import java.util.Scanner;

import com.sunbeam.quiz.dao.AdminDao;
import com.sunbeam.quiz.dao.QuestionDao;
import com.sunbeam.quiz.dao.QuizDao;
import com.sunbeam.quiz.menu.AdminMenu;

public class AdminService {
	public AdminMenu am=new AdminMenu();
	
	public void adminLogin(Scanner sc) {
		System.out.println("Enter Email:");
		String email=sc.next();
		System.out.println("Enter Password:");
		String password=sc.next();
		
		try(AdminDao ad=new AdminDao()){
			if(ad.adminLogin(email,password)) {
				System.out.println("Login Successful");
				System.out.println();
				am.adminMenu(sc);
			}
			else {
				System.out.println("Login Failed");
				System.out.println();
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void createQuiz(Scanner sc) {
		System.out.println("Enter title:");
		String title=sc.next();
		System.out.println("Enter creator_id");
		int creator_id=sc.nextInt();
		System.out.println("Enter Quiz id:");
		int quiz_id=sc.nextInt();
		
		try(QuizDao aq=new QuizDao()){
			if(aq.addQuiz(title,creator_id)) {
				QuestionDao qd=new QuestionDao();
				System.out.println();
				System.out.println("Quiz id and Title added");
				qd.addQuestion(quiz_id, sc);
			}
			else {
				System.out.println("creator id is incorrect");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void listQuiz() {
		try(QuizDao aq=new QuizDao()){
			aq.listQuiz();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteQuiz(Scanner sc) {
		System.out.println("Enter Quiz ID: ");
		int quiz_id=sc.nextInt();
		try(QuizDao qd=new QuizDao() ){
			
			qd.deleteQuiz(quiz_id);
			
			System.out.println("Quiz Deleted Successfully");
			System.out.println();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}

