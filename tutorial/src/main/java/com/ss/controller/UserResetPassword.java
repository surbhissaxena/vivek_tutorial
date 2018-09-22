package com.ss.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ss.dto.UserDTO;
import com.ss.service.UserService;
import com.ss.service.impl.UserServiceImpl;
import com.ss.util.DBResponse;
import com.ss.util.OpCode;

/**
 * Servlet implementation class UserResetPassword
 */
public class UserResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserResetPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   RequestDispatcher rd=request.getRequestDispatcher("/jsp/change_password_form.jsp");
   rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("UserResetPassword");
        DBResponse dbResponse=new DBResponse();
        List<Object> userDTOLIst =new  ArrayList<Object>();
         UserService service= new UserServiceImpl();
        String oldPassword =request.getParameter("OldPassword");
        String newPassword =request.getParameter("newpassword");
        String confirmPassword =request.getParameter("conpassword");
        
        System.out.println(oldPassword+" "+newPassword+" "+ confirmPassword);
        
        try {dbResponse=service.getAllUserData();
            if(OpCode.SUCCESS==dbResponse.getOperationCode()){
            	System.out.println(dbResponse.getMessage());
              userDTOLIst=dbResponse.getData().get("UserList");
              Iterator<Object> it=userDTOLIst.iterator();
              
              while(it.hasNext()){
            	  
            	  UserDTO dto=(UserDTO)it.next();
            	  String username=dto.getUserName();
            	  if(dto.getUserPassword().equals(oldPassword)){
            		System.out.println("old password mamtch = "+oldPassword);
                    System.out.println("user name= "+dto.getUserName());
                   
                    UserDTO dto1=new UserDTO();
              	   dto1.setUserName(username);
              	   dto1.setUserPassword(newPassword);
                 try {dbResponse=service.userResetPassword(dto1);
  				      if(OpCode.SUCCESS==dbResponse.getOperationCode()){
  				    	  System.out.println(dbResponse.getMessage());
  				        request.setAttribute("username", username);
  				   RequestDispatcher rd=request.getRequestDispatcher("/jsp/welcome.jsp");
  				   rd.forward(request, response);
  				      }else if(OpCode.FAIL==dbResponse.getOperationCode()){
  				    	  System.out.println(dbResponse.getMessage());
  				      }
  			     } catch (Exception e) {
                      if(OpCode.EXECPTION==dbResponse.getOperationCode()){
                    	  System.out.println(dbResponse.getMessage());
                      }
  			      }
                    
            	}  
              }
              
            }else if(OpCode.FAIL==dbResponse.getOperationCode()){
            	System.out.println(dbResponse.getMessage());
            }
			
		} catch (Exception e) {
			if(OpCode.EXECPTION==dbResponse.getOperationCode()){
          	  System.out.println(dbResponse.getMessage());
            }	
			}
        
	}

}
