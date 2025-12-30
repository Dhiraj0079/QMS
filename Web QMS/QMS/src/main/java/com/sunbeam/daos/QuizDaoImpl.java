package com.sunbeam.daos;

import java.sql.*;
import java.util.*;
import com.sunbeam.pojos.*;

public class QuizDaoImpl extends Dao implements QuizDao {
    private PreparedStatement stmtInsertQuiz, stmtInsertQuest, stmtFindAll, stmtDelete;
    private PreparedStatement stmtSaveAttempt, stmtUserScores, stmtAllResults;

    public QuizDaoImpl() throws Exception {
        super(); // Initializes connection via DbUtil
        
        // Admin Queries
        stmtInsertQuiz = con.prepareStatement("INSERT INTO quizzes (title, creator_id) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
        stmtInsertQuest = con.prepareStatement("INSERT INTO questions (quiz_id, question_text, option_a, option_b, option_c, option_d, correct_option) VALUES (?, ?, ?, ?, ?, ?, ?)");
        stmtFindAll = con.prepareStatement("SELECT * FROM quizzes");
        stmtDelete = con.prepareStatement("DELETE FROM quizzes WHERE id = ?");
        stmtAllResults = con.prepareStatement("SELECT * FROM quiz_attempts ORDER BY created_at DESC");

        // Student Queries
        stmtSaveAttempt = con.prepareStatement("INSERT INTO quiz_attempts (quiz_id, user_id, final_score, total_questions, created_at) VALUES (?, ?, ?, ?, ?)");
        stmtUserScores = con.prepareStatement("SELECT * FROM quiz_attempts WHERE user_id = ? ORDER BY created_at DESC");
    }

    @Override
    public void saveQuizWithQuestions(String title, int creatorId, List<Question> questions) throws Exception {
        try {
            con.setAutoCommit(false); // Start Transaction

            // 1. Insert Quiz Metadata
            stmtInsertQuiz.setString(1, title);
            stmtInsertQuiz.setInt(2, creatorId);
            stmtInsertQuiz.executeUpdate();

            // 2. Retrieve the new Quiz ID
            ResultSet rs = stmtInsertQuiz.getGeneratedKeys();
            if (rs.next()) {
                int quizId = rs.getInt(1);

                // 3. Batch Creation: Insert questions from QuestionFileParser
                for (Question q : questions) {
                    stmtInsertQuest.setInt(1, quizId);
                    stmtInsertQuest.setString(2, q.getText());
                    stmtInsertQuest.setString(3, q.getA());
                    stmtInsertQuest.setString(4, q.getB());
                    stmtInsertQuest.setString(5, q.getC());
                    stmtInsertQuest.setString(6, q.getD());
                    stmtInsertQuest.setString(7, String.valueOf(q.getCorrect()));
                    stmtInsertQuest.addBatch(); 
                }
                stmtInsertQuest.executeBatch(); // Execute all inserts at once
            }
            con.commit(); // Commit transaction
        } catch (Exception e) {
            con.rollback(); // Undo on error
            throw e;
        } finally {
            con.setAutoCommit(true);
        }
    }

    @Override
    public List<Quiz> findAll() throws Exception {
        List<Quiz> list = new ArrayList<>();
        try (ResultSet rs = stmtFindAll.executeQuery()) {
            while (rs.next()) {
                list.add(new Quiz(rs.getInt("id"), rs.getString("title"), rs.getInt("creator_id")));
            }
        }
        return list;
    }

    @Override
    public int saveAttempt(Attempt a) throws Exception {
        stmtSaveAttempt.setInt(1, a.getQuiz_id());
        stmtSaveAttempt.setInt(2, a.getUser_id());
        stmtSaveAttempt.setInt(3, a.getFinal_Score());
        stmtSaveAttempt.setInt(4, a.getTotal_question());
        stmtSaveAttempt.setTimestamp(5, Timestamp.valueOf(a.getCreated_at()));
        return stmtSaveAttempt.executeUpdate();
    }

    @Override
    public List<Attempt> findUserScores(int userId) throws Exception {
        List<Attempt> list = new ArrayList<>();
        stmtUserScores.setInt(1, userId);
        try (ResultSet rs = stmtUserScores.executeQuery()) {
            while (rs.next()) {
                list.add(new Attempt(rs.getInt("id"), rs.getInt("quiz_id"), rs.getInt("user_id"), 
                    rs.getInt("final_score"), rs.getInt("total_questions"), 
                    rs.getTimestamp("created_at").toLocalDateTime()));
            }
        }
        return list;
    }

    @Override
    public List<Attempt> getAllResults() throws Exception {
        List<Attempt> list = new ArrayList<>();
        try (ResultSet rs = stmtAllResults.executeQuery()) {
            while (rs.next()) {
                list.add(new Attempt(rs.getInt("id"), rs.getInt("quiz_id"), rs.getInt("user_id"), 
                    rs.getInt("final_score"), rs.getInt("total_questions"), 
                    rs.getTimestamp("created_at").toLocalDateTime()));
            }
        }
        return list;
    }

    @Override
    public int deleteById(int id) throws Exception {
        stmtDelete.setInt(1, id);
        return stmtDelete.executeUpdate();
    }

    @Override
    public void close() throws Exception {
        stmtInsertQuiz.close(); stmtInsertQuest.close(); stmtFindAll.close();
        stmtDelete.close(); stmtSaveAttempt.close();
        stmtUserScores.close(); stmtAllResults.close();
        super.close();
    }
}