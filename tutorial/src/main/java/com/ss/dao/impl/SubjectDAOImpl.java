package com.ss.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ss.dao.SubjectDAO;
import com.ss.dto.SubjectDTO;
import com.ss.dto.UserDTO;
import com.ss.util.DBResponse;
import com.ss.util.JDBCUtil;
import com.ss.util.Message;
import com.ss.util.OpCode;
import com.ss.vo.SubjectVO;

public class SubjectDAOImpl implements SubjectDAO{

	@Override
	public DBResponse addSubject(SubjectDTO dto) throws Exception {

		DBResponse dbResponse = new DBResponse();
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = JDBCUtil.getInstance().getConnection();
			con.setAutoCommit(false);
			String query = "insert into subject_table (sub_name) values(?)";
			st = con.prepareStatement(query);
			st.setString(1, dto.getSub_name());
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

	@Override
	public DBResponse getAllSubject() throws Exception {
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		List<Object> userDTOList = new ArrayList<Object>();
		DBResponse dbRespons = new DBResponse();
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = JDBCUtil.getInstance().getConnection();
			String q = "SELECT * FROM  subject_table order by sub_name";
			st = con.prepareStatement(q);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				SubjectVO vo = new SubjectVO();
				vo.setSub_id(rs.getInt("sub_id"));
				vo.setSub_name(rs.getString("sub_name"));
				userDTOList.add(vo);
			}
			System.out.println(userDTOList.isEmpty());
			if (!userDTOList.isEmpty()) {
				map.put("UserList", userDTOList);
				dbRespons.setOperationCode(OpCode.SUCCESS);
				dbRespons.setDataAvailable(true);
				dbRespons.setData(map);
				dbRespons.setMessage(Message.RECORD_FOUND);
			} else {
				dbRespons.setOperationCode(OpCode.FAIL);
				dbRespons.setMessage(Message.RECORD_NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			dbRespons.setOperationCode(OpCode.EXECPTION);
			dbRespons.setMessage(Message.SOMETHING_WENT_WRONG);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {

			}

		}
		return dbRespons;
	}

	@Override
	public SubjectVO getSubjectBySubjectId(int sub_id, Connection con)throws Exception{
		PreparedStatement st = null;
		SubjectVO vo = null;
		String q = "SELECT * FROM  subject_table where sub_id = ?";
		st = con.prepareStatement(q);
		st.setInt(1, sub_id);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			vo = new SubjectVO();
			vo.setSub_id(rs.getInt("sub_id"));
			vo.setSub_name(rs.getString("sub_name"));
		}
		return vo;
	}
	

}
