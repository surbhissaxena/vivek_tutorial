package com.ss.service;

import com.ss.dto.SubjectDTO;
import com.ss.util.DBResponse;

public interface SubjectService {
	DBResponse addSubject(SubjectDTO dto) throws Exception;
	DBResponse getAllsubject()throws Exception;
}
