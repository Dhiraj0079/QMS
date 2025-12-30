package com.sunbeam.daos;

import java.util.List;
import com.sunbeam.pojos.Question;
import com.sunbeam.daos.*;

public interface QuestionDao extends AutoCloseable {
    List<Question> findByQuizId(int quizId) throws Exception;
}