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
 * Servlet implementation class ShowStuRecordController
 */
public class ShowStuRecordController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowStuRecordController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("get all student get metod");
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		List<Object> userDTOList = new ArrayList<Object>();
		DBResponse dbResponse = new DBResponse();
		UserService service = new UserServiceImpl();
		UserDTO dto = new UserDTO();
		try {
			dbResponse = service.getAllUserData();
			if (OpCode.SUCCESS == dbResponse.getOperationCode()) {
				System.out.println(dbResponse.getMessage());
				userDTOList = dbResponse.getData().get("UserList");

				/*Iterator<Object> it = userDTOList.iterator();

				System.out.println("USERID\t\tFIRSTNAME\t\tLASTNAME\t\tCONTACTNO\t\tADDRESS\t\tEMAIL");
				while (it.hasNext()) {
					UserDTO dtoo = (UserDTO) it.next();
					System.out.println(dtoo.getUserId() + "\t\t" + dtoo.getFirstName() + "\t\t\t" + dtoo.getLastName()
							+ "\t\t\t" + dtoo.getContectNo() + "\t\t" + dtoo.getAddress() + "\t\t" + dtoo.getEmailId());
				}
*/				request.setAttribute("allUser_list", userDTOList);
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/show_student_record.jsp");
				rd.forward(request, response);
			} else if (OpCode.FAIL == dbResponse.getOperationCode()) {
				System.out.println(dbResponse.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 * response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
