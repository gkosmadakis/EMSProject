package com.ems.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ems.dao.BaseDao;
import com.ems.dao.BaseDaoImpl;
import com.ems.model.Department;
import com.ems.model.Employee;
import com.ems.service.BaseService;
import com.ems.service.BaseServiceImpl;
import com.ems.util.Constants;

/**
 * Servlet implementation class RegistrationController
 */
@WebServlet(urlPatterns={"/userRegistration"})
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(RegistrationController.class); 
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrationController() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.sendRedirect("userRegistration.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//doGet(request, response);

		String pwdMustBeSame = Constants.MSGPWD;

		String page = Constants.USERREGISTRATIONPAGE;

		/*Get the required data from the request*/
		if(request.getParameter(Constants.PASSWORD).equals(request.getParameter(Constants.CONFPASSWORD))){

			Employee employee = new Employee();

			/*set employee object*/
			employee.setFirstname(request.getParameter(Constants.FIRSTNAME));

			employee.setLastname(request.getParameter(Constants.LASTNAME));

			DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.US);

			Date date = null;

			try {

				date = format.parse(request.getParameter(Constants.DATEOFBIRTH));

			} catch (ParseException e) {

				logger.error("There was an error parsing the date of birth " + e.getLocalizedMessage());
			}
			date.setHours(12);
			date.setMinutes(00);
			employee.setDateOfBirth(date);

			employee.setDepartment(request.getParameter(Constants.DEPARTMENT));

			employee.setEmail(request.getParameter(Constants.EMAIL));

			employee.setUsername(request.getParameter(Constants.USERNAME));

			employee.setPassword(request.getParameter(Constants.PASSWORD));

			employee.setRole(request.getParameter(Constants.ROLE));

			logger.info(employee.toString());

			BaseDao baseDao = new BaseDaoImpl();

			boolean flag = baseDao.register(employee); 

			if (flag) {

				logger.info("New user registered successfully!");

				page = Constants.INDEXJSP;

				request.setAttribute("success", "New user registered successfully");
			}	 

		} 

		else {

			request.setAttribute("nonSuccess", pwdMustBeSame);
		}

		if (request.getParameter(Constants.ROLE).equals("headOfDepartment")){

			/*Call the db and save the employee to Department table*/

			Department dept = new Department();

			dept.setDeptName(request.getParameter(Constants.DEPARTMENT));
			dept.setHeadOfDept(request.getParameter(Constants.FIRSTNAME) +" " + request.getParameter(Constants.LASTNAME));

			BaseService addHeadofDeptToDepartment = new BaseServiceImpl();

			boolean flag = addHeadofDeptToDepartment.createDepartment(dept);

			if (flag) {

				logger.info("New Employee is a head of Department so it was added to Department table!");
			}
		}

		request.getRequestDispatcher(page).include(request, response);

	}



}


