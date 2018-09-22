package com.ss.service;

import com.ss.dto.QuestionDTO;
import com.ss.util.DBResponse;

public interface QuestionService {
	DBResponse addQuestion(QuestionDTO dto)throws Exception;
	DBResponse getAllQuestionBySubjectId(QuestionDTO dto)throws Exception;
}
