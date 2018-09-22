package com.ss.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ss.dao.QuestionDAO;
import com.ss.dao.SubjectDAO;
import com.ss.dto.QuestionDTO;
import com.ss.dto.SubjectDTO;
import com.ss.util.DBResponse;
import com.ss.util.JDBCUtil;
import com.ss.util.Message;
import com.ss.util.OpCode;
import com.ss.vo.QuestionVO;

public class QuestionDAOImpl implements QuestionDAO{

	@Override
	public DBResponse addQuestion(QuestionDTO dto) throws Exception {

		DBResponse dbResponse = new DBResponse();
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = JDBCUtil.getInstance().getConnection();
			con.setAutoCommit(false);
		
			String query_question = "insert into question_table (description,sub_id,option_a,option_b,option_c,option_d,answer) values(?,?,?,?,?,?,?)";
			st = con.prepareStatement(query_question);
			st.setString(1, dto.getDescription());
			st.setInt(2, dto.getSub_id());
			st.setString(3, dto.getOption_a());
			st.setString(4, dto.getOption_b());
			st.setString(5, dto.getOption_c());
			st.setString(6, dto.getOption_d());
			st.setString(7, dto.getAnswer());
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
	public DBResponse getAllQuestionBySubjectId(QuestionDTO dto)
			throws Exception {
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		List<Object> list = new ArrayList<Object>();
		DBResponse dbRespons = new DBResponse();
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = JDBCUtil.getInstance().getConnection();
			String q = "SELECT * FROM  question_table where sub_id = ?";
			st = con.prepareStatement(q);
			st.setInt(1, dto.getSub_id());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				QuestionVO questionVO = new QuestionVO();
				questionVO.setQuestion_id(rs.getInt("question_id"));
				questionVO.setOption_a(rs.getString("option_a"));
				questionVO.setOption_b(rs.getString("option_b"));
				questionVO.setOption_c(rs.getString("option_c"));
				questionVO.setOption_d(rs.getString("option_d"));
				questionVO.setAnswer(rs.getString("answer"));
				questionVO.setDescription(rs.getString("description"));
				SubjectDAO subjectDAO = new SubjectDAOImpl();
				questionVO.setSubject(subjectDAO.getSubjectBySubjectId(rs.getInt("sub_id"), con));
				list.add(questionVO);
			}
			System.out.println(list.isEmpty());
			if (!list.isEmpty()) {
				map.put("list", list);
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
}
