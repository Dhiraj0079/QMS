package com.sunbeam.quiz.menu;

import java.util.Scanner;

import com.sunbeam.quiz.service.StudentService;

public class StudentMenu {
	
	StudentService sds=new StudentService();
	
	public int studentMenuOptions(Scanner sc) {
		
		System.out.println("0.Logout");
		System.out.println("1.View Quiz");
		System.out.println("2.Take Quiz");
		System.out.println("3.View Score");
		System.out.println();
		System.out.println("Enter The Choice:");
		return sc.nextInt();
	}
	
	public void studentMenu(Scanner sc) {
		int choice;
		while((choice=studentMenuOptions(sc))!=0) {
			switch(choice) {
			case 1:
				System.out.println("Selected View Quiz");
				sds.listQuiz();
				System.out.println();
				break;
			case 2:
				System.out.println("Selected Take Quiz");
				sds.takeQuiz(sc);
				System.out.println();
				break;
			case 3:
				System.out.println("Selected View Quiz");
				sds.showScore();
				System.out.println();
				break;
			default:
				System.out.println("Wrong Choice");
				System.out.println();
				break;
			
			}
		}
	}

}
