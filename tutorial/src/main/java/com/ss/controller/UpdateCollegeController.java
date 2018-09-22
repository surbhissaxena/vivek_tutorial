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
import com.ss.service.CollageService;
import com.ss.service.impl.CollageServiceImpl;
import com.ss.util.DBResponse;
import com.ss.util.OpCode;

/**
 * Servlet implementation class UpdateCollegeController
 */
public class UpdateCollegeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCollegeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String collageId = request.getParameter("collageId");
		int r = Integer.parseInt(collageId);
		System.out.println("collageId Id = " + r);
		CollageDTO dto = new CollageDTO();
		dto.setClg_Id(r);
		DBResponse dbResponse = new DBResponse();
		DBResponse dbResponse2 = new DBResponse();
		CollageService service = new CollageServiceImpl();
		List<Object> collageList = new ArrayList<Object>();
		try {
			dbResponse2 = service.getCollageById(dto);
			if (OpCode.SUCCESS == dbResponse2.getOperationCode()) {
				collageList = dbResponse2.getData().get("clgList");
				request.setAttribute("allCollage_list", collageList);
				System.out.println(dbResponse.getMessage());
				request.setAttribute("message", dbResponse2.getMessage());
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/update_collage_data.jsp");
				rd.forward(request, response);
			} else if ((OpCode.FAIL == dbResponse2.getOperationCode())) {
				System.out.println(dbResponse2.getMessage());
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
        System.out.println("update collge controller....");
		 //String clg_Id = request.getParameter("clgId");
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
         String updatebutton=request.getParameter("updatecollage");
         System.out.println("colllage id =="+clg_Id);
        if ("updatecollage".equals(updatebutton)) {
			
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
              
			DBResponse dbResponse = new DBResponse();
			DBResponse dbResponse2 = new DBResponse();
			List<Object> collageList = new ArrayList<Object>();
			CollageService userservice = new CollageServiceImpl();
			try {
				dbResponse = userservice.updateCollageById(dto);
				if (OpCode.SUCCESS == dbResponse.getOperationCode()) {
					System.out.println(dbResponse.getMessage());
					dbResponse2 = userservice.getAllCollageData();
					if (OpCode.SUCCESS == dbResponse.getOperationCode()) {
						System.out.println(dbResponse2.getMessage());
						collageList = dbResponse2.getData().get("clgList");
						request.setAttribute("allCollage_list", collageList);
						RequestDispatcher rd = request.getRequestDispatcher("/jsp/show_collage_record.jsp");
						rd.forward(request, response);

					} else if (OpCode.FAIL == dbResponse.getOperationCode()) {
						System.out.println(dbResponse.getMessage());
						request.setAttribute("allCollage_list", collageList);
						RequestDispatcher rd = request.getRequestDispatcher("/jsp/show_collage_record.jsp");
						rd.forward(request, response);
					}
				} else if (OpCode.FAIL == dbResponse.getOperationCode()) {
					System.out.println(dbResponse.getMessage());
					request.setAttribute("allCollage_list", collageList);
					RequestDispatcher rd = request.getRequestDispatcher("/jsp/show_collage_record.jsp");
					rd.forward(request, response);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}


	}

}
