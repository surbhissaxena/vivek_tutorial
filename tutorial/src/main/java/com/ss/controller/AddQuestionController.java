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
 * Servlet implementation class AddQuestionController
 */
public class AddQuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddQuestionController() {
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
			QuestionDTO dto = new QuestionDTO();
			String sub_id = request.getParameter("sub_id");
			String option_a = request.getParameter("option_a");
			String option_b = request.getParameter("option_b");
			String option_c = request.getParameter("option_c");
			String option_d = request.getParameter("option_d");
			String answer = request.getParameter("answer");
			String description = request.getParameter("description");	
			if(sub_id!=null){
				dto.setSub_id(Integer.valueOf(sub_id));	
			}
			dto.setOption_a(option_a);
			dto.setOption_b(option_b);
			dto.setOption_c(option_c);
			dto.setOption_d(option_d);
			dto.setAnswer(answer);
			dto.setDescription(description);
			QuestionService service = new QuestionServiceImpl();
			dbResponse = service.addQuestion(dto);
						
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
