package com.ss.dao;

import com.ss.dto.ResultDTO;
import com.ss.util.DBResponse;


public interface ResultDAO {
	DBResponse addResult(ResultDTO dto )throws Exception;
    DBResponse viewReasult()throws Exception;
    DBResponse resultById(ResultDTO dto)throws Exception;
    DBResponse searchResultByUsingPrePairdStatement(ResultDTO dto)throws Exception;	

}
