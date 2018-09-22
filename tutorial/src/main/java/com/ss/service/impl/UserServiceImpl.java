package com.ss.service.impl;


import com.ss.dao.UserDAO;
import com.ss.dao.impl.UserDAOImpl;
import com.ss.dto.UserDTO;
import com.ss.service.UserService;
import com.ss.util.DBResponse;
import com.ss.util.PasswordGenerator;

public class UserServiceImpl implements UserService {

	UserDAO userDAO = new UserDAOImpl();

	public DBResponse getAllUserData() throws Exception {
		return userDAO.getAllUserData();
	}

	public DBResponse registerUser(UserDTO dto) throws Exception {
		try {
			dto.setUserPassword(PasswordGenerator.generateRandomPassword());
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
		return userDAO.addUser(dto);
	}

	public DBResponse getUserById(UserDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.getUserById(dto);
	}

	public DBResponse getUserByName(UserDTO dto) throws Exception {
		return userDAO.getUserByName(dto);
	}

	public DBResponse deleteUserById(UserDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.deleteUserById(dto);
	}

	public DBResponse updateUser(UserDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.updateUser(dto);
	}
	public DBResponse authenticateUser(UserDTO dto) throws Exception {
		DBResponse dbResponse = new DBResponse();
		try {
			dbResponse = userDAO.getUserByEmailAndPassword(dto);
			} catch (Exception e) {
			e.printStackTrace();
		}
		return dbResponse;
	}

	public DBResponse getUserByUserName(UserDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.getUserByUserName(dto);
	}

	
	public DBResponse searchUser(UserDTO dto) throws Exception {

		return userDAO.searchUser(dto);
	}

	
	public DBResponse searchUserByUsingPrePaidStatement(UserDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.searchUserByUsingPrePaidStatement(dto);
	}

	@Override
	public DBResponse updateUserPasswordToken(UserDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return  userDAO.updateUserPasswordToken(dto);
	}

	@Override
	public DBResponse updateUserResetPassword(UserDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.updateUserResetPassword(dto);
	}

	@Override
	public DBResponse getUserRecordByPagination(int start, int total) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.getUserRecordByPagination(start, total);
	}

	@Override
	public DBResponse userResetPassword(UserDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.userResetPassword(dto);
	}

	@Override
	public DBResponse getAllOperator() throws Exception {
		// TODO Auto-generated method stub
		return userDAO.getAllOperator();
	}

	@Override
	public DBResponse getAllStudent() throws Exception {
		// TODO Auto-generated method stub
		return userDAO.getAllStudent();
	}

	@Override
	public DBResponse aboutMe(UserDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.aboutMe(dto);
	}

	@Override
	public DBResponse getUserByEmail(UserDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.getUserByEmail(dto);
	}

	@Override
	public DBResponse verifyUserForPasswordReset(UserDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.getUserByEmailAndToken(dto);
	}

		

}
