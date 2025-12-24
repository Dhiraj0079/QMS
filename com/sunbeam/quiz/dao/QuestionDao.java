package com.sunbeam.quiz.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.sunbeam.quiz.entity.Question;
import com.sunbeam.quiz.util.DButil;
import com.sunbeam.quiz.util.QuestionFileParser;

public class QuestionDao implements AutoCloseable {
	Connection connection=null;
	
	public QuestionDao() throws SQLException {
		connection=DButil.getConnection();
	}
	
	public void addQuestion(int quiz_id,Scanner sc) throws Exception {
		System.out.println("Enter The Path: ");
		String path=sc.next();
		
		File file= new File(path);
		
		List<Question> queList= QuestionFileParser.parse(file);
		
		String sql="INSERT INTO questions (quiz_id,question_text,option_a,option_b,option_c,option_d,correct_option) VALUES(?,?,?,?,?,?,?)";
		for(int i=0;i<queList.size();i++) {
			
		try(PreparedStatement stmt=connection.prepareStatement(sql)){
			
			stmt.setInt(1, quiz_id);
			stmt.setString(2,queList.get(i).getText());
			stmt.setString(3,queList.get(i).getA());
			stmt.setString(4,queList.get(i).getB());
			stmt.setString(5, queList.get(i).getC());
			stmt.setString(6,queList.get(i).getD());
			stmt.setString(7, String.valueOf(queList.get(i).getCorrect()));
			stmt.executeUpdate();
			
			System.out.println("Questions Successfully Added");
		}
		}
	}
	
	
	@Override
	public void close() throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
