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
 * Servlet implementation class GetAllStudentController
 */
public class GetAllStudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllStudentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GetAllStudentController");
		// TODO Auto-generated method stub
		List<Object> userDTOList = new ArrayList<Object>();
		DBResponse dbResponse = new DBResponse();
		UserService service = new UserServiceImpl();
		try {
			dbResponse = service.getAllStudent();
			if (OpCode.SUCCESS == dbResponse.getOperationCode()) {
				System.out.println(dbResponse.getMessage());
				userDTOList = dbResponse.getData().get("UserList");

						request.setAttribute("allUser_list", userDTOList);
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/get_all_student_record.jsp");
				rd.forward(request, response);
			} else if (OpCode.FAIL == dbResponse.getOperationCode()) {
				System.out.println(dbResponse.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
