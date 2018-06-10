package com.ems.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ems.dao.BaseDao;
import com.ems.dao.BaseDaoImpl;
import com.ems.model.Department;
import com.ems.model.Employee;
import com.ems.service.BaseService;
import com.ems.service.BaseServiceImpl;
import com.session.SessionManagement;

/**
 * Servlet implementation class AddEmployeeController
 */
@WebServlet("/addEmployeeController") 
public class AddEmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AddEmployeeController.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEmployeeController() {
        super();
   
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String employeeAdded =null;
		if(session!=null){
			logger.info("session not null");
			employeeAdded= request.getParameter("firstname")+" "+request.getParameter("lastname");
			PrintWriter out =response.getWriter();
			response.setContentType("text/html");
			out.println("<h2>Added "+employeeAdded+"</h2>");
			
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String dob = request.getParameter("dateOfBirth");
			String dept = request.getParameter("department");
			
			// Store these 4 data to mySQL DB
			 
			logger.info(firstname+" "+lastname+" "+dob+" "+dept);
			out.print("<a href=Page3>Page3</a>");
			
		}
		/*session has been killed and is null*/
		else {
			logger.warn("Session is null: "+session);
			response.sendRedirect("index.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//doGet(request, response);
		/*Get the required data from the request*/
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String dateOfBirth = request.getParameter("dateofbirth");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String department = request.getParameter("department");	
		String role = request.getParameter("role");
		
		Employee employee = new Employee();
		/*Set the employee object*/
		employee.setFirstname(firstname.trim());
		
		employee.setLastname(lastname.trim());
		
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
		
		Date date = null;
		
		try {
		
			date = format.parse(dateOfBirth);
		
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		date.setHours(12);
		date.setMinutes(00);
		employee.setDateOfBirth(date);
		employee.setUsername(username.trim());
		employee.setPassword(password.trim());
		employee.setEmail(email.trim());
		employee.setDepartment(department.trim());
		employee.setRole(role.trim());

		String page = "addEmployeeDetails.jsp";
		
		/* if data are not null and have size perform transaction DB to register the employee*/
		if(firstname.trim().length() >= 0 && firstname != null &&
				lastname.trim().length() >= 0 && lastname != null) {
			
			BaseService baseDao = new BaseServiceImpl();
		
			boolean flag = baseDao.register(employee);
			
			if(flag) {
				
				logger.info("Added new Employee successfully!");
				
				request.setAttribute("success", "New Employee added successfully");
				
				page = "adminHomePage.jsp";   
			
			} else {
				
				/* if there is any error during DB transaction send an attribute to the JSP page*/
				request.setAttribute("msg", "Wrong Username or Password, Try again!!!");
			}
			
		} else {
			
			request.setAttribute("msg", "Please enter username and password...");
		}
		
		if (role.equals("headOfDepartment")){
			
			/*Call the db and save the employee to Department table*/
			
			Department dept = new Department();
			
			dept.setDeptName(department);
			dept.setHeadOfDept(firstname +" " + lastname);
			
			BaseService addHeadofDeptToDepartment = new BaseServiceImpl();
			
			boolean flag = addHeadofDeptToDepartment.createDepartment(dept);
			
			if (flag) {
				
				logger.info("New Employee is a head of Department so it was added to Department table!");
			}
		}
		
		request.getRequestDispatcher(page).include(request, response);
	}

}
