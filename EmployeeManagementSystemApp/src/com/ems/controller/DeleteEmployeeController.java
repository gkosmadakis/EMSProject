package com.ems.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ems.model.Department;
import com.ems.model.Employee;
import com.ems.service.BaseService;
import com.ems.service.BaseServiceImpl;
import com.session.SessionManagement;

/**
 * Servlet implementation class DeleteEmployeeController
 */
@WebServlet("/DeleteEmployeeController")
public class DeleteEmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(DeleteEmployeeController.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteEmployeeController() {
        super();
      
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		/*Request for an employee row, user has clicked Delete button in viewAllEmployees.jsp*/
		String employeeToDelete = request.getParameter("id");
		
		Long employeeId = Long.parseLong(employeeToDelete);
		
		Employee emp = null;
		
		String page = "adminHomePage.jsp";
		  
		if(employeeId !=null) {

			BaseService deleteEmployeeService = new BaseServiceImpl();
			
			/* retrieve the employee from the DB*/
			emp = deleteEmployeeService.findEmployeeById(employeeId); 
			
			/*delete the employee*/
			boolean flag = deleteEmployeeService.deleteEmployee(emp);

			if(flag) {

				logger.info("Employee with username "+ emp.getUsername()+ " Deleted");

				request.setAttribute("success", "Employee "+emp.getFirstname()+ " "+emp.getLastname()+ " Deleted Successfully!");

				page = "viewAllEmployees.jsp";   

			} else {
				
				/* if there is any error during DB transaction send an attribute to the JSP page*/
				request.setAttribute("nonSuccess", "Employee was not deleted, Try again!!!");
			}

		}
		
		if (emp.getRole().equals("headOfDepartment")){
			
			/*Call the db and delete the head of department from Department table*/
			BaseService deleteHeadofDeptFromDepartment = new BaseServiceImpl();
			
			Department dept = new Department();
			
			dept.setDeptName(emp.getDepartment());
			dept.setHeadOfDept(emp.getFirstname() +" " + emp.getLastname());
			dept.setId(deleteHeadofDeptFromDepartment.findIdByDepartmentHead(emp.getFirstname() +" " + emp.getLastname()));
			
			boolean flag = deleteHeadofDeptFromDepartment.deleteDepartment(dept);
			
			if (flag) {
				
				logger.info("Employee "+emp.getFirstname()+" "+emp.getLastname()+" was a head of Department so it was deleted from Department table!");
			}
		}


		request.getRequestDispatcher(page).include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//doGet(request, response);
		
		
	}
	
	
	public static List<String> getAllEmployeesFromDB() {
		
		List<String> employees = new ArrayList<String>();

		BaseService deleteEmployeeService = new BaseServiceImpl();

		List<Employee> employeesObj = deleteEmployeeService.getAllEmployees();
		
		for (Employee employee: employeesObj) {
			
			String firstname = employee.getFirstname();
			String lastname = employee.getLastname();
			employees.add(firstname+" "+lastname);
		}

		return employees;


	}

}
