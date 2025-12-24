package com.sunbeam.quiz.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sunbeam.quiz.entity.Question;
import com.sunbeam.quiz.entity.Quiz;
import com.sunbeam.quiz.util.DButil;
import com.sunbeam.quiz.util.QuestionFileParser;

public class QuizDao implements AutoCloseable {
	
	Connection connection=null;
	
	public QuizDao() throws SQLException {
		connection=DButil.getConnection();
	}
	
	public boolean addQuiz(String title,int creator_id) throws SQLException {
		String sql="INSERT INTO quizzes(title,creator_id) VALUES (?,?)";
		try(PreparedStatement stmt=connection.prepareStatement(sql)){
			stmt.setString(1, title);
			stmt.setInt(2, creator_id);
			stmt.executeUpdate();
			return true;
			
		}
	}
	
	public void listQuiz() throws SQLException {
		Quiz q=new Quiz();
		String sql="SELECT*FROM quizzes ";
		try(PreparedStatement stmt=connection.prepareStatement(sql)){
			ResultSet rs=stmt.executeQuery();
			
			while(rs.next()) {
				q.setId(rs.getInt(1));
				q.setTitle(rs.getString(2));
				q.setCreator_id(rs.getInt(3));
				System.out.println(q);
			}
		}
	}
	public void deleteQuiz(int quiz_id) throws SQLException {
		String sql="DELETE FROM quizzes WHERE quiz_id=?";
		try(PreparedStatement stmt=connection.prepareStatement(sql)){
			stmt.setInt(1, quiz_id);
			stmt.executeUpdate();
			
			
		}
	}
	
	public List<Question> takeQuiz(int quiz_id) throws SQLException {
		List<Question> list=new ArrayList<>();
		String sql="SELECT quiz_id ,question_text,option_a,option_b,option_c,option_d,correct_option FROM questions WHERE quiz_id=? ";
		try(PreparedStatement stmt=connection.prepareStatement(sql)){
			stmt.setInt(1, quiz_id);
			ResultSet rs=stmt.executeQuery();
			
			while(rs.next()) {
				Question qz=new Question();
				qz.setQuiz_id(quiz_id);
				qz.setText(rs.getString(2));
				qz.setA(rs.getString(3));
				qz.setB(rs.getString(4));
				qz.setC(rs.getString(5));
				qz.setD(rs.getString(6));
				qz.setCorrect(rs.getString(7).toUpperCase().charAt(0));
				list.add(qz);
			}
		}
		return list;
	}
	
	public void attemptQuiz(int quiz_id,int user_id,int final_score,int total_questions) throws SQLException {
		String sql="INSERT INTO quiz_attempts (quiz_id,student_id,final_score,total_questions) VALUES (?,?,?,?)";
		try(PreparedStatement stmt=connection.prepareStatement(sql)){
			stmt.setInt(1, quiz_id);
			stmt.setInt(2, user_id);
			stmt.setInt(3, final_score);
			stmt.setInt(4, total_questions);
			stmt.executeUpdate();
		}
	}
	
	public List<String[]> viewScore(int id ) throws SQLException {
		List<String []> list = new ArrayList<>();
		String sql = "select student_id,final_score, name from users u inner join quiz_attempts at on u.user_id=at.student_id where quiz_id=?";
		try(PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String quizInfo []= {rs.getInt(1)+"",rs.getInt(2)+"",rs.getString(3)};
				list.add(quizInfo);
			}
		}
		return list;
		
	}
	
	
	
	@Override
	public void close() throws SQLException {
		// TODO Auto-generated method stub
		if(connection!=null) {
			connection.close();
			connection=null;
		}
	}

	
}
