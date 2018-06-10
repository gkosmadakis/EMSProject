package com.ems.controller;

import java.io.IOException;
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
import com.sun.corba.se.impl.orbutil.closure.Constant;

/**
 * Servlet implementation class GetEmployeeDetailsController
 */
@WebServlet("/GetEmployeeDetailsController")
public class GetEmployeeDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RegulationAnalysis regulation = null;
	private static final Logger logger = Logger.getLogger(GetEmployeeDetailsController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetEmployeeDetailsController() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//response.getWriter().append("Served at: ").append(request.getContextPath());

		HttpSession session = request.getSession(false);

		SessionManagement.checkUserActivity(request, response);

		if (request.getParameter("assigned") != null) {

			/*Show the regulation to employee*/
			Long employeeId = (Long) session.getAttribute("employeeId");

			BaseService findRegulationService = new BaseServiceImpl();

			/*Call the DB find the regulation based on employee id*/
			regulation = findRegulationService.findRegulationByEmployeeId(employeeId);

			request.setAttribute("regulationassigned", regulation);

			request.getRequestDispatcher("employeeViewRegulation.jsp").include(request, response);
		}

		/*This is to show employee details to the employee*/
		else {

			if (request.isRequestedSessionIdValid()) {
				
				String username = (String) session.getAttribute("username");

				Employee employeeId = null;

				BaseService getEmployeeDetailsService = null;

				if (username != null) {

					getEmployeeDetailsService = new BaseServiceImpl();

					employeeId = getEmployeeDetailsService.findTypeOfEmployee(username);
					/*call DB and get all the details*/
					Employee employeeRetrieved = getEmployeeDetailsService.getEmployeeDetails(employeeId.getId());

					if (employeeRetrieved != null) {

						/*set the attributes and pass them to fetchEmployeeDetails.jsp */
						request.setAttribute("username", employeeRetrieved.getUsername());
						request.setAttribute("userdepartment", employeeRetrieved.getDepartment());
						request.setAttribute("email", employeeRetrieved.getEmail());
						request.setAttribute("firstname", employeeRetrieved.getFirstname());
						request.setAttribute("lastname", employeeRetrieved.getLastname());
						request.setAttribute("userrole", employeeRetrieved.getRole());

						request.getRequestDispatcher("fetchEmployeeDetails.jsp").include(request, response);
					}
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*Employee has added a comment save it in the database*/
		String page = "employeeViewRegulation.jsp"; 

		BaseService updateRegulationService = null;

		/* if data are not null perform transaction DB to save the comment*/
		if (request.getParameter("comment") != null) {

			String employeeComment = request.getParameter("comment");

			updateRegulationService = new BaseServiceImpl();

			regulation.setComments(employeeComment);

			boolean flag = updateRegulationService.updateRegulation(regulation);

			if (flag) {

				logger.info("Regulation Comment: " +employeeComment+ " added successfully!");

				request.setAttribute("success", "Regulation Comment "+regulation.getComments()+" Added Successfully!");  

			} 

			else {

				/* if there is any error during DB transaction send an attribute to the JSP page*/
				request.setAttribute("nonSuccess", "There was an error adding the comment. Try again!!!");
			}

			request.setAttribute("regulationassigned", regulation);

			request.getRequestDispatcher(page).include(request, response);

			logger.info(request.getParameter("comment") + regulation);

		} 

		/*Check if user clicked on Accept or Decline buttons*/
		if (request.getParameter("accept") != null || request.getParameter("decline") != null) {

			if (request.getParameter("accept") != null) {

				regulation.setRegulationStatus(Constants.ACCEPTED);

				logger.info("User clicked on Accept " + request.getParameter("accept"));
			}

			if (request.getParameter("decline") != null) {

				regulation.setRegulationStatus(Constants.DECLINED);

				logger.info("User clicked on Decline " + request.getParameter("decline"));
			}

			updateRegulationService = new BaseServiceImpl();

			/* Save on DB the accept or decline status accordingly*/
			boolean flag =  updateRegulationService.updateRegulation(regulation);

			if (flag) {

				logger.info("Regulation Status updated successfully!");

				request.setAttribute("success", "Regulation was updated with status "+regulation.getRegulationStatus());  

			}
			else {

				/* if there is any error during DB transaction send an attribute to the JSP page*/
				request.setAttribute("nonSuccess", "There was an error updating the regulation status. Try again!!!");
			}

			request.setAttribute("regulationassigned", regulation);

			request.getRequestDispatcher(page).include(request, response);
		}

	}



}
