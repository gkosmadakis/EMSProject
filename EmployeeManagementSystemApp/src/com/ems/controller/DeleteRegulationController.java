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
import com.ems.model.RegulationDetail;
import com.ems.service.BaseService;
import com.ems.service.BaseServiceImpl;
import com.ems.util.Constants;
import com.session.SessionManagement;

/**
 * Servlet implementation class DeleteRegulationController
 */
@WebServlet("/DeleteRegulationController")
public class DeleteRegulationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(DeleteRegulationController.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteRegulationController() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		/*Get the required data from the request*/
		String regulationId = request.getParameter("id");

		String page = "viewRegulation.jsp";
  
		/* if data are not null and have size perform transaction DB to delete regulation*/
		if(regulationId.trim().length() >= 0 && regulationId != null) {

			BaseService deleteRegulationService = new BaseServiceImpl();
			
			Long regIdLong = Long.parseLong(regulationId);

			RegulationDetail regulation = deleteRegulationService.findRegulationById(regIdLong); 

			boolean flag = deleteRegulationService.deleteRegulation(regulation);

			if(flag) {

				logger.info("Regulation with title "+ regulation.getRegulationTitle()+ " Deleted");

				request.setAttribute("success", "Regulation "+regulation.getRegulationId()+" Deleted Successfully!"); 
				
				page = "adminHomePage.jsp";

			} else {
				
				/* if there is any error during DB transaction send an attribute to the JSP page*/
				request.setAttribute("nonSuccess", "Regulation was not deleted, Try again!!!");
			}

		} else {

			request.setAttribute("nonSuccess", "Please select Regulation ");
		}


		request.getRequestDispatcher(page).include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//doGet(request, response);
		
		
	}
	
	/*This methos returns all the regulations in a list and then it is used in assignRegulations.jsp*/
	public static List<RegulationDetail> getAllRegulationsFromDB() {
		
		List<RegulationDetail> regulations = new ArrayList<RegulationDetail>();

		BaseService retrieveRegulationsService = new BaseServiceImpl();

		regulations = retrieveRegulationsService.getAllRegulations();

		return regulations;
		
	}

}
