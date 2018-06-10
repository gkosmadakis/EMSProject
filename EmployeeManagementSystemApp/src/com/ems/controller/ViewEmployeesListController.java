package com.ems.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ems.model.Employee;
import com.ems.service.BaseService;
import com.ems.service.BaseServiceImpl;
import com.session.SessionManagement;

/**
 * Servlet implementation class ViewEmployeesListController
 */
@WebServlet("/ViewEmployeesListController")
public class ViewEmployeesListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ViewEmployeesListController.class); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewEmployeesListController() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		/*Request for an employee row, user has clicked Edit button in viewAllEmployees.jsp*/
		String idStr = request.getParameter("id");
		
		BaseService getEmployeeDetailsService = new BaseServiceImpl();
		
		Long id = Long.parseLong(idStr);
		
		/*Retrieve employee object from DB*/
		Employee employeeRetrieved = getEmployeeDetailsService.getEmployeeDetails(id);
		
		request.setAttribute("id", idStr);
		request.setAttribute("username", employeeRetrieved.getUsername());
		request.setAttribute("department", employeeRetrieved.getDepartment());
		request.setAttribute("email", employeeRetrieved.getEmail());
		request.setAttribute("firstname", employeeRetrieved.getFirstname());
		request.setAttribute("lastname", employeeRetrieved.getLastname());
		request.setAttribute("password", employeeRetrieved.getPassword());
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String dateofbirth = df.format(employeeRetrieved.getDateOfBirth());
		
		request.setAttribute("dateofbirth", dateofbirth);
		request.setAttribute("role", employeeRetrieved.getRole());
		
		/*sent all the data to editEmployee.jsp page*/
		request.getRequestDispatcher("editEmployee.jsp").include(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//doGet(request, response);
		
		/*Request for an update, user has clicked Update button in editEmployee.jsp*/
		if(request.getParameter("submit") != null) {
			
			Employee employeeUpdated = new Employee();
			
			/*Get the required data from the request and the set the employee object*/
			employeeUpdated.setId(Long.parseLong(request.getParameter("id")));
			employeeUpdated.setUsername(request.getParameter("username"));
			employeeUpdated.setDepartment(request.getParameter("department"));
			employeeUpdated.setEmail(request.getParameter("email"));
			employeeUpdated.setFirstname(request.getParameter("firstname"));
			employeeUpdated.setLastname(request.getParameter("lastname"));
			employeeUpdated.setPassword(request.getParameter("password"));
			employeeUpdated.setRole(request.getParameter("role"));
			
			Date date = null;
			DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
			try {
				date = format.parse(request.getParameter("dateofbirth"));
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
			employeeUpdated.setDateOfBirth(date);
			
			String page = "editEmployee.jsp";
			
			BaseService baseDao = new  BaseServiceImpl();
			
			/*Call DB to update the employee details*/
			boolean updatedEmployee = baseDao.updateEmployee(employeeUpdated);
			
			if (updatedEmployee) {
				
				logger.info("Employee Updated successfully!");
				
				request.setAttribute("success", "Employee "+employeeUpdated.getFirstname()+" "+employeeUpdated.getLastname()+" Updated Successfully!");  
				
				page = "viewAllEmployees.jsp"; 
			}
			
			else {

				/* if there is any error during DB transaction send an attribute to the JSP page*/
				request.setAttribute("nonSuccess", "There was an error updating the employee. Try again!!!");
			}

		request.getRequestDispatcher(page).include(request, response);
			
			logger.info(request.getParameter("username"));
		}
		
	}
	
	/*This method returns a list of employee objects and it is used in viewAllEmployees.jsp*/
	public static List<Employee> getAllEmployeesFromDB() {
		
		BaseService getEmployeeService = new BaseServiceImpl();

 		List<Employee> employeesObj = getEmployeeService.getAllEmployees();
 
		return employeesObj;


	}

}
