package com.sunbeam.quiz.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;
import com.sunbeam.quiz.dao.QuizDao;
import com.sunbeam.quiz.dao.StudentDao;
import com.sunbeam.quiz.entity.Question;
import com.sunbeam.quiz.entity.User;
import com.sunbeam.quiz.menu.StudentMenu;

public class StudentService {
	
	static StudentMenu sdm=new StudentMenu();
	
	public static User u=new User();
	
	public void studentRegister(Scanner sc) {
		
		System.out.println("Enter Student name:");
		u.setName(sc.next());
		System.out.println("Enter the Student email:");
		u.setEmail(sc.next());
		System.out.println("Enter The Password:");
		u.setPassword(sc.next());;
		
		try(StudentDao sd=new StudentDao()){
			if(sd.studentRegister(u)) {
				System.out.println();
				System.out.println("Student Register Successfull");
				System.out.println(u);
				System.out.println();
				System.out.println("***********Entering studentLogin************");
				studentLogin(sc);
				System.out.println();
			}
			else {
				System.out.println("Registration failed or User already exist");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void studentLogin(Scanner sc) {
		System.out.println("Enter Student Email: ");
		String email=sc.next();
		System.out.println("Enter Password:");
		String password=sc.next();
		
		try(StudentDao sd=new StudentDao()){
			if(sd.studentLogin(email, password)) {
				
				System.out.println("Student Login SuccessFull ");
				System.out.println();
				sdm.studentMenu(sc);
			}
			else {
				System.out.println("Student Login Failed");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void listQuiz() {
		try(QuizDao qd=new QuizDao()){
			qd.listQuiz();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void takeQuiz(Scanner sc) {
		System.out.println("Enter Quiz ID:");
		int quiz_id=sc.nextInt();
		
		try(QuizDao qd=new QuizDao()){
			List<Question> list=qd.takeQuiz(quiz_id);
			
			if(list.isEmpty()==true) {
				System.out.println();
				System.out.println("No Quiz available");
				System.out.println();
			}
			else {
				try(StudentDao sd=new StudentDao()){
					List<String[]> list2=sd.viewScore();
					if(!list2.isEmpty() ) {
						System.out.println("You Have Already Solved Quiz");
						return;
					}
					int score=0,i=0;
					for(Question q:list) {
						
						
						i++;
						System.out.println(i+"."+q.text);
						System.out.println("A."+q.a);
						System.out.println("B."+q.b);
						System.out.println("C."+q.c);
						System.out.println("D."+q.d);
						System.out.println();
						System.out.println("Enter The Correct Options:");
						char ans=sc.next().toUpperCase().charAt(0);
						
						
						if(ans==q.correct) {
							score+=1;
						}
						System.out.println();
					}
					System.out.println("Quiz Completed");
					System.out.println("You Scored "+score+"Out of "+i);
					
					qd.attemptQuiz(quiz_id,StudentDao.u.getId() , score, i);
				}
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void showScore() {
		try(StudentDao sd=new StudentDao()){
			List<String[]> list=sd.viewScore();
			if(list.isEmpty()) {
				System.out.println("You Have not Solved Any Quiz");
				
			}else {
				for(String[] s:list) {
					System.out.println("Quiz ID:"+s[0]);
					System.out.println("Quiz Name:"+s[1]);
					System.out.println("Your Score:"+s[2]);
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
