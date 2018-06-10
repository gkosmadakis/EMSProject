package com.ems.controller;

import java.io.IOException;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.ems.service.BaseService;
import com.ems.service.BaseServiceImpl;
import com.ems.util.Constants;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(LoginController.class);   



	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.sendRedirect("index.jsp");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//doGet(request, response);

		HttpSession session = request.getSession(true);

		/*Get the required data from the request*/
		String username = request.getParameter(Constants.USERNAME);

		String password = request.getParameter(Constants.PASSWORD);

		Long employeeId = null;

		BaseService loginService = null;

		logger.info(username + " :: " + password);

		String page = "index.jsp";

		/* if username/password are not null and have size, perform transaction DB to login the user*/
		if(username.trim().length() >= 0 && username != null &&
				password.trim().length() >= 0 && password != null) {

			loginService = new BaseServiceImpl();

			employeeId = (Long) request.getAttribute("id");

			boolean flag = loginService.login(employeeId, password);

			/*Get the role and department of the user from the RoleFilter*/

			String role = (String) request.getAttribute("role");

			String department = (String) request.getAttribute("department");

			if(flag) {

				logger.info("Login success! User logged in Successfully");

				request.setAttribute("username", username);

				/*Store the role the username the department name and the id in session level and use it across the app*/

				session.setAttribute("role", formatRole(role));

				session.setAttribute("username", username);

				session.setAttribute("department", department);

				session.setAttribute("employeeId", employeeId);

				request.setAttribute("msg", "Login Success.....");

				page = (String) request.getAttribute("homePage");

			} else {

				/* if there is any error during DB transaction send an attribute to the JSP page*/
				logger.log(Level.ERROR, "Wrong username or password");

				request.setAttribute("nonSuccess", "Wrong Username or Password, Try again!!!");
				
			}

		} else {

			request.setAttribute("msg", "Please enter username and password...");
		}

		/*Chcek if any Regulations are assigned to the Logged in Employee*/
		if (employeeId != null) {

			List<Long> employeesidWithRegulationsAssigned = loginService.findAssignedRegulationsForEmployee(); 

			if (employeesidWithRegulationsAssigned.contains(employeeId)) {

				logger.info("Logged in employee has assigned a regulation, employee ID is " + employeeId);

				session.setAttribute("employeeHasAssignedRegulation", true);
			}
		}

		request.getRequestDispatcher(page).include(request, response);
	}

	/*This formats the role because it is displayed in the UI*/
	private String formatRole(String role) {

		if (role.equals("headOfDepartment")) {

			role = "Head of Department";
		}


		return role;
	}


}


