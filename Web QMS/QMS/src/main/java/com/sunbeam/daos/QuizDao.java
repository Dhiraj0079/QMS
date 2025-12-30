package com.sunbeam.daos;

import java.util.*;
import com.sunbeam.pojos.*;
import com.sunbeam.daos.*;

public interface QuizDao extends AutoCloseable {
    // Admin Functionalities
    void saveQuizWithQuestions(String title, int creatorId, List<Question> questions) throws Exception;
    List<Quiz> findAll() throws Exception;
    int deleteById(int id) throws Exception;
    List<Attempt> getAllResults() throws Exception;

    // Student Functionalities
    int saveAttempt(Attempt attempt) throws Exception;
    List<Attempt> findUserScores(int userId) throws Exception;
}