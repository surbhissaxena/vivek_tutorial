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

import com.ss.dto.UserDTO;
import com.ss.service.UserService;
import com.ss.service.impl.UserServiceImpl;
import com.ss.util.DBResponse;
import com.ss.util.OpCode;

/**
 * Servlet implementation class PaginationController
 */
public class PaginationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaginationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("pagination controller by do get");
		String spageid=request.getParameter("page");  
		  System.out.println("page id ="+spageid);
		     int pageid=Integer.parseInt(spageid);  
			 int total=pageid;
			 int start=1;
		List<Object> userDTOList = new ArrayList<Object>();
		DBResponse dbResponse = new DBResponse();
		UserService service = new UserServiceImpl();
		UserDTO dto = new UserDTO();
		System.out.println("value 1 = "+start +","+total);
		try {
			dbResponse = service.getUserRecordByPagination(start, total);
			if (OpCode.SUCCESS == dbResponse.getOperationCode()) {
				System.out.println("value 2 = "+start +","+total);		
				System.out.println(dbResponse.getMessage());
				userDTOList = dbResponse.getData().get("UserList");
				
				
				request.setAttribute("allUser_list", userDTOList);
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/view.jsp");
				rd.forward(request, response);
			} else if (OpCode.FAIL == dbResponse.getOperationCode()) {
				System.out.println(dbResponse.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 System.out.println("pagination controller by do post");
	 
	 }
}
