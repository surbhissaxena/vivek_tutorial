package com.ss.service.impl;

import com.ss.dao.CollageDAO;
import com.ss.dao.impl.CollageDAOImpl;
import com.ss.dto.CollageDTO;
import com.ss.service.CollageService;
import com.ss.util.DBResponse;

public class CollageServiceImpl implements CollageService {
      
	 CollageDAO collageDao=new CollageDAOImpl(); 
	@Override
	public DBResponse addCollageData(CollageDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return collageDao.addCollageData(dto) ;
	}
	@Override
	public DBResponse getAllCollageData() throws Exception {
		// TODO Auto-generated method stub
		return collageDao.getAllCollageData();
	}
	@Override
	public DBResponse deleteCollageById(CollageDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return collageDao.deleteCollageById(dto);
	}
	@Override
	public DBResponse updateCollageById(CollageDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return collageDao.updateCollageById(dto);
	}
	@Override
	public DBResponse getCollageById(CollageDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return collageDao.getCollageById(dto);
	}
	@Override
	public DBResponse getCollageByPercentage(CollageDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return  collageDao.getCollageByPercentage(dto);
	}
	@Override
	public DBResponse searchCollageByUsingPrePaidStatement(CollageDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return collageDao.searchCollageByUsingPrePaidStatement(dto);
	}

}
