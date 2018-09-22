package com.ss.service;

import com.ss.dto.CollageDTO;
import com.ss.util.DBResponse;

public interface CollageService {

	DBResponse addCollageData( CollageDTO dto)throws Exception;
	DBResponse getAllCollageData()throws Exception;
	DBResponse deleteCollageById(CollageDTO dto)throws Exception;
	DBResponse updateCollageById(CollageDTO dto)throws Exception;
	DBResponse getCollageById(CollageDTO dto) throws Exception;
	DBResponse getCollageByPercentage(CollageDTO dto)throws Exception;
	DBResponse searchCollageByUsingPrePaidStatement(CollageDTO dto)throws Exception;	


}
