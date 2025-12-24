package com.sunbeam.quiz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sunbeam.quiz.util.DButil;
import com.sunbeam.quiz.util.DButil;

public class AdminDao implements AutoCloseable {
	Connection connection=null;
	 
	public AdminDao() throws SQLException {
		connection=DButil.getConnection();
	}
	
	public boolean adminLogin(String email, String password) throws SQLException {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM users WHERE email=? AND password_hash=?";
		try(PreparedStatement stmt=connection.prepareStatement(sql)){
			stmt.setString(1, email);
			stmt.setString(2, password);
			ResultSet rs=stmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}
			return false;
		}
		
	}
	
	@Override
	public void close() throws SQLException {
		// TODO Auto-generated method stub
		if(connection!=null)
			connection.close();
		
	}


	

}
