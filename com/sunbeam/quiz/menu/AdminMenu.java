package com.sunbeam.quiz.menu;

import java.util.Scanner;

import com.sunbeam.quiz.service.AdminService;

public class AdminMenu {
	static AdminService ads=new AdminService();
	
	public static int adminMenuOptions(Scanner sc) {
		System.out.println("***************Admin Menu**************");
		System.out.println("0.Logout");
		System.out.println("1.Create Quiz");
		System.out.println("2.List Quiz");
		System.out.println("3.Delete Quiz");
		
		System.out.println();
		System.out.println("Enter choice:");
		return sc.nextInt();
		
	}
	
	public static void adminMenu(Scanner sc) {
		int choice;
		while((choice=adminMenuOptions(sc))!=0) {
			switch(choice) {
			case 1:
				System.out.println();
				System.out.println("Selected Create Quiz");
				ads.createQuiz(sc);
				break;
			case 2:
				System.out.println();
				System.out.println("Selected List Quiz");
				ads.listQuiz();
				break;
			case 3:
				System.out.println();
				System.out.println("Selected Delete quiz");
				ads.deleteQuiz(sc);
				break;
			default:
				System.out.println();
				System.out.println("Wrong Choice");
				break;
				
			}
		}
	}
}

