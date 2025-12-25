package com.sunbeam.quiz.menu;

import java.util.Scanner;

import com.sunbeam.quiz.service.AdminService;
import com.sunbeam.quiz.service.StudentService;

public class MainMenu {
	static AdminService ads=new AdminService();
	static StudentService sds=new StudentService();
	
	
	public static int mainMenuOptions(Scanner sc) {
		System.out.println("0.EXIT");
		System.out.println("1.Admin Login");
		System.out.println("2.Student Register");
		System.out.println("3.Student Login");
		return sc.nextInt();
		
	}
	
	public static void mainMenu(Scanner sc) {
		int choice;
		while((choice=mainMenuOptions(sc))!=0) {
			switch(choice) {
			case 1:
				System.out.println("Admin Login msg");
				ads.adminLogin(sc);
				break;
			case 2:
				System.out.println("Student Reg Msg");
				sds.studentRegister(sc);
				break;
			case 3:
				System.out.println("Student Login Msg");
				sds.studentLogin(sc);
				break;
			default:
				System.out.println("Wrong Choice");
				break;
			}
		}
	}

}

