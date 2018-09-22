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

import com.ss.dao.UserDAO;
import com.ss.dto.UserDTO;
import com.ss.util.DBResponse;
import com.ss.util.JDBCUtil;
import com.ss.util.Message;
import com.ss.util.OpCode;

public class UserDAOImpl implements UserDAO {

	public DBResponse addUser(UserDTO dto) throws Exception {
		DBResponse dbResponse = new DBResponse();
		System.out.println(dto.getUserPassword());
		Connection con = null;
		PreparedStatement st = null;
		PreparedStatement st1 = null;
		PreparedStatement st2 = null;
		try {
			con = JDBCUtil.getInstance().getConnection();
			 con.setAutoCommit(false);
			int profileID = getNextProfileID();
			int userID = getNextUserID();
			String query = " insert into profile_table (profile_id,first_name, last_name,contact_no, address, email_id) values(?,?,?,?,?,?)";
			String query1 = " insert into user_table (user_id,profile_id) values(?,?)";
			String query2 = " insert into login_table (login_id,user_name,password,role) values(?,?,?,?)";

			st = con.prepareStatement(query);
			st1 = con.prepareStatement(query1);
			st2 = con.prepareStatement(query2);

			st.setInt(1, profileID);
			st.setString(2, dto.getFirstName());
			st.setString(3, dto.getLastName());
			st.setString(4, dto.getContactNo());
			st.setString(5, dto.getAddress());
			st.setString(6, dto.getEmailId());

			st1.setInt(1, userID);
			st1.setInt(2, profileID);

			// st2.setInt(1,dto.getUserId());
			st2.setInt(1, profileID);
			st2.setString(2, dto.getUserName());
			st2.setString(3, dto.getUserPassword());
			st2.setString(4, dto.getRole());

			int i = st.executeUpdate();
			int i1 = st1.executeUpdate();
			int i2 = st2.executeUpdate();
			if (i != 0 && i1 != 0 && i2 != 0) {
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
			if(e.getMessage().contains("contact_no_UNIQUE")) {
				dbResponse.setMessage("contact no already registered with another user!");
			}
			if(e.getMessage().contains("email_id_UNIQUE")) {
				dbResponse.setMessage("email id already registered with another user!");
			}
			
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

	public int getNextProfileID() throws Exception {
		Connection con = null;
		PreparedStatement st = null;
		int id = 0;
		try {
			con = JDBCUtil.getInstance().getConnection();
			String query = "SELECT max(profile_id)proid FROM profile_table";
			st = con.prepareStatement(query);
			ResultSet rs = st.executeQuery(query);
			rs.next();
			id = rs.getInt("proid");
			++id;
			System.out.println("profile_id = " + id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				st.close();
				con.close();
			}
		}
		return id;
	}

	private int getNextUserID() throws Exception {
		Connection con = null;
		PreparedStatement st = null;
		int id = 0;
		try {
			con = JDBCUtil.getInstance().getConnection();
			String query = "select max(user_id)userid FROM user_table";
			st = con.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			rs.next();
			id = rs.getInt("userid");
			++id;
			System.out.println("user_id = " + id);
		} catch (Exception e) {
			if (con != null) {
				st.close();
				con.close();
			}
		}
		return id;
	}

	public DBResponse getAllUserData() throws Exception {
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		List<Object> userDTOList = new ArrayList<Object>();
		DBResponse dbRespons = new DBResponse();
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = JDBCUtil.getInstance().getConnection();
			String q = "SELECT * FROM  login_table login , profile_table profile , user_table user where user.user_id=login.login_id and user.profile_id=profile.profile_id";
			st = con.prepareStatement(q);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				UserDTO dto = new UserDTO();
				dto.setProfileId(rs.getInt("profile.profile_id"));
				dto.setFirstName(rs.getString("first_name"));
				dto.setLastName(rs.getString("last_name"));
				dto.setContactNo(rs.getString("contact_no"));
				dto.setAddress(rs.getString("address"));
				dto.setEmailId(rs.getString("email_id"));
				dto.setUserId(rs.getInt("login_id"));
				dto.setUserName(rs.getString("user_name"));
				dto.setUserPassword(rs.getString("password"));
				dto.setPassToken(rs.getString("pass_token"));
				dto.setRole(rs.getString("role"));
				userDTOList.add(dto);
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

	public DBResponse getUserById(UserDTO dto) throws Exception {
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		List<Object> userDTOList = new ArrayList<Object>();
		DBResponse dbRespons = new DBResponse();
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = JDBCUtil.getInstance().getConnection();
			String q = "SELECT * FROM  login_table login , profile_table profile , user_table user where user.user_id=login.login_id and user.profile_id=profile.profile_id and user.user_id=?";
			st = con.prepareStatement(q);
			st.setLong(1, dto.getUserId());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				UserDTO dto1 = new UserDTO();
				dto1.setProfileId(rs.getInt("profile.profile_id"));
				dto1.setFirstName(rs.getString("first_name"));
				dto1.setLastName(rs.getString("last_name"));
				dto1.setContactNo(rs.getString("contact_no"));
				dto1.setAddress(rs.getString("address"));
				dto1.setEmailId(rs.getString("email_id"));
				dto1.setUserId(rs.getInt("login_id"));
				dto1.setUserName(rs.getString("user_name"));
				dto1.setUserPassword(rs.getString("password"));
				userDTOList.add(dto1);
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
					st.close();
					con.close();

				}
			} catch (Exception e) {

			}

		}
		return dbRespons;
	}

	public DBResponse getUserByName(UserDTO dto) throws Exception {
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		List<Object> userDTO = new ArrayList<Object>();
		DBResponse dbResponse = new DBResponse();
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = JDBCUtil.getInstance().getConnection();
			String query = "SELECT * FROM user_db.login_table login , user_db.user_table user , user_db.profile_table profile where user.user_id= login.login_id and user.profile_id=profile.profile_id and login.user_name =? and login.password =? ";

			st = con.prepareStatement(query);
			st.setString(1, dto.getUserName());
			st.setString(2, dto.getUserPassword());

			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				UserDTO dto1 = new UserDTO();
				dto1.setProfileId(rs.getInt("profile_id"));
				dto1.setFirstName(rs.getString("first_name"));
				dto1.setLastName(rs.getString("last_name"));
				dto1.setContactNo(rs.getString("contact_no"));
				dto1.setAddress(rs.getString("address"));
				dto1.setEmailId(rs.getString("email_id"));

				dto1.setUserId(rs.getInt("login_id"));
				dto1.setUserName(rs.getString("user_name"));
				dto1.setUserPassword(rs.getString("password"));
				userDTO.add(dto1);
			}
			if (!userDTO.isEmpty()) {
				map.put("UserList", userDTO);
				dbResponse.setOperationCode(OpCode.SUCCESS);
				dbResponse.setDataAvailable(true);
				dbResponse.setData(map);
				dbResponse.setMessage(Message.RECORD_FOUND);
			} else {
				dbResponse.setOperationCode(OpCode.SUCCESS);
				dbResponse.setMessage(Message.RECORD_NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			dbResponse.setOperationCode(OpCode.EXECPTION);
			dbResponse.setMessage(Message.SOMETHING_WENT_WRONG);
		}

		return dbResponse;
	}

	public DBResponse deleteUserById(UserDTO dto) throws Exception {
		DBResponse dbRespons = new DBResponse();
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = JDBCUtil.getInstance().getConnection();
			String query = " delete login_table,profile_table ,user_table from user_table inner join profile_table inner join login_table  where user_table.user_id=profile_table.profile_id and profile_table.profile_id=login_table.login_id and user_table.user_id =? ";
			st = con.prepareStatement(query);
			st.setLong(1, dto.getUserId());

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

	public DBResponse updateUser(UserDTO dto) throws Exception {
		DBResponse dbResponse = new DBResponse();
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = JDBCUtil.getInstance().getConnection();
			con.setAutoCommit(false);
			String query = "update profile_table profile inner join login_table login on profile.profile_id=login.login_id set profile.first_name= ?,profile.last_name= ?,profile.contact_no= ?,profile.address= ?,"
					+ "profile.email_id= ?,profile.course= ?,login.user_name= ? where profile.profile_id= ? ;";

			st = con.prepareStatement(query);

			st.setString(1, dto.getFirstName());
			st.setString(2, dto.getLastName());
			st.setString(3, dto.getContactNo());
			st.setString(4, dto.getAddress());
			st.setString(5, dto.getEmailId());
			st.setString(7, dto.getUserName());
			st.setLong(8, dto.getProfileId());

			int i = st.executeUpdate();
			if (i != 0) {
				dbResponse.setOperationCode(OpCode.SUCCESS);
				dbResponse.setMessage(Message.RECORD_SUCCESSFULLY_UPDETED);
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

	public DBResponse getUserByUserName(UserDTO dto) throws Exception {

		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		List<Object> list = new ArrayList<Object>();
		DBResponse dbRespons = new DBResponse();
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = JDBCUtil.getInstance().getConnection();
			String query = "SELECT * FROM user_db.login_table login , user_db.user_table user , user_db.profile_table profile where user.user_id= login.login_id and user.profile_id=profile.profile_id and login.user_name = ? and login.password = ?";
			st = con.prepareStatement(query);
			st.setString(1, dto.getUserName().trim());
			st.setString(2, dto.getUserPassword().trim());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				dto.setProfileId(rs.getInt("profile_id"));
				dto.setFirstName(rs.getString("first_name"));
				dto.setLastName(rs.getString("last_name"));
				dto.setContactNo(rs.getString("contact_no"));
				dto.setAddress(rs.getString("address"));
				dto.setEmailId(rs.getString("email_id"));
				dto.setUserId(rs.getInt("login_id"));
				dto.setUserName(rs.getString("user_name"));
				dto.setUserPassword(rs.getString("password"));
				dto.setRole(rs.getString("role"));
				list.add(dto);
			}
			if (list.size() != 0) {
				map.put("UserList", list);
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
		}

		return dbRespons;
	}

	public DBResponse searchUser(UserDTO dto) throws Exception {
		DBResponse dbResponse = new DBResponse();
		Map<String, List<Object>> map = new HashMap<>();
		List<Object> userDTOList = new ArrayList<Object>();
		Connection con = null;
		Statement st = null;
		try {
			con = JDBCUtil.getInstance().getConnection();
			String query = "SELECT * FROM  login_table login , profile_table profile , user_table user where user.user_id=login.login_id and user.profile_id=profile.profile_id";
			StringBuilder sb = new StringBuilder();
			sb.append(query);
			if (dto.getProfileId() != 0) {
				sb.append(" and profile_id = " + dto.getProfileId());
			}
			if (dto.getUserName() != null) {
				sb.append(" and user_name like '%" + dto.getUserName() + "%'");
			}
			if (dto.getFirstName() != null) {
				sb.append(" and first_name like '%" + dto.getFirstName() + "%'");
			}
			if (dto.getLastName() != null) {
				sb.append(" and last_name like '%" + dto.getLastName() + "%'");
			}
			if (dto.getContactNo() != null) {
				sb.append(" and contact_no like '%" + dto.getContactNo() + "%'");
			}
			if (dto.getAddress() != null) {
				sb.append(" and address like '%" + dto.getAddress() + "%'");
			}
			if (dto.getEmailId() != null) {
				sb.append(" and email_id like '%" + dto.getEmailId() + "%'");
			}
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sb.toString());
			while (rs.next()) {
				UserDTO dto1 = new UserDTO();
				dto1.setProfileId(rs.getInt("profile_id"));
				dto1.setFirstName(rs.getString("first_name"));
				dto1.setLastName(rs.getString("last_name"));
				dto1.setContactNo(rs.getString("contact_no"));
				dto1.setAddress(rs.getString("address"));
				dto1.setEmailId(rs.getString("email_id"));
				dto1.setUserName(rs.getString("user_name"));
				userDTOList.add(dto1);
			}
			System.out.println(userDTOList.isEmpty());
			if (!userDTOList.isEmpty()) {
				map.put("UserList", userDTOList);
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
					con.close();
				}
			} catch (Exception e) {
			}
		}
		return dbResponse;
	}

	public DBResponse searchUserByUsingPrePaidStatement(UserDTO dto) throws Exception {
		DBResponse dbResponse = new DBResponse();
		Map<String, List<Object>> map = new HashMap<>();
		List<Object> userDTOList = new ArrayList<Object>();
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = JDBCUtil.getInstance().getConnection();
			String query = "SELECT * FROM  login_table login , profile_table profile , user_table user where user.user_id=login.login_id and user.profile_id=profile.profile_id";
			StringBuilder sb = new StringBuilder();
			sb.append(query);

			List<String> params = new ArrayList<>();
			if (dto.getUserName() != null) {
				sb.append(" and user_name like ?");
				params.add(dto.getUserName());
			}
			if (dto.getFirstName() != null) {
				sb.append(" and first_name like ?");
				params.add(dto.getFirstName());
			}
			if (dto.getLastName() != null) {
				sb.append(" and last_name like ?");
				params.add(dto.getLastName());
			}
			if (dto.getContactNo() != null) {
				sb.append(" and contact_no like ?");
				params.add(dto.getContactNo());
			}
			if (dto.getAddress() != null) {
				sb.append(" and address like ?");
				params.add(dto.getAddress());
			}
			if (dto.getEmailId() != null) {
				sb.append(" and email_id like ?");
				params.add(dto.getEmailId());
			}
			st = con.prepareStatement(sb.toString());
			Iterator<String> it = params.iterator();
			int count = 1;
			while (it.hasNext()) {
				String e = (String) it.next();
				st.setString(count, "%" + e.trim() + "%");
				count++;
			}
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				UserDTO dto1 = new UserDTO();
				dto1.setProfileId(rs.getInt("profile_id"));
				dto1.setFirstName(rs.getString("first_name"));
				dto1.setLastName(rs.getString("last_name"));
				dto1.setContactNo(rs.getString("contact_no"));
				dto1.setAddress(rs.getString("address"));
				dto1.setEmailId(rs.getString("email_id"));
				dto1.setUserName(rs.getString("user_name"));
				userDTOList.add(dto1);
			}
			System.out.println(userDTOList.isEmpty());
			if (!userDTOList.isEmpty()) {
				map.put("UserList", userDTOList);
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
	public DBResponse updateUserPasswordToken(UserDTO dto) throws Exception {
		DBResponse dbResponse = new DBResponse();
		System.out.println("updateUserPasswordToken method");
		System.out.println(dto.getEmailId());
		System.out.println(dto.getPassToken());
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = JDBCUtil.getInstance().getConnection();
			con.setAutoCommit(false);
			String query = " update profile_table set pass_token =? where email_id =? ";
			st = con.prepareStatement(query);
			st.setString(1, dto.getPassToken());
			st.setString(2, dto.getEmailId());
			int i = st.executeUpdate();
			if (i != 0) {
				dbResponse.setOperationCode(OpCode.SUCCESS);
				dbResponse.setMessage(Message.RECORD_SUCCESSFULLY_UPDETED);
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
	public DBResponse userResetPassword(UserDTO dto) throws Exception {
		DBResponse dbResponse = new DBResponse();
		Connection con = null;
		PreparedStatement st = null;
		System.out.println(dto.getUserName() + "  " + dto.getResetPassword());
		try {
			con = JDBCUtil.getInstance().getConnection();
			con.setAutoCommit(false);
			String query = " update login_table set password =? where user_name =? ";
			st = con.prepareStatement(query);
			st.setString(1, dto.getUserPassword());
			st.setString(2, dto.getUserName());

			int i = st.executeUpdate();
			if (i != 0) {
				dbResponse.setOperationCode(OpCode.SUCCESS);
				dbResponse.setMessage(Message.RECORD_SUCCESSFULLY_UPDETED);
			} else {
				dbResponse.setOperationCode(OpCode.FAIL);
				dbResponse.setMessage(Message.RECORD_Not_UPDETED);
			}
			con.commit();
		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
			dbResponse.setOperationCode(OpCode.EXECPTION);
			dbResponse.setMessage(Message.SOMETHING_WENT_WRONG);
		}
		return dbResponse;
	}

	public DBResponse getUserRecordByPagination(int start, int total) throws Exception {
		System.out.println("hello = " + start + total);
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		List<Object> userDTOList = new ArrayList<Object>();
		DBResponse dbRespons = new DBResponse();
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = JDBCUtil.getInstance().getConnection();
			String query = "SELECT * FROM  login_table login , profile_table profile , user_table user where user.user_id=login.login_id and user.profile_id=profile.profile_id  limit ?,?";
			st = con.prepareStatement(query);
			st.setInt(1, start - 1);
			st.setInt(2, total);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				UserDTO dto = new UserDTO();
				dto.setProfileId(rs.getInt("profile.profile_id"));
				dto.setFirstName(rs.getString("first_name"));
				dto.setLastName(rs.getString("last_name"));
				dto.setContactNo(rs.getString("contact_no"));
				dto.setAddress(rs.getString("address"));
				dto.setEmailId(rs.getString("email_id"));
				dto.setUserId(rs.getInt("login_id"));
				dto.setUserName(rs.getString("user_name"));
				dto.setUserPassword(rs.getString("password"));
				userDTOList.add(dto);
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
	public DBResponse updateUserResetPassword(UserDTO dto) throws Exception {
		DBResponse dbResponse = new DBResponse();
		System.out.println("updateUserResetPassword method");
		System.out.println(dto.getEmailId());
		System.out.println(dto.getUserPassword());
		Connection con = null;
		PreparedStatement st = null;

		try {
			con = JDBCUtil.getInstance().getConnection();
			con.setAutoCommit(false);
			String query = "update login_table login inner join profile_table profile on login.login_id=profile.profile_id set login.password =? where profile.email_id =? ";
			st = con.prepareStatement(query);
			st.setString(1, dto.getUserPassword());
			st.setString(2, dto.getEmailId());
			int i = st.executeUpdate();
			if (i != 0) {
				dbResponse.setOperationCode(OpCode.SUCCESS);
				dbResponse.setMessage(Message.PASSWORD_SUCCESSFULLY_UPDATE);
			} else {
				dbResponse.setOperationCode(OpCode.FAIL);
				dbResponse.setMessage(Message.PASSWORD_NOT_SUCCESSFULLY_UPDATE);
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
	public DBResponse aboutMe(UserDTO dto) throws Exception {
		DBResponse dbResponse = new DBResponse();
		System.out.println("aboutMe ....");
		System.out.println("profile id = " + dto.getProfileId());
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = JDBCUtil.getInstance().getConnection();
			con.setAutoCommit(false);
			String query = " update profile_table set about_me = ? where profile_id = ? ";
			st = con.prepareStatement(query);
			st.setLong(2, dto.getProfileId());
			int i = st.executeUpdate();
			if (i != 0) {
				dbResponse.setOperationCode(OpCode.SUCCESS);
				dbResponse.setMessage(Message.RECORD_SUCCESSFULLY_UPDETED);
			} else {
				dbResponse.setOperationCode(OpCode.FAIL);
				dbResponse.setMessage(Message.RECORD_Not_UPDETED);
			}
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
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
	public DBResponse getAllOperator() throws Exception {
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		List<Object> userDTOList = new ArrayList<Object>();
		DBResponse dbRespons = new DBResponse();
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = JDBCUtil.getInstance().getConnection();
			String q = "SELECT * FROM  login_table login , profile_table profile where profile.profile_id=login.login_id and login.role='operator'";
			st = con.prepareStatement(q);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				UserDTO dto1 = new UserDTO();
				dto1.setProfileId(rs.getInt("profile.profile_id"));
				dto1.setFirstName(rs.getString("first_name"));
				dto1.setLastName(rs.getString("last_name"));
				dto1.setContactNo(rs.getString("contact_no"));
				dto1.setAddress(rs.getString("address"));
				dto1.setEmailId(rs.getString("email_id"));
				dto1.setUserId(rs.getInt("login_id"));
				dto1.setUserName(rs.getString("user_name"));
				dto1.setUserPassword(rs.getString("password"));
				dto1.setRole(rs.getString("role"));

				userDTOList.add(dto1);
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
					st.close();
					con.close();

				}
			} catch (Exception e) {

			}

		}
		return dbRespons;

	}

	@Override
	public DBResponse getAllStudent() throws Exception {
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		List<Object> userDTOList = new ArrayList<Object>();
		DBResponse dbRespons = new DBResponse();
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = JDBCUtil.getInstance().getConnection();
			con.setAutoCommit(false);
			String q = "SELECT * FROM  login_table login , profile_table profile where profile.profile_id=login.login_id and login.role='student'";
			st = con.prepareStatement(q);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				UserDTO dto1 = new UserDTO();
				dto1.setProfileId(rs.getInt("profile.profile_id"));
				dto1.setFirstName(rs.getString("first_name"));
				dto1.setLastName(rs.getString("last_name"));
				dto1.setContactNo(rs.getString("contact_no"));
				dto1.setAddress(rs.getString("address"));
				dto1.setEmailId(rs.getString("email_id"));
				dto1.setUserId(rs.getInt("login_id"));
				dto1.setUserName(rs.getString("user_name"));
				dto1.setUserPassword(rs.getString("password"));
				dto1.setRole(rs.getString("role"));
				userDTOList.add(dto1);
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
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			dbRespons.setOperationCode(OpCode.EXECPTION);
			dbRespons.setMessage(Message.SOMETHING_WENT_WRONG);
		} finally {
			try {
				if (con != null) {
					st.close();
					con.close();

				}
			} catch (Exception e) {

			}

		}
		return dbRespons;

	}

	@Override
	public DBResponse getUserByEmailAndPassword(UserDTO dto) throws Exception {

		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		List<Object> list = new ArrayList<Object>();
		DBResponse dbRespons = new DBResponse();
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = JDBCUtil.getInstance().getConnection();
			String query = "SELECT * FROM login_table login , profile_table profile where  login.login_id = profile.profile_id and profile.email_id = ? and login.password = ?";
			st = con.prepareStatement(query);
			st.setString(1, dto.getEmailId().trim());
			st.setString(2, dto.getUserPassword().trim());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				dto.setProfileId(rs.getInt("profile_id"));
				dto.setFirstName(rs.getString("first_name"));
				dto.setLastName(rs.getString("last_name"));
				dto.setContactNo(rs.getString("contact_no"));
				dto.setAddress(rs.getString("address"));
				dto.setEmailId(rs.getString("email_id"));
				dto.setUserId(rs.getInt("login_id"));
				dto.setUserName(rs.getString("user_name"));
				dto.setUserPassword(rs.getString("password"));
				dto.setRole(rs.getString("role"));
				list.add(dto);
			}
			if (list.size() != 0) {
				map.put("UserList", list);
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
		}

		return dbRespons;
	}

	@Override
	public DBResponse getUserByEmail(UserDTO dto) throws Exception {
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		List<Object> userDTOList = new ArrayList<Object>();
		DBResponse dbRespons = new DBResponse();
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = JDBCUtil.getInstance().getConnection();
			String q = "SELECT * FROM  login_table login , profile_table profile , user_table user where profile.profile_id=login.login_id and user.profile_id=profile.profile_id and profile.email_id = ?";
			st = con.prepareStatement(q);
			st.setString(1, dto.getEmailId().trim());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				UserDTO dto1 = new UserDTO();
				dto1.setProfileId(rs.getInt("profile.profile_id"));
				dto1.setFirstName(rs.getString("first_name"));
				dto1.setLastName(rs.getString("last_name"));
				dto1.setContactNo(rs.getString("contact_no"));
				dto1.setAddress(rs.getString("address"));
				dto1.setEmailId(rs.getString("email_id"));
				dto1.setUserId(rs.getInt("login_id"));
				dto1.setUserName(rs.getString("user_name"));
				dto1.setUserPassword(rs.getString("password"));
				userDTOList.add(dto1);
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
					st.close();
					con.close();

				}
			} catch (Exception e) {

			}

		}
		return dbRespons;
	}

	@Override
	public DBResponse getUserByEmailAndToken(UserDTO dto) throws Exception {
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		List<Object> userDTOList = new ArrayList<Object>();
		DBResponse dbRespons = new DBResponse();
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = JDBCUtil.getInstance().getConnection();
			String q = "SELECT * FROM  login_table login , profile_table profile , user_table user where profile.profile_id=login.login_id and user.profile_id=profile.profile_id and profile.email_id = ? and profile.pass_token = ?";
			st = con.prepareStatement(q);
			st.setString(1, dto.getEmailId());
			st.setString(2, dto.getPassToken());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				UserDTO dto1 = new UserDTO();
				dto1.setProfileId(rs.getInt("profile.profile_id"));
				dto1.setFirstName(rs.getString("first_name"));
				dto1.setLastName(rs.getString("last_name"));
				dto1.setContactNo(rs.getString("contact_no"));
				dto1.setAddress(rs.getString("address"));
				dto1.setEmailId(rs.getString("email_id"));
				dto1.setUserId(rs.getInt("login_id"));
				dto1.setUserName(rs.getString("user_name"));
				dto1.setUserPassword(rs.getString("password"));
				userDTOList.add(dto1);
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
					st.close();
					con.close();

				}
			} catch (Exception e) {

			}

		}
		return dbRespons;
	}

}
