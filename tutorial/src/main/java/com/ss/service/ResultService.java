package com.ss.service;

import com.ss.dto.ResultDTO;
import com.ss.util.DBResponse;


public interface ResultService {

	DBResponse addResult(ResultDTO dto )throws Exception;
    DBResponse viewReasult()throws Exception;
    DBResponse resultById(ResultDTO dto)throws Exception;
    DBResponse searchResultByUsingPrePairdStatement(ResultDTO dto)throws Exception;	

}
