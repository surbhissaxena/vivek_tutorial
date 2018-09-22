package com.ss.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.dto.SubjectDTO;
import com.ss.service.SubjectService;
import com.ss.service.impl.SubjectServiceImpl;
import com.ss.util.DBResponse;
import com.ss.util.Message;
import com.ss.util.OpCode;

/**
 * Servlet implementation class AddSubjectController
 */
public class AddSubjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddSubjectController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBResponse dbResponse = null;
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
		try {
			String subName = request.getParameter("sub_name");	
			SubjectDTO  dto = new SubjectDTO();
			dto.setSub_name(subName);
			SubjectService service = new SubjectServiceImpl();
			dbResponse = service.addSubject(dto);
						
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

}
