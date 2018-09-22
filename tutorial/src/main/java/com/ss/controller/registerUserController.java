package com.ss.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.dto.UserDTO;
import com.ss.service.UserService;
import com.ss.service.impl.UserServiceImpl;
//import com.ns.util.MailController;
import com.ss.util.DBResponse;
import com.ss.util.MailController;
import com.ss.util.Message;
import com.ss.util.OpCode;

/**
 * Servlet implementation class AddDataController
 */
public class registerUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerUserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		 TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/register.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Add User Data..");   
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    PrintWriter out = response.getWriter();
		String userName=request.getParameter("username");
        String firstrName=request.getParameter("firstname");
        String lastName=request.getParameter("lastname");
        String conactNo=request.getParameter("contactno");
        String emailId=request.getParameter("email");
        String  address=request.getParameter("address");
        String  role=request.getParameter("role");
     
        UserDTO dto=new  UserDTO();
        dto.setUserName(userName);
        dto.setFirstName(firstrName);
        dto.setLastName(lastName);
        dto.setContactNo(conactNo);
        dto.setAddress(address);
        dto.setEmailId(emailId);
        dto.setRole(role);
        System.out.println(dto);
       
        UserService service =new UserServiceImpl(); 
        DBResponse dbResponse=new DBResponse();
        try{ 
             dbResponse=service.registerUser(dto);
             try {
//            	 MailController.mailMethod(dto);
             }catch (Exception e) {
            	 e.printStackTrace();
             }
			 
        }catch(Exception e){
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
