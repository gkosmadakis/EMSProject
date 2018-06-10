package com.ems.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.ems.dao.BaseDao;
import com.ems.dao.BaseDaoImpl;
import com.ems.model.Employee;
import com.ems.model.RegulationDetail;
import com.ems.service.BaseService;
import com.ems.service.BaseServiceImpl;
import com.ems.util.Constants;
import com.session.SessionManagement;

/**
 * Servlet implementation class AddRegulationController
 */
@WebServlet("/AddRegulationController")
public class AddRegulationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(AddRegulationController.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRegulationController() {
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

		/*Get the required data from the request*/
		String departmentName = request.getParameter("department");
		String departmentHead = request.getParameter("departmenthead");
		
		String regulationTitle = request.getParameter("regulationtitle");
		String regulationText = request.getParameter("regulationtext");

		String page = "addRegulation.jsp";

		/* if data are not null and have size perform transaction DB to add the regulation*/
		if(regulationText.length() > 0 && regulationText !=null) {

			BaseService baseDao = new  BaseServiceImpl();

			RegulationDetail regulation = new RegulationDetail();
			
			/*Set the RegulationDetail object*/
			regulation.setDeptHeadName(departmentHead);
			regulation.setDeptName(departmentName);
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
			Date date = null;
			
			try {
			
				date = format.parse(request.getParameter("createdate"));
			
			} catch (ParseException e) {
				 
				logger.error("There was an error parsing the Create date for Regulation "+ e.getLocalizedMessage() );
				
			}
			date.setHours(12);
			date.setMinutes(00);
			regulation.setCreateDate(date);
			regulation.setRegulationTitle(regulationTitle);
			regulation.setRegulationDetail(regulationText);

			boolean flag = baseDao.addRegulation(regulation); 

			if (flag) {

				logger.info("Regulation Added successfully!");
				
				request.setAttribute("success", "Regulation "+regulation.getRegulationTitle()+" Added Successfully!");  
				
				page = "adminHomePage.jsp"; 
			}

			else {
				
				/* if there is any error during DB transaction send an attribute to the JSP page*/
				request.setAttribute("nonSuccess", "There was an error adding the regulation. Try again!!!");
			}

		} else {

			request.setAttribute("nonSuccess", "Please enter a Regulation text message.");
		}

		request.getRequestDispatcher(page).include(request, response);

	}

	

}
