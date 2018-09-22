package com.ss.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ss.dto.UserDTO;
import com.ss.service.UserService;
import com.ss.service.impl.UserServiceImpl;
import com.ss.util.DBResponse;
import com.ss.util.OpCode;

/**
 * Servlet implementation class UpdateController
 */
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateController() {
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
		String userId = request.getParameter("userId");
		long r = Long.parseLong(userId);
		System.out.println("userId Id = " + r);
		UserDTO dto = new UserDTO();
		dto.setUserId(r);
		DBResponse dbResponse = new DBResponse();
		DBResponse dbResponse2 = new DBResponse();
		dto.setUserId(r);
		UserService service = new UserServiceImpl();
		List<Object> userList = new ArrayList<Object>();
		try {
			dbResponse2 = service.getUserById(dto);
			if (OpCode.SUCCESS == dbResponse2.getOperationCode()) {
				userList = dbResponse2.getData().get("UserList");
				request.setAttribute("allUser_list", userList);
				System.out.println(dbResponse.getMessage());
				request.setAttribute("message", dbResponse2.getMessage());
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/update_user.jsp");
				rd.forward(request, response);
			} else if ((OpCode.FAIL == dbResponse2.getOperationCode())) {
				System.out.println(dbResponse2.getMessage());
				System.out.println(dbResponse.getMessage());
				request.setAttribute("message", dbResponse.getMessage());
				request.setAttribute("allUser_list", userList);
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/show_student_record.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (OpCode.EXECPTION == dbResponse.getOperationCode()) {
				System.out.println(dbResponse.getMessage());
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userid = request.getParameter("id");
		String username = request.getParameter("username");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String contactno = request.getParameter("contactno");
		String address = request.getParameter("address");
		String emailid = request.getParameter("emailid");
		String password = request.getParameter("pass");
		String updatebutton = request.getParameter("updateuser");
		if ("updateuser".equals(updatebutton)) {
			System.out.println(userid);
			System.out.println(username);
			System.out.println(firstname);
			System.out.println(lastname);
			System.out.println(contactno);
			System.out.println(address);
			System.out.println(emailid);
			System.out.println(password);
			UserDTO dto = new UserDTO();

			dto.setProfileId(Long.parseLong(userid));
			dto.setUserName(username);
			dto.setFirstName(firstname);
			dto.setLastName(lastname);
			dto.setContactNo(contactno);
			dto.setEmailId(emailid);
			dto.setAddress(address);
			dto.setUserPassword(password);
			DBResponse dbResponse = new DBResponse();
			DBResponse dbResponse2 = new DBResponse();
			List<Object> userList = new ArrayList<Object>();
			UserService userservice = new UserServiceImpl();
			try {
				dbResponse = userservice.updateUser(dto);
				if (OpCode.SUCCESS == dbResponse.getOperationCode()) {
					System.out.println(dbResponse.getMessage());
					dbResponse2 = userservice.getAllUserData();
					if (OpCode.SUCCESS == dbResponse.getOperationCode()) {
						System.out.println(dbResponse2.getMessage());
						userList = dbResponse2.getData().get("UserList");
						request.setAttribute("allUser_list", userList);
						RequestDispatcher rd = request.getRequestDispatcher("/jsp/show_student_record.jsp");
						rd.forward(request, response);

					} else if (OpCode.FAIL == dbResponse.getOperationCode()) {
						System.out.println(dbResponse.getMessage());
						request.setAttribute("allUser_list", userList);
						RequestDispatcher rd = request.getRequestDispatcher("/jsp/show_student_record.jsp");
						rd.forward(request, response);
					}
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
}
