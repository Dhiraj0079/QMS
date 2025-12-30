package com.sunbeam.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.sunbeam.pojos.Question;

public class QuestionDaoImpl extends Dao implements QuestionDao {
    private PreparedStatement stmtFindByQuizId;

    public QuestionDaoImpl() throws Exception {
        super(); // Initializes connection via DbUtil
        // SQL to fetch all questions for a specific quiz
        stmtFindByQuizId = con.prepareStatement("SELECT * FROM questions WHERE quiz_id = ?");
    }

    @Override
    public List<Question> findByQuizId(int quizId) throws Exception {
        List<Question> list = new ArrayList<>();
        stmtFindByQuizId.setInt(1, quizId);
        
        try (ResultSet rs = stmtFindByQuizId.executeQuery()) {
            while (rs.next()) {
                // Mapping DB columns to your Question POJO
                Question q = new Question();
                q.setId(rs.getInt("id"));
                q.setQuiz_id(rs.getInt("quiz_id"));
                q.setText(rs.getString("question_text"));
                q.setA(rs.getString("option_a"));
                q.setB(rs.getString("option_b"));
                q.setC(rs.getString("option_c"));
                q.setD(rs.getString("option_d"));
                // Converts DB string (e.g., "A") to char for the POJO
                q.setCorrect(rs.getString("correct_option").charAt(0));
                
                list.add(q);
            }
        }
        return list;
    }

    @Override
    public void close() throws Exception {
        stmtFindByQuizId.close();
        super.close(); // Closes the connection
    }
}