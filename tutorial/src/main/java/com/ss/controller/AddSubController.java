package com.ss.controller;

import com.ss.dto.SubjectDTO;
import com.ss.service.SubjectService;
import com.ss.service.impl.SubjectServiceImpl;
import com.ss.util.DBResponse;

public class AddSubController {

	public DBResponse addSubject(SubjectDTO dto) throws Exception{
		SubjectService service = new SubjectServiceImpl();
		DBResponse dbResponse = service.addSubject(dto);
		return dbResponse;
	}
}
