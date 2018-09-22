package com.ss.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.ss.dao.TutorialDAO;
import com.ss.dto.TutorialDTO;
import com.ss.util.DBResponse;
import com.ss.util.JDBCUtil;
import com.ss.util.Message;
import com.ss.util.OpCode;

public class TutorialDAOImpl implements TutorialDAO{

	@Override
	public DBResponse addTutorial(TutorialDTO dto) throws Exception {

		DBResponse dbResponse = new DBResponse();
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = JDBCUtil.getInstance().getConnection();
			con.setAutoCommit(false);
			String query = "insert into tutorial_table (sub_id, heading, description) values(?,?,?)";
			st = con.prepareStatement(query);
			st.setInt(1, dto.getSub_id());
			st.setString(2, dto.getHeading());
			st.setString(3, dto.getDescription());
			int i = st.executeUpdate();
			if (i != 0 ) {
				dbResponse.setOperationCode(OpCode.SUCCESS);
				dbResponse.setMessage(Message.RECORD_SUCCESSFULLY_SAVED);
			} else {
				dbResponse.setOperationCode(OpCode.FAIL);
				dbResponse.setMessage(Message.RECORD_NOT_SAVED);
			}
			 con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			// con.rollback();
			dbResponse.setOperationCode(OpCode.EXECPTION);
			dbResponse.setMessage(Message.SOMETHING_WENT_WRONG);
		} finally {
			if (con != null) {
				st.close();
				con.close();
			}
		}
		return dbResponse;
	
	}

}
