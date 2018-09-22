package com.ss.dao;

import com.ss.dto.CollageDTO;
import com.ss.dto.UserDTO;
import com.ss.util.DBResponse;

public interface CollageDAO {

	DBResponse addCollageData( CollageDTO dto)throws Exception;
	DBResponse getAllCollageData()throws Exception;
	DBResponse deleteCollageById(CollageDTO dto)throws Exception;
	DBResponse updateCollageById(CollageDTO dto)throws Exception;
	DBResponse getCollageById(CollageDTO dto)throws Exception;
	DBResponse getCollageByPercentage(CollageDTO dto)throws Exception;
	
    DBResponse searchCollageByUsingPrePaidStatement(CollageDTO dto)throws Exception;	

	
}
