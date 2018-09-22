package com.ss.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.dto.QuestionDTO;
import com.ss.service.QuestionService;
import com.ss.service.impl.QuestionServiceImpl;
import com.ss.util.DBResponse;
import com.ss.util.Message;
import com.ss.util.OpCode;

/**
 * Servlet implementation class GetAllQuestionController
 */
public class GetAllQuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllQuestionController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBResponse dbResponse = null;
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
		try {
			String subId = request.getParameter("sub_id");	
			QuestionDTO dto = new QuestionDTO();
			dto.setSub_id(Integer.valueOf(subId));
			QuestionService service = new QuestionServiceImpl();
			dbResponse = service.getAllQuestionBySubjectId(dto);
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
