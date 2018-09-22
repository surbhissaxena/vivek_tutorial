package com.ss.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

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
import com.ss.util.MailController;
import com.ss.util.OpCode;

/**
 * Servlet implementation class ForgotPasswordController
 */
public class ForgotPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ForgotPasswordController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/forgot-password.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("check id Controller");
		String emailId = request.getParameter("email");
		System.out.println(emailId);
		UserService service = new UserServiceImpl();
		Random r = new Random();
		int val = r.nextInt(1000000000);
		System.out.println(val);
		Date d = new Date();
		List<Object> list = new ArrayList<Object>();
		DBResponse dbResponse = new DBResponse();
		try {
			UserDTO  dto = new UserDTO();
			dto.setEmailId(emailId);
			dbResponse = service.getUserByEmail(dto);
			if (OpCode.SUCCESS == dbResponse.getOperationCode()) {
				System.out.println(dbResponse.getMessage());
				list = dbResponse.getData().get("UserList");

				System.out.println(dto.getEmailId());
				String token = dto.getEmailId() + "," + val + "," + d.getTime();
				String encodedToken = Base64.encodeBase64String(token.getBytes());
				System.out.println(emailId);
				UserDTO dto1 = new UserDTO();
				dto1.setEmailId(emailId);
				dto1.setPassToken(token);
				dbResponse = service.updateUserPasswordToken(dto1);
			
				if (OpCode.SUCCESS == dbResponse.getOperationCode()) {
					String link = "http://localhost:8080/tutorial/verify?token=" + encodedToken;
					dto1.setLink(link);
					MailController.sendMailForFogotPassword(dto1);
					request.setAttribute("message", "please check your mail...");
				}else{
					request.setAttribute("message", "please try again");

				}
				System.out.println(token);
				
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/forgot-password.jsp");
				rd.forward(request, response);
					
			} else {
				System.out.println(dbResponse.getMessage());
				request.setAttribute("message", "please try again");
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/forgot-password.jsp");
				rd.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (OpCode.EXECPTION == dbResponse.getOperationCode()) {
				request.setAttribute("message", "please try again");
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/forgot-password.jsp");
				rd.forward(request, response);
				System.out.println(dbResponse.getMessage());
			}
		}
	}

}
