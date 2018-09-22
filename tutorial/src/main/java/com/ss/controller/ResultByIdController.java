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

import com.ss.dto.ResultDTO;
import com.ss.dto.UserDTO;
import com.ss.service.ResultService;
import com.ss.service.UserService;
import com.ss.service.impl.ResultServiveImpl;
import com.ss.service.impl.UserServiceImpl;
import com.ss.util.DBResponse;
import com.ss.util.OpCode;

/**
 * Servlet implementation class ResultByIdController
 */
public class ResultByIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResultByIdController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          
        
		System.out.println("ResultByIdController....");
        String userName=(String)request.getSession(false).getAttribute("username");
        System.out.println("username = "+userName);
		DBResponse dbResponse = new DBResponse();
		ResultDTO dto = new ResultDTO();
		dto.setName(userName);
		ResultService service = new ResultServiveImpl();
		List<Object> resultList = new ArrayList<Object>();
		try {
			dbResponse = service.resultById(dto);
			if (OpCode.SUCCESS == dbResponse.getOperationCode()) {
			   System.out.println(dbResponse.getMessage());
					resultList = dbResponse.getData().get("resultList");
					request.setAttribute("allResult_list", resultList);
					RequestDispatcher rd = request.getRequestDispatcher("/jsp/view_result_by_id.jsp");
					rd.forward(request, response);
				}
			 else if (OpCode.FAIL == dbResponse.getOperationCode()) {
				System.out.println(dbResponse.getMessage());
				request.setAttribute("message", dbResponse.getMessage());
				request.setAttribute("allResult_list", resultList);
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/view_result_by_id.jsp");
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
