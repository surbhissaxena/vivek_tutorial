package com.ss.service.impl;

import com.ss.dao.TutorialDAO;
import com.ss.dao.impl.TutorialDAOImpl;
import com.ss.dto.TutorialDTO;
import com.ss.service.TutorialService;
import com.ss.util.DBResponse;

public class TutorialServiceImpl implements TutorialService{

	@Override
	public DBResponse addTutorial(TutorialDTO dto) throws Exception {
		TutorialDAO dao = new TutorialDAOImpl();
		return dao.addTutorial(dto);
	}

}
