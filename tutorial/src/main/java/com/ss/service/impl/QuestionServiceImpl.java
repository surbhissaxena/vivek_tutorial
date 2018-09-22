package com.ss.service.impl;

import com.ss.dao.QuestionDAO;
import com.ss.dao.impl.QuestionDAOImpl;
import com.ss.dto.QuestionDTO;
import com.ss.service.QuestionService;
import com.ss.util.DBResponse;

public class QuestionServiceImpl implements QuestionService{

	@Override
	public DBResponse addQuestion(QuestionDTO dto) throws Exception {
		QuestionDAO dao = new QuestionDAOImpl();
		return dao.addQuestion(dto);
	}

	@Override
	public DBResponse getAllQuestionBySubjectId(QuestionDTO dto)
			throws Exception {
		QuestionDAO dao = new QuestionDAOImpl();
		return dao.getAllQuestionBySubjectId(dto);
	}



	
}
