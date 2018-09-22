package com.ss.service;

import com.ss.dto.UserDTO;
import com.ss.util.DBResponse;

public interface UserService {

	DBResponse getAllUserData() throws Exception;

	DBResponse registerUser(UserDTO dto) throws Exception;

	DBResponse getUserById(UserDTO dto) throws Exception;
	
	DBResponse getUserByEmail(UserDTO dto) throws Exception;
	
	DBResponse verifyUserForPasswordReset(UserDTO dto) throws Exception;

	DBResponse getUserByName(UserDTO dto) throws Exception;

	DBResponse deleteUserById(UserDTO dto) throws Exception;

	DBResponse updateUser(UserDTO dto) throws Exception;

	DBResponse getUserByUserName(UserDTO dto) throws Exception;

	DBResponse searchUser(UserDTO dto) throws Exception;

	DBResponse authenticateUser(UserDTO dto) throws Exception;

	DBResponse searchUserByUsingPrePaidStatement(UserDTO dto) throws Exception;

	DBResponse updateUserPasswordToken(UserDTO dto) throws Exception;

	DBResponse updateUserResetPassword(UserDTO dto) throws Exception;

	DBResponse getUserRecordByPagination(int start, int total) throws Exception;

	DBResponse userResetPassword(UserDTO dto) throws Exception;

	DBResponse getAllOperator() throws Exception;

	DBResponse getAllStudent() throws Exception;
	
	DBResponse aboutMe(UserDTO dto ) throws Exception;}
