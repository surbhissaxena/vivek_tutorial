package com.ss.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ss.dto.UserDTO;
import com.ss.service.UserService;
import com.ss.service.impl.UserServiceImpl;
import com.ss.util.DBResponse;
import com.ss.util.OpCode;

/**
 * Servlet implementation class LoginController
 */

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBResponse dbResponse;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");
		request.setAttribute("login_message", " Invalid Account...");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("login controller");
		UserDTO dto = new UserDTO();
		List<Object> list = new ArrayList<Object>();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println(password);
		dto.setEmailId(email);	
		dto.setUserPassword(password);
		UserService service = new UserServiceImpl();
		try {
			dbResponse = service.authenticateUser(dto);
			System.out.println(dbResponse.getMessage());
			
			System.out.println("roll2"+dto.getRole());
			if (OpCode.SUCCESS == dbResponse.getOperationCode()) {
				if(list!=null){
					dto = (UserDTO) dbResponse.getData().get("UserList").get(0);
				}
				System.out.println(dto);
				 HttpSession session=request.getSession();
				 session.setAttribute("userId", dto.getProfileId());
	                session.setAttribute("fname",dto.getFirstName());
	                session.setAttribute("lname",dto.getLastName());
	                session.setAttribute("username", dto.getUserName());
	                session.setAttribute("email", dto.getEmailId());
					request.setAttribute("username", dto.getFirstName());
				System.out.println(dbResponse.getMessage());
				System.out.println(" system roll "+dto.getRole());
				if (Constant.role_Admin.equals(dto.getRole())) {
					
					RequestDispatcher rd = request.getRequestDispatcher("/jsp/admin/index.jsp");
					rd.forward(request, response);
					//list = dbResponse.getData().get("UserList");
					//UserDTO dto1 = (UserDTO) list.get(0);
					//System.out.println(dto1.getUserName() + " " + dto.getUserPassword());
				} else if ((Constant.role_Operator).equals(dto.getRole())) {
					  System.out.println("i m roll...");
					RequestDispatcher rd = request.getRequestDispatcher("/jsp/wel_operator.jsp");
					rd.forward(request, response);
				
			    } else if (Constant.role_Student.equals(dto.getRole())) {
				System.out.println("system in");
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/wel_student.jsp");
				    rd.forward(request, response);
			  } 
			 }else {
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/index.jsp");
				request.setAttribute("login_message", " Invalid Account...");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
