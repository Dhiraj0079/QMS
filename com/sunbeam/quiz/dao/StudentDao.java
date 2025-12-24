package com.sunbeam.quiz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sunbeam.quiz.entity.User;
import com.sunbeam.quiz.util.DButil;

public class StudentDao implements AutoCloseable{
	Connection connection=null;
	public static User u=new User();
	
	
	public StudentDao() throws SQLException {
		connection=DButil.getConnection();
	}
	
	public boolean studentRegister(User u) throws SQLException {
		
		String sql="INSERT INTO users (name,email,password_hash) VALUES(?,?,?)";
		try(PreparedStatement stmt=connection.prepareStatement(sql)){
			
			if(studentLogin(u.getEmail(), u.getPassword())) {
				return false;
			}
			stmt.setString(1, u.getName());
			stmt.setString(2, u.getEmail());
			stmt.setString(3, u.getPassword());
			stmt.executeUpdate();
			return true;
			
		}
		
		
	}
	
	public boolean studentLogin(String email,String password) throws SQLException {
		String sql="SELECT*FROM users WHERE email=? AND password_hash=?";
		try(PreparedStatement stmt=connection.prepareStatement(sql)){
			
			stmt.setString(1,email);
			stmt.setString(2, password);
			ResultSet rs=stmt.executeQuery();
			
			if(rs.next()) {
				u.setId(rs.getInt(1));
				return true;
			}
			return false;
		}
		
		
	}
	
	public List<String[]> viewScore() throws SQLException{
		List<String[]> list =new ArrayList<>();
		String sql="SELECT q.quiz_id,q.title,a.final_score FROM quizzes q INNER JOIN quiz_attempts a ON q.quiz_id=a.quiz_id WHERE student_id=? ";
		try(PreparedStatement stmt=connection.prepareStatement(sql)){
			stmt.setInt(1, u.getId());
			ResultSet rs=stmt.executeQuery();
			
			while(rs.next()) {
				String quizInfo[]= {rs.getInt(1)+"",rs.getString(2)+" ",rs.getInt(3)+""};
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
