package com.ems.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ems.model.Department;
import com.ems.service.BaseService;
import com.ems.service.BaseServiceImpl;
import com.ems.util.Constants;
import com.session.SessionManagement;

/**
 * Servlet implementation class AddDepartmentController
 */
@WebServlet("/AddDepartmentController")
public class AddDepartmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AddDepartmentController.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddDepartmentController() {
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
		//doGet(request, response);

		/*Get department name and department head from the request*/
		String departmentName = request.getParameter(Constants.DEPARTMENT);

		String departmentHead = request.getParameter(Constants.DEPARTMENTHEAD);

		logger.info(departmentName + " :: " + departmentHead);

		String page = "addDepartment.jsp";

		/* if these two strings are not null and have a size add them to the dept object and perform the creation transaction
		 * in the database*/
		if(departmentName.trim().length() >= 0 && departmentName != null &&
				departmentHead.trim().length() >= 0 && departmentHead != null) {

			BaseService createDeptService = new BaseServiceImpl();

			Department dept = new Department();

			dept.setDeptName(departmentName.trim());
			dept.setHeadOfDept(departmentHead.trim());

			boolean flag = createDeptService.createDepartment(dept);

			if(flag) {

				logger.info("Department Created");

				request.setAttribute("department", departmentName);

				request.setAttribute("success", "Department Created Successfully!");

				page = "adminHomePage.jsp";   

			} else {
				/*if there is an error during the db transaction send an attribute to be send on the JSP page*/
				request.setAttribute("nonSuccess", "Wrong department name or department head, Try again!!!");
			}

		} else {

			request.setAttribute("nonSuccess", "Please enter department name and head...");
		}



		request.getRequestDispatcher(page).include(request, response);
	}



}
