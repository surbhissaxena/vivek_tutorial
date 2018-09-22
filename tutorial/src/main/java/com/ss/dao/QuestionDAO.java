package com.ss.dao;

import com.ss.dto.QuestionDTO;
import com.ss.util.DBResponse;

public interface QuestionDAO {
DBResponse addQuestion(QuestionDTO dto)throws Exception;
DBResponse getAllQuestionBySubjectId(QuestionDTO dto)throws Exception;
}
