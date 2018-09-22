package com.ss.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ss.dto.CollageDTO;
import com.ss.service.CollageService;
import com.ss.service.impl.CollageServiceImpl;
import com.ss.util.DBResponse;
import com.ss.util.OpCode;

/**
 * Servlet implementation class AddCollegeDataController
 */
public class AddCollegeDataController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCollegeDataController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/collage_data_register.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            System.out.println("AddCollageDataControlller");
       
           int clg_Id=Integer.parseInt(request.getParameter("clgId"));
           String clg_Name=request.getParameter("clgName");
           String clg_Univercity=request.getParameter("clgUnivercity");
           String clg_Address=request.getParameter("address");
           String clg_Fees=request.getParameter("fees");
           String clg_Branch=request.getParameter("branch");
           String clg_Contact=request.getParameter("contact");
           String clg_Degree=request.getParameter("roleOption");
           String clg_Document=request.getParameter("document");
           String clg_Course_Details =request.getParameter("coursedetails");
           String clg_Cutoff=request.getParameter("cutoff");
           System.out.println("contact =="+clg_Contact);
           CollageDTO dto=new CollageDTO();
           
           dto.setClg_Id(clg_Id);
           dto.setClg_Name(clg_Name);
           dto.setClg_Univercity(clg_Univercity);
           dto.setClg_Address(clg_Address);
           dto.setClg_Fee(clg_Fees);
           dto.setClg_Branch(clg_Branch);
           dto.setClg_Contact(clg_Contact);
           dto.setClg_degree(clg_Degree);
           dto.setClg_Docoment(clg_Document);
           dto.setClg_cource_declaration(clg_Course_Details);
           dto.setClg_Cutoff(clg_Cutoff);
           
           CollageService  collageService=new CollageServiceImpl();
           DBResponse dbResponse=new DBResponse();
          try { dbResponse=collageService.addCollageData(dto);
        	    if(OpCode.SUCCESS==dbResponse.getOperationCode()){
        	    	System.out.println(dbResponse.getMessage());
        	    	
        	    	request.setAttribute("message", dbResponse.getMessage());
        	    	RequestDispatcher rd=request.getRequestDispatcher("/jsp/response.jsp");
        	    	rd.forward(request, response);
        	    }else if(OpCode.SUCCESS==dbResponse.getOperationCode()){
        	    	System.out.println(dbResponse.getMessage());
        	    	
        	    	request.setAttribute("message", dbResponse.getMessage());
        	    	RequestDispatcher rd=request.getRequestDispatcher("/jsp/response.jsp");
        	    	rd.forward(request, response);
        	    }
			
		} catch (Exception e) {
			e.printStackTrace();
        	if(OpCode.EXECPTION==dbResponse.getOperationCode()){
        		System.out.println(dbResponse.getMessage());
        	}		}           
	}
	

}
