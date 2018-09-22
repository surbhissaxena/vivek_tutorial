package com.ss.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ss.dao.CollageDAO;
import com.ss.dto.CollageDTO;
import com.ss.dto.ResultDTO;
import com.ss.dto.UserDTO;
import com.ss.util.DBResponse;
import com.ss.util.JDBCUtil;
import com.ss.util.Message;
import com.ss.util.OpCode;

public class CollageDAOImpl implements CollageDAO {

	@Override
	public DBResponse addCollageData(CollageDTO dto) throws Exception {
		System.out.println("contact 2 =="+dto.getClg_Contact());
		DBResponse dbResponse = new DBResponse();
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = JDBCUtil.getInstance().getConnection();
			con.setAutoCommit(false);
			String query = "insert into collage_table values(?,?,?,?,?,?,?,?,?,?,?)";
			st = con.prepareStatement(query);

			st.setInt(1, dto.getClg_Id());
			st.setString(2, dto.getClg_Name());
			st.setString(3, dto.getClg_Univercity());
			st.setString(4, dto.getClg_Address());
			st.setString(5, dto.getClg_Fee());
			st.setString(6, dto.getClg_Branch());
			st.setString(7, dto.getClg_Contact());
			st.setString(8, dto.getClg_degree());
			st.setString(9, dto.getClg_Docoment());
			st.setString(10, dto.getClg_cource_declaration());
			st.setString(11, dto.getClg_Cutoff());

			int i = st.executeUpdate();
			if (i != 0) {
				dbResponse.setOperationCode(OpCode.SUCCESS);
				dbResponse.setMessage(Message.RECORD_SUCCESSFULLY_SAVED);
			} else {
				dbResponse.setOperationCode(OpCode.FAIL);
				dbResponse.setMessage(Message.RECORD_NOT_SAVED);
			}
			con.commit();
		} catch (Exception e) {
			con.rollback();
			dbResponse.setOperationCode(OpCode.EXECPTION);
			dbResponse.setMessage(Message.SOMETHING_WENT_WRONG);
		}

		return dbResponse;
	}

	@Override
	public DBResponse getAllCollageData() throws Exception {
		List<Object> clgDTOList=new ArrayList<>();
		Map<String , List<Object>> map=new HashMap<>();
		DBResponse dbResponse = new DBResponse();
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs=null;
		try {
			con = JDBCUtil.getInstance().getConnection();
			String query = " select * from collage_table";
			st = con.prepareStatement(query);
			rs=st.executeQuery();
			while (rs.next()) {
				CollageDTO dto=new CollageDTO();
				dto.setClg_Id(rs.getInt("clg_id"));
				dto.setClg_Name(rs.getString("clg_name"));
				dto.setClg_Univercity(rs.getString("clg_uni"));
				dto.setClg_Address(rs.getString("clg_address"));
				dto.setClg_Fee(rs.getString("clg_fee"));
				dto.setClg_Branch(rs.getString("clg_branch"));
				dto.setClg_Contact(rs.getString("clg_contact"));
				dto.setClg_degree(rs.getString("clg_degree"));
				dto.setClg_Docoment(rs.getString("clg_docoment"));
				dto.setClg_cource_declaration(rs.getString("clg_corse_dec"));
				dto.setClg_Cutoff(rs.getString("clg_cutoff"));
				clgDTOList.add(dto);
			}
			if(!clgDTOList.isEmpty()){
				map.put("clgList", clgDTOList);
				dbResponse.setOperationCode(OpCode.SUCCESS);
				dbResponse.setDataAvailable(true);
				dbResponse.setData(map);
             dbResponse.setMessage(Message.RECORD_FOUND);
			}else{
				dbResponse.setOperationCode(OpCode.FAIL);
				dbResponse.setMessage(Message.RECORD_NOT_FOUND);
			}

		} catch (Exception e) {
                dbResponse.setOperationCode(OpCode.EXECPTION);
                dbResponse.setMessage(Message.SOMETHING_WENT_WRONG);
		} finally{
			try {
				if(con!=null){
					con.close();
				}
			} catch (Exception e2) {
			}
			
		}
		return dbResponse;
	}

	@Override
	public DBResponse deleteCollageById(CollageDTO dto) throws Exception {
		DBResponse dbRespons = new DBResponse();
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = JDBCUtil.getInstance().getConnection();
             String query =" delete from collage_table  where clg_id =? ";
			 st = con.prepareStatement(query);
	         st.setLong(1,dto.getClg_Id());

			int i = st.executeUpdate();
			if (i != 0) {
				dbRespons.setOperationCode(OpCode.SUCCESS);
				dbRespons.setMessage(Message.RECORD_SUCCESSFULLY_DELETED);
			} else {
				dbRespons.setOperationCode(OpCode.SUCCESS);
				dbRespons.setMessage(Message.RECORD_NOT_DELETE);
			}

		} catch (Exception e) {
			e.printStackTrace();
			dbRespons.setOperationCode(OpCode.EXECPTION);
			dbRespons.setMessage(Message.SOMETHING_WENT_WRONG);
		} finally {
			if (con != null) {
				st.close();
				con.close();
			}
		}
		return dbRespons;
	}

	@Override
	public DBResponse updateCollageById(CollageDTO dto) throws Exception {
			DBResponse dbResponse = new DBResponse();
			Connection con = null;
			PreparedStatement st = null;
			try {
				con = JDBCUtil.getInstance().getConnection();
				con.setAutoCommit(false);
	            String  query="update collage_table set clg_name=?,clg_uni=?,clg_address=?, clg_fee=?,clg_branch=?,clg_contact=?,clg_degree=?,clg_docoment=?,clg_corse_dec=?,clg_cutoff=? where clg_id= ?"; 
				st = con.prepareStatement(query);
				
				st.setString(1, dto.getClg_Name());
				st.setString(2, dto.getClg_Univercity());
				st.setString(3, dto.getClg_Address());
				st.setString(4, dto.getClg_Fee());
				st.setString(5, dto.getClg_Branch());
				st.setString(6, dto.getClg_Contact());
				st.setString(7, dto.getClg_degree());
				st.setString(8, dto.getClg_Docoment());
				st.setString(9, dto.getClg_cource_declaration());
				st.setString(10, dto.getClg_Cutoff());
				st.setInt(11, dto.getClg_Id());
					int i=st.executeUpdate();
				if(i!=0){
					dbResponse.setOperationCode(OpCode.SUCCESS);
	               dbResponse.setMessage(Message.RECORD_FOUND);
				} else {
					dbResponse.setOperationCode(OpCode.FAIL);
					dbResponse.setMessage(Message.RECORD_Not_UPDETED);
				}
				con.commit();
			} catch (Exception e) {
				e.printStackTrace();
				con.rollback();
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
	public DBResponse getCollageById(CollageDTO dto) throws Exception {
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		List<Object> clgDTOList = new ArrayList<Object>();
		DBResponse dbResponse = new DBResponse();
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = JDBCUtil.getInstance().getConnection();
			String q = "SELECT * FROM  collage_table where clg_id=?";
			st = con.prepareStatement(q);
			st.setInt(1, dto.getClg_Id());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				CollageDTO dto1=new CollageDTO();
				dto1.setClg_Id(rs.getInt("clg_id"));
				dto1.setClg_Name(rs.getString("clg_name"));
				dto1.setClg_Univercity(rs.getString("clg_uni"));
				dto1.setClg_Address(rs.getString("clg_address"));
				dto1.setClg_Fee(rs.getString("clg_fee"));
				dto1.setClg_Branch(rs.getString("clg_branch"));
				dto1.setClg_Contact(rs.getString("clg_contact"));
				dto1.setClg_degree(rs.getString("clg_degree"));
				dto1.setClg_Docoment(rs.getString("clg_docoment"));
				dto1.setClg_cource_declaration(rs.getString("clg_corse_dec"));
				dto1.setClg_Cutoff(rs.getString("clg_cutoff"));
				clgDTOList.add(dto1);
			}
			if(!clgDTOList.isEmpty()){
				map.put("clgList", clgDTOList);
				dbResponse.setOperationCode(OpCode.SUCCESS);
				dbResponse.setDataAvailable(true);
				dbResponse.setData(map);
             dbResponse.setMessage(Message.RECORD_FOUND);
			} else {
				dbResponse.setOperationCode(OpCode.FAIL);
				dbResponse.setMessage(Message.RECORD_NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			dbResponse.setOperationCode(OpCode.EXECPTION);
			dbResponse.setMessage(Message.SOMETHING_WENT_WRONG);
		} finally {
			try {
				if (con != null) {
					st.close();
					con.close();

				}
			} catch (Exception e) {

			}

		}
		return dbResponse;

	}

	@Override
	public DBResponse getCollageByPercentage(CollageDTO dto) throws Exception {
		System.out.println("hello = "+dto.getClg_Cutoff());
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		List<Object> clgDTOList = new ArrayList<Object>();
		DBResponse dbResponse = new DBResponse();
		Connection con = null;
		int start=1;
		PreparedStatement st = null;
		try {
			con = JDBCUtil.getInstance().getConnection();
			String query = "SELECT * FROM collage_table where clg_cutoff between ? and ? order by clg_cutoff desc";
			st = con.prepareStatement(query);
			st.setInt(1, start-1);
	        st.setString(2, dto.getClg_Cutoff());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				CollageDTO dto1=new CollageDTO();
				dto1.setClg_Id(rs.getInt("clg_id"));
				dto1.setClg_Name(rs.getString("clg_name"));
				dto1.setClg_Univercity(rs.getString("clg_uni"));
				dto1.setClg_Address(rs.getString("clg_address"));
				dto1.setClg_Fee(rs.getString("clg_fee"));
				dto1.setClg_Branch(rs.getString("clg_branch"));
				dto1.setClg_Contact(rs.getString("clg_contact"));
				dto1.setClg_degree(rs.getString("clg_degree"));
				dto1.setClg_Docoment(rs.getString("clg_docoment"));
				dto1.setClg_cource_declaration(rs.getString("clg_corse_dec"));
				dto1.setClg_Cutoff(rs.getString("clg_cutoff"));
				clgDTOList.add(dto1);
			}
			if(!clgDTOList.isEmpty()){
				map.put("clgList", clgDTOList);
				dbResponse.setOperationCode(OpCode.SUCCESS);
				dbResponse.setDataAvailable(true);
				dbResponse.setData(map);
             dbResponse.setMessage(Message.RECORD_FOUND);
			} else {
				dbResponse.setOperationCode(OpCode.FAIL);
				dbResponse.setMessage(Message.RECORD_NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			dbResponse.setOperationCode(OpCode.EXECPTION);
			dbResponse.setMessage(Message.SOMETHING_WENT_WRONG);
		} finally {
			try {
				if (con != null) {
					st.close();
					con.close();

				}
			} catch (Exception e) {

			}

		}
		return dbResponse;

	}

	@Override
	public DBResponse searchCollageByUsingPrePaidStatement(CollageDTO dto) throws Exception {
		DBResponse dbResponse = new DBResponse();
		Map<String, List<Object>> map = new HashMap<>();
		List<Object> clgDTOList = new ArrayList<Object>();
		Connection con = null;
		Statement st = null;
		try {
			con = JDBCUtil.getInstance().getConnection();
			String query = "SELECT * FROM  collage_table where 1 = 1 ";
			StringBuilder sb = new StringBuilder();
			sb.append(query);
			if (dto.getClg_Id() != 0) {
				sb.append(" and clg_id = " + dto.getClg_Id()  );
			}
			if (dto.getClg_Name() != null) {
				sb.append(" and clg_name like '%" + dto.getClg_Name().trim() + "%'" );
			}
			if (dto.getClg_Univercity() != null) {
				sb.append(" and clg_uni like '%" + dto.getClg_Univercity() + "%'");
			}
			if (dto.getClg_Address() != null) {
				sb.append(" and clg_address like '%" + dto.getClg_Address() + "%'");
			}
			if (dto.getClg_Fee() != null) {
				sb.append(" and clg_fee like '%" + dto.getClg_Fee() + "%'");
			}
			if (dto.getClg_Contact() != null) {
				sb.append(" and clg_contact like '%" + dto.getClg_Contact() + "%'");
			}
			if (dto.getClg_degree() != null) {
				sb.append(" and clg_degree like '%" + dto.getClg_degree() + "%'");
			}
			if (dto.getClg_Docoment() != null) {
				sb.append(" and clg_docoment like '%" + dto.getClg_Docoment() + "%'");
			}
			if (dto.getClg_cource_declaration() != null) {
				sb.append(" and clg_corse_dec like '%" + dto.getClg_cource_declaration() + "%'");
			}
			if (dto.getClg_Cutoff() != null) {
				sb.append(" and clg_cutoff like '%" + dto.getClg_Cutoff() + "%'");
			}
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sb.toString());
			while (rs.next()) {
				CollageDTO dto1=new CollageDTO();
				dto1.setClg_Id(rs.getInt("clg_id"));
				dto1.setClg_Name(rs.getString("clg_name"));
				dto1.setClg_Univercity(rs.getString("clg_uni"));
				dto1.setClg_Address(rs.getString("clg_address"));
				dto1.setClg_Fee(rs.getString("clg_fee"));
				dto1.setClg_Branch(rs.getString("clg_branch"));
				dto1.setClg_Contact(rs.getString("clg_contact"));
				dto1.setClg_degree(rs.getString("clg_degree"));
				dto1.setClg_Docoment(rs.getString("clg_docoment"));
				dto1.setClg_cource_declaration(rs.getString("clg_corse_dec"));
				dto1.setClg_Cutoff(rs.getString("clg_cutoff"));
				clgDTOList.add(dto1);
			}
			if(!clgDTOList.isEmpty()){
				map.put("clgList", clgDTOList);
			dbResponse.setOperationCode(OpCode.SUCCESS);
				dbResponse.setDataAvailable(true);
				dbResponse.setData(map);
				dbResponse.setMessage(Message.RECORD_FOUND);
			} else {
				dbResponse.setOperationCode(OpCode.FAIL);
				dbResponse.setMessage(Message.RECORD_NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			dbResponse.setOperationCode(OpCode.EXECPTION);
			dbResponse.setMessage(Message.SOMETHING_WENT_WRONG);
		} finally {
			try {
				if (con != null) {
					st.close();
					con.close();
				}
			} catch (Exception e) {
			}
		}
		return dbResponse;
	}
}
