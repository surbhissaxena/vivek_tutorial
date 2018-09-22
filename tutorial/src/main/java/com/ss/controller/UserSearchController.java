package com.ss.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ss.dao.UserDAO;
import com.ss.dao.impl.UserDAOImpl;
import com.ss.dto.UserDTO;
import com.ss.service.UserService;
import com.ss.service.impl.UserServiceImpl;
import com.ss.util.DBResponse;
import com.ss.util.OpCode;

/**
 * Servlet implementation class UserSearchController
 */
public class UserSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserSearchController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String address = request.getParameter("address");
		// String emailid = request.getParameter("dataSearch");
		// // String usersearch = request.getParameter("usersearch");

		System.out.println(username);
		System.out.println(firstname);
		System.out.println(lastname);
		System.out.println(address);
		// System.out.println(emailid);
		//
		UserDTO dto = new UserDTO();

		dto.setUserName(username);
		dto.setFirstName(firstname);
		dto.setLastName(lastname);
		// dto.setEmailId(emailid);
		// dto.setAddress(address);
		DBResponse dbResponse = new DBResponse();
		List<Object> userList = new ArrayList<Object>();
		UserService userservice = new UserServiceImpl();
		try {
			// dbResponse = userservice.searchUser(dto);
			dbResponse = userservice.searchUserByUsingPrePaidStatement(dto);

			if (OpCode.SUCCESS == dbResponse.getOperationCode()) {
				System.out.println(dbResponse.getMessage());
				userList = dbResponse.getData().get("UserList");
				request.setAttribute("allUser_list", userList);
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/show_student_record.jsp");
				rd.forward(request, response);
			} else if (OpCode.FAIL == dbResponse.getOperationCode()) {
				System.out.println(dbResponse.getMessage());
				request.setAttribute("allUser_list", userList);
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/show_student_record.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
