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
import com.ss.service.impl.CollageServiceImpl;
import com.ss.util.DBResponse;
import com.ss.util.OpCode;

/**
 * Servlet implementation class GetCollegeByPercentage
 */
public class GetCollegeByPercentage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCollegeByPercentage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     
		System.out.println("GetCollegeByPercentage");
		List<Object> clgDTOList = new ArrayList<Object>();
		DBResponse dbResponse = new DBResponse();
		String cutOff=request.getParameter("cutoff");
		System.out.println("Cuttoff "+cutOff);
		CollageService service = new CollageServiceImpl();
		CollageDTO  dto=new CollageDTO();
		      dto.setClg_Cutoff(cutOff);
		try {
			dbResponse = service.getCollageByPercentage(dto);
			if (OpCode.SUCCESS == dbResponse.getOperationCode()) {
				System.out.println(dbResponse.getMessage());
				clgDTOList = dbResponse.getData().get("clgList");
				request.setAttribute("allCollage_list", clgDTOList);
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/show_collage_record.jsp");
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
