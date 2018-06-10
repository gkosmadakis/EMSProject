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

import com.ems.model.Employee;
import com.ems.model.RegulationAnalysis;
import com.ems.model.RegulationDetail;
import com.ems.service.BaseService;
import com.ems.service.BaseServiceImpl;
import com.ems.util.Constants;
import com.session.SessionManagement;

/**
 * Servlet implementation class AssignRegulationController
 */
@WebServlet("/AssignRegulationController")
public class AssignRegulationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AssignRegulationController.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AssignRegulationController() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//response.getWriter().append("Served at: ").append(request.getContextPath());

		/*Get the department of the logged in user which at this case is a Head of department*/
		findEmployeesByDepartment(request, response);
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//doGet(request, response);
		/*Get the selected employee to assign regulation*/
		String employeeName = request.getParameter("employee");
		String firstname = employeeName.substring(0, employeeName.indexOf(" "));
		String lastname = employeeName.substring(employeeName.indexOf(" ")+1, employeeName.length());

		String page = "assignRegulationToEmployee.jsp";

		if (firstname != null && lastname != null) {

			/*Find the id of the employee to whom is assigned the regulation*/
			BaseService assignRegulationService = new BaseServiceImpl();

			Long employeeId = assignRegulationService.findEmployeeIdByFullName(firstname, lastname);

			logger.info(employeeId);

			/*Get the id of the regulation to be assigned from the assignRegulationToEmployee.jsp */
			String regulationIdToBeAssigned = request.getParameter("regulationIdToBeAssigned");

			logger.info("Regulation ID to be assigned " +regulationIdToBeAssigned);

			/*Get the regulation from the regulation detail table now that you have the id of the regulation*/
			RegulationDetail regulationToBeAssigned = assignRegulationService.findRegulationById(Long.parseLong(regulationIdToBeAssigned));

			/*Prepare and set a regulation analysis object*/
			RegulationAnalysis regAnalysis = new RegulationAnalysis();

			regAnalysis.setRegulationDate(regulationToBeAssigned.getCreateDate());
			regAnalysis.setEmployeeId(employeeId);
			regAnalysis.setRegulationDetails(regulationToBeAssigned.getRegulationDetail());
			regAnalysis.setDeptHeadName(regulationToBeAssigned.getDeptHeadName());
			regAnalysis.setRegulationStatus(Constants.ASSIGNED);
			regAnalysis.setComments("");

			/*create an entry with all the relevant data to the regulation analysis table, regulation status is initialized as assigned, 
			 * comments are initialized as empty string*/
			boolean regulationAssigned = assignRegulationService.assignNewRegulation(regAnalysis);

			if(regulationAssigned) {

				logger.info("Regulation Assigned to " + employeeName);

				request.setAttribute("success", "Regulation "+regulationToBeAssigned.getRegulationTitle()+" Assigned Successfully to "+ employeeName); 

				page = "headDeptHomePage.jsp";

			} else {

				/* if there is any error during DB transaction send an attribute to the JSP page*/
				request.setAttribute("nonSuccess", "Regulation was not deleted, Try again!!!");
			}

		} else {

			request.setAttribute("nonSuccess", "Please select an Employee Name to Assign Regulation ");
		}

		request.getRequestDispatcher(page).include(request, response);


	} 

	private void findEmployeesByDepartment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*This method retrieves all the employees by department and adds them to employeeNames List */
		HttpSession session = request.getSession(false);
		String departmentName = (String) session.getAttribute("department");
		String regulationId = request.getParameter("id");
		String role = "employee";

		if (departmentName != null) {

			BaseService findEmployeesByDeptService = new BaseServiceImpl();

			List<Employee> employeesRetrieved = findEmployeesByDeptService.getEmployeesByDepartment(departmentName, role);

			List<String> employeeNames = new ArrayList<String>();

			for (Employee emp: employeesRetrieved) {
				/*if the role is employee add them to the list*/  
				if (emp.getRole().equals("employee")) {
					
					String firstname = emp.getFirstname();
					String lastname = emp.getLastname();

					employeeNames.add(firstname+" "+lastname);
				}
			}
			/* The firstname and lastname are sent then to assignRegulationToEmployee.jsp page*/
			request.setAttribute("fullnameslist", employeeNames);
			request.setAttribute("regulationIdToBeAssigned", regulationId);
			request.getRequestDispatcher("assignRegulationToEmployee.jsp").include(request, response);
		}
	}

}
