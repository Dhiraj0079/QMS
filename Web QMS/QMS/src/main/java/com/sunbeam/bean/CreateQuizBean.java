package com.sunbeam.bean;

import java.io.File;
import java.util.List;
import com.sunbeam.util.QuestionFileParser; // Using your parser
import com.sunbeam.daos.*;
import com.sunbeam.pojos.*;

public class CreateQuizBean {
    private String title;
    private String filePath; // Path to the uploaded .txt file
    private int adminId;
    private boolean status;

    public CreateQuizBean() {}

    // Method to execute the import
    public void importAndSave() {
        try (QuizDao quizDao = new QuizDaoImpl()) {
            // 1. Parse the file using your utility
            File file = new File(filePath);
            List<Question> questions = QuestionFileParser.parse(file);

            // 2. Save the Quiz and Questions via DAO
            // Ensure your QuizDaoImpl has a method for transactional saving
            quizDao.saveQuizWithQuestions(title, adminId, questions);
            this.status = true;
        } catch (Exception e) {
            e.printStackTrace();
            this.status = false;
        }
    }

    // Getters and Setters for title, filePath, adminId, and status...
}
