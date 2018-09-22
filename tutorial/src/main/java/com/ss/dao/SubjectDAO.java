package com.ss.dao;

import java.sql.Connection;

import com.ss.dto.SubjectDTO;
import com.ss.util.DBResponse;
import com.ss.vo.SubjectVO;

public interface SubjectDAO {

	DBResponse addSubject(SubjectDTO dto)throws Exception;
	DBResponse getAllSubject()throws Exception;
	SubjectVO getSubjectBySubjectId(int sub_id, Connection con)throws Exception;
	
}
