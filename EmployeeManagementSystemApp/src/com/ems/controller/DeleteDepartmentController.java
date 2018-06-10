package com.ems.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ems.model.Department;
import com.ems.service.BaseService;
import com.ems.service.BaseServiceImpl;
import com.ems.util.Constants;
import com.session.SessionManagement;

/**
 * Servlet implementation class DeleteDepartmentController
 */
@WebServlet("/DeleteDepartmentController")
public class DeleteDepartmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(DeleteDepartmentController.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteDepartmentController() {
		super();
 
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}  
  
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*Get the required data from the request*/
   		String departmentName = request.getParameter(Constants.DEPARTMENT);

		logger.info(departmentName); 

		String page = "adminHomePage.jsp";
		
		/* if data are not null and have size, perform transaction DB to delete the department*/
		if(departmentName.trim().length() >= 0 && departmentName != null) {

			BaseService deleteDeptService = new BaseServiceImpl();

			Department dept = deleteDeptService.findIdByDepartmentName(departmentName); 
			
			/*Set the department object*/
			dept.setDeptName(departmentName);
			
			dept.setHeadOfDept(dept.getHeadOfDept());
			
			dept.setId(dept.getId());

			boolean flag = deleteDeptService.deleteDepartment(dept);

			if(flag) {

				logger.info("Department " +departmentName +" Deleted");

				request.setAttribute("success", "Department "+departmentName+" Deleted Successfully!");  

			} else {
				
				/* if there is any error during DB transaction send an attribute to the JSP page*/
				request.setAttribute("nonSuccess", "Department was not deleted, Try again!!!");
			}

		} else {

			request.setAttribute("nonSuccess", "Please select department name ");
		}


		request.getRequestDispatcher(page).include(request, response);
	}

	/*This method gets all the dpearmtents from DB and then the list is used in deleteDepartment.jsp*/
	public static List<String> getAllDepartmentsFromDB() {

		List<String> departments = new ArrayList<String>();

		BaseService deleteDeptService = new BaseServiceImpl();

		departments = deleteDeptService.getAllDepartments("deptname");

		return departments;


	}

}
