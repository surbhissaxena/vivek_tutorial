package com.ss.service.impl;

import com.ss.dao.SubjectDAO;
import com.ss.dao.impl.SubjectDAOImpl;
import com.ss.dto.SubjectDTO;
import com.ss.service.SubjectService;
import com.ss.util.DBResponse;

public class SubjectServiceImpl implements SubjectService{

	@Override
	public DBResponse addSubject(SubjectDTO dto) throws Exception {
		SubjectDAO dao = new SubjectDAOImpl();
		return dao.addSubject(dto);
	}

	@Override
	public DBResponse getAllsubject() throws Exception {
		SubjectDAO dao = new SubjectDAOImpl();
		return dao.getAllSubject();
	}

}
