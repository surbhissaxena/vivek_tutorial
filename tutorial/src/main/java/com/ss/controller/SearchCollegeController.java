package com.ss.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ss.dto.CollageDTO;
import com.ss.dto.UserDTO;
import com.ss.service.CollageService;
import com.ss.service.UserService;
import com.ss.service.impl.CollageServiceImpl;
import com.ss.service.impl.UserServiceImpl;
import com.ss.util.DBResponse;
import com.ss.util.OpCode;

/**
 * Servlet implementation class SearchCollegeController
 */
public class SearchCollegeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchCollegeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String collage_Name = request.getParameter("collagename");
		String uni_Name = request.getParameter("univercityname");
		String address = request.getParameter("address");
		// String emailid = request.getParameter("dataSearch");
		// // String usersearch = request.getParameter("usersearch");

		System.out.println(collage_Name);
		System.out.println(uni_Name);
		System.out.println(address);
		// System.out.println(emailid);
		//
		CollageDTO dto = new CollageDTO();

		dto.setClg_Name(collage_Name);
		dto.setClg_Univercity(uni_Name);;
		dto.setClg_Address(address);
		// dto.setEmailId(emailid);
		// dto.setAddress(address);
		DBResponse dbResponse = new DBResponse();
		List<Object> clgDTOList = new ArrayList<Object>();
		CollageService collageservice = new CollageServiceImpl();
		try {
			// dbResponse = userservice.searchUser(dto);
			dbResponse = collageservice.searchCollageByUsingPrePaidStatement(dto);

			if (OpCode.SUCCESS == dbResponse.getOperationCode()) {
				System.out.println(dbResponse.getMessage());
				clgDTOList = dbResponse.getData().get("clgList");
				request.setAttribute("allCollage_list", clgDTOList);
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/show_collage_record.jsp");		
				rd.forward(request, response);			
			} else if (OpCode.FAIL == dbResponse.getOperationCode()) {
				System.out.println(dbResponse.getMessage());
				clgDTOList = dbResponse.getData().get("clgList");
				request.setAttribute("allCollage_list", clgDTOList);
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/show_collage_record.jsp");	
				 rd.forward(request, response);}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
