package com.ss.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.service.SubjectService;
import com.ss.service.impl.SubjectServiceImpl;
import com.ss.util.DBResponse;
import com.ss.util.Message;
import com.ss.util.OpCode;

/**
 * Servlet implementation class GetAllSubjectController
 */
public class GetAllSubjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllSubjectController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        DBResponse dbResponse = null;
        try {
        	SubjectService service = new SubjectServiceImpl();
			dbResponse = service.getAllsubject();        	
		} catch (Exception e) {
			if(dbResponse==null){
				dbResponse = new DBResponse();
			}
			dbResponse.setOperationCode(OpCode.EXECPTION);
			dbResponse.setDataAvailable(false);
			dbResponse.setMessage(Message.SOMETHING_WENT_WRONG);
			e.printStackTrace();
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonStr = mapper.writeValueAsString(dbResponse); 
		out.print(jsonStr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
