package com.ss.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

import com.ss.dto.UserDTO;
import com.ss.service.UserService;
import com.ss.service.impl.UserServiceImpl;
import com.ss.util.DBResponse;
import com.ss.util.OpCode;

/**
 * Servlet implementation class verify
 */
public class VerifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VerifyController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("this is verify sevlet..");
		String token = request.getParameter("token");

		System.out.println(token);
		String dStr =new String(Base64.decodeBase64(token));
		System.out.println(dStr);

		String[] TokenArray = dStr.split(",");
		String t1 = TokenArray[0];
		
		System.out.println("t1 =" + t1);
		List<Object> allUserList = new ArrayList<Object>();
		DBResponse dbResponse = new DBResponse();
		UserService service = new UserServiceImpl();
		try {  
			UserDTO dto = new UserDTO();
			dto.setEmailId(t1);
			dto.setPassToken(dStr);
			dbResponse = service.verifyUserForPasswordReset(dto);
			if (OpCode.SUCCESS == dbResponse.getOperationCode()) {
				System.out.println(dbResponse.getMessage());
				allUserList = dbResponse.getData().get("UserList");

				System.out.println("token  match .....");
				request.setAttribute("emailId", t1);
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/reset_password.jsp");
				rd.forward(request, response);

			} else {
				System.out.println(dbResponse.getMessage());

			}

		} catch (Exception e) {

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("verify controller method vi post ");
        
	//	HttpSession session = request.getSession();
	//	session.setMaxInactiveInterval(2*60);
		String email_id = request.getParameter("email_id");
		String newpassword = request.getParameter("newPassword");
		String confirmpassword = request.getParameter("confirmPassword");
	    System.out.println("un="+ email_id);
		System.out.println(newpassword);
		UserDTO dto = new UserDTO();
		dto.setEmailId(email_id);;
		dto.setUserPassword(newpassword);
		
		DBResponse dbResponse = new DBResponse();
		  UserService service =new UserServiceImpl();
		if(newpassword.equals(confirmpassword)) {
			try {
                dbResponse=service.updateUserResetPassword(dto);			
				if(OpCode.SUCCESS==dbResponse.getOperationCode()){
					System.out.println(dbResponse.getMessage());
				}
				else if(OpCode.FAIL==dbResponse.getOperationCode()){
					System.out.println(dbResponse.getMessage());
				}
			} catch (Exception e) {
				e.printStackTrace();
                if(OpCode.EXECPTION==dbResponse.getOperationCode()){
                	System.out.println(dbResponse.getMessage());
                }
			}
			request.setAttribute("message", "succesfully update....");
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/response.jsp");
			rd.forward(request, response);
			
		} else {
			request.setAttribute("message", "password not match");
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/response.jsp");
			rd.forward(request, response);
		}

	}

}
