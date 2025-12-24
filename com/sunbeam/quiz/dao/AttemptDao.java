package com.sunbeam.quiz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sunbeam.quiz.entity.Attempt;
import com.sunbeam.quiz.util.DButil;

public class AttemptDao implements AutoCloseable{
	private static Connection connection = null;

	public AttemptDao() throws SQLException {
		connection = DButil.getConnection();
	}
	
	public boolean hasAttemptedBefore(int studentId,int quizId) throws SQLException {
		String sql = "SELECT * FROM quiz_attempts WHERE student_id = ? AND quiz_id = ?";
		PreparedStatement selectStatement = connection.prepareStatement(sql);
		selectStatement.setInt(1, studentId);
		selectStatement.setInt(2, quizId);
		ResultSet rs = selectStatement.executeQuery();
		return rs.next();
	}
	
	public void recordAttempt(int quiz_id,int student_id,int finalScore,int numberOfQuestins) throws SQLException {
		String sql = "INSERT INTO quiz_attempts (quiz_id,student_id,final_score,total_questions) VALUES(?,?,?,?)";
		PreparedStatement insertStatement = connection.prepareStatement(sql);
		insertStatement.setInt(1, quiz_id);
		insertStatement.setInt(2, student_id);
		insertStatement.setInt(3, finalScore);
		insertStatement.setInt(4, numberOfQuestins);
		insertStatement.executeUpdate();
	}
	
	public List<Attempt> getAttemptList(int student_id) throws SQLException{
		List<Attempt> attemptList = new ArrayList<>();
		String sql = "SELECT * FROM quiz_attempts WHERE student_id = ?";
		PreparedStatement selectStatement = connection.prepareStatement(sql);
		selectStatement.setInt(1, student_id);
		ResultSet rs =  selectStatement.executeQuery();
		while(rs.next()) {
			Attempt attempt = new Attempt();
			attempt.setattemptId(rs.getInt("attempt_id"));
			attempt.setQuizId(rs.getInt("quiz_id"));
			attempt.setFinalscore(rs.getInt("final_score"));
			attempt.setStudentId(rs.getInt("student_id"));
			attempt.setTotalquestion(rs.getInt("total_questions"));
			//attempt.setAttemptTime(rs.getTimestamp("created_at"));4
			attemptList.add(attempt);
		}
		return attemptList;
	}
	
	public void displayResult() throws SQLException {
		String sql = "SELECT u.name, q.final_score, q.total_questions, q.quiz_id FROM quiz_attempts q, users u WHERE student_id = user_id AND role = 'STUDENT' ORDER BY u.name";
		PreparedStatement selectStatement = connection.prepareStatement(sql);
		ResultSet rs = selectStatement.executeQuery();
		int i = 1;
		while(rs.next()) {
			System.out.println(i + ") " + "Name: " + rs.getString("name") + " --> " + "Quiz id: " + rs.getInt("quiz_id") + "Score " + rs.getInt("final_score") + "out of " + rs.getInt("total_questions"));
			i++;
		}
		
	}
	
	@Override
	public void close() throws SQLException {
		if (connection != null) {
			connection.close();
			connection = null;
		}
	}
}

