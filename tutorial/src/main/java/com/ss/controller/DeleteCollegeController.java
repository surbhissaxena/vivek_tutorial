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
 * Servlet implementation class DeleteCollegeController
 */
public class DeleteCollegeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCollegeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("delete collage Id....");
		String collageId = request.getParameter("collageId");
		int r = Integer.parseInt(collageId);
		System.out.println("collageId Id = " + r);
		DBResponse dbResponse = new DBResponse();
		DBResponse dbResponse2 = new DBResponse();
		CollageDTO dto = new CollageDTO();
		dto.setClg_Id(r);
		CollageService service = new CollageServiceImpl();
		List<Object> collageList = new ArrayList<Object>();
		try {
			dbResponse = service.deleteCollageById(dto);
			if (OpCode.SUCCESS == dbResponse.getOperationCode()) {
			   System.out.println(dbResponse.getMessage());
				dbResponse2 = service.getAllCollageData();
				if(OpCode.SUCCESS == dbResponse2.getOperationCode()){
					System.out.println(dbResponse.getMessage());
					collageList = dbResponse2.getData().get("clgList");
					request.setAttribute("allCollage_list", collageList);
					//request.setAttribute("message", dbResponse2.getMessage());
					RequestDispatcher rd = request.getRequestDispatcher("/jsp/show_collage_record.jsp");
					rd.forward(request, response);
				}else if((OpCode.FAIL == dbResponse2.getOperationCode())){
					System.out.println(dbResponse2.getMessage());
					System.out.println(dbResponse.getMessage());
					request.setAttribute("message", dbResponse.getMessage());
					request.setAttribute("allCollage_list", collageList);
					RequestDispatcher rd = request.getRequestDispatcher("/jsp/show_collage_record.jsp");
					rd.forward(request, response);
				}
				
			} else if (OpCode.FAIL == dbResponse.getOperationCode()) {
				System.out.println(dbResponse.getMessage());
				request.setAttribute("message", dbResponse.getMessage());
				request.setAttribute("allCollage_list", collageList);
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/show_collage_record.jsp");
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
