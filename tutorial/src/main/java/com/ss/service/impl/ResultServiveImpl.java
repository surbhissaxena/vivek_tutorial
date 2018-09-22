package com.ss.service.impl;

import java.util.Date;

import com.ss.dao.ResultDAO;
import com.ss.dao.impl.ResultDAOImpl;
import com.ss.dto.ResultDTO;
import com.ss.service.ResultService;
import com.ss.util.DBResponse;

public class ResultServiveImpl implements ResultService {

	ResultDAO  resultservice=new ResultDAOImpl();
	@Override
	public DBResponse addResult(ResultDTO dto) throws Exception {
		DBResponse dbResponse = new DBResponse();
		String status = null;
		ResultDAO resultDAO = new ResultDAOImpl();
		double percentage = (dto.getResult() / 10.0) * 100;
		String per = percentage + "%";
		if (percentage >= 40) {
			status = "Eligible";
		} else {
			status = "Not Eligible";
		}
		dto.setStatus(status);
		dto.setPercent(String.valueOf(percentage));
		dto.setResultDate(String.valueOf(new Date().getTime()));
		dbResponse = resultDAO.addResult(dto);
		return dbResponse;
	}

	@Override
	public DBResponse viewReasult() throws Exception {
		// TODO Auto-generated method stub
		return resultservice.viewReasult();
	}

	@Override
	public DBResponse resultById(ResultDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return resultservice.resultById(dto);
	}

	@Override
	public DBResponse searchResultByUsingPrePairdStatement(ResultDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return resultservice.resultById(dto);
	}

}
