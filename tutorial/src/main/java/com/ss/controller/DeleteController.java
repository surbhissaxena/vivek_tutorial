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
 * Servlet implementation class DeleteController
 */
//@WebServlet("/DeleteController")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteController() {
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
		System.out.println("delete userId....");
		String userId = request.getParameter("userId");
		int r = Integer.parseInt(userId);
		System.out.println("userId Id = " + r);
		DBResponse dbResponse = new DBResponse();
		DBResponse dbResponse2 = new DBResponse();
		UserDTO dto = new UserDTO();
		dto.setUserId(r);
		UserService service = new UserServiceImpl();
		List<Object> userList = new ArrayList<Object>();
		try {
			dbResponse = service.deleteUserById(dto);
			if (OpCode.SUCCESS == dbResponse.getOperationCode()) {
			   System.out.println(dbResponse.getMessage());
				dbResponse2 = service.getAllUserData();
				if(OpCode.SUCCESS == dbResponse2.getOperationCode()){
					System.out.println(dbResponse.getMessage());
					userList = dbResponse2.getData().get("UserList");
					request.setAttribute("allUser_list", userList);
					//request.setAttribute("message", dbResponse2.getMessage());
					RequestDispatcher rd = request.getRequestDispatcher("/jsp/show_student_record.jsp");
					rd.forward(request, response);
				}else if((OpCode.FAIL == dbResponse2.getOperationCode())){
					System.out.println(dbResponse2.getMessage());
					System.out.println(dbResponse.getMessage());
					request.setAttribute("message", dbResponse.getMessage());
					request.setAttribute("allUser_list", userList);
					RequestDispatcher rd = request.getRequestDispatcher("/jsp/show_student_record.jsp");
					rd.forward(request, response);
				}
				
			} else if (OpCode.FAIL == dbResponse.getOperationCode()) {
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
		System.out.println("delete userId....");
		String userId = request.getParameter("userId");
		int r = Integer.parseInt(userId);
		System.out.println("userId Id = " + r);
		DBResponse dbResponse = new DBResponse();
		DBResponse dbResponse2 = new DBResponse();
		UserDTO dto = new UserDTO();
		dto.setUserId(r);
		UserService service = new UserServiceImpl();
		List<Object> userList = new ArrayList<Object>();
		try {
			dbResponse = service.deleteUserById(dto);
			if (OpCode.SUCCESS == dbResponse.getOperationCode()) {
				dbResponse2 = service.getAllUserData();
				if(OpCode.SUCCESS == dbResponse2.getOperationCode()){
					userList = dbResponse2.getData().get(0);
					request.setAttribute("allUser_list", userList);
					System.out.println(dbResponse.getMessage());
					request.setAttribute("message", dbResponse2.getMessage());
					RequestDispatcher rd = request.getRequestDispatcher("show_student_record.jsp");
					rd.forward(request, response);
				}else if((OpCode.FAIL == dbResponse2.getOperationCode())){
					System.out.println(dbResponse2.getMessage());

				}
				
			} else if (OpCode.FAIL == dbResponse.getOperationCode()) {
				System.out.println(dbResponse.getMessage());
				//request.setAttribute("message", dbResponse.getMessage());
				//RequestDispatcher rd = request.getRequestDispatcher("show_student_record.jsp");
				//rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (OpCode.EXECPTION == dbResponse.getOperationCode()) {
				System.out.println(dbResponse.getMessage());
			}
		}

	}

}
