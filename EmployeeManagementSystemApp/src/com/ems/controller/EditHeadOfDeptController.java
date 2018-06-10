package com.ems.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ems.model.Department;
import com.ems.service.BaseService;
import com.ems.service.BaseServiceImpl;
import com.session.SessionManagement;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

/**
 * Servlet implementation class EditHeadOfDeptController
 */
@WebServlet("/EditHeadOfDeptController")
public class EditHeadOfDeptController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(EditHeadOfDeptController.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditHeadOfDeptController() {
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

		/*This is called when the user clicks on the Save Button to save updates on Head of Department info*/
		if(request.getParameter("savetoedit") != null) {

			logger.info(request.getParameter("updatedheadofdept") + request.getParameter("updateddept")+request.getParameter("oldheadofdept"));

			/*Start the update of Head of Department and/or department name*/
			/*Get the required data from the request*/
			String oldHeadOfDeptName = request.getParameter("oldheadofdept");

			String oldDepartmentName = request.getParameter("oldddept");

			Long idToUpdate = getIdByHeadOfDepartmentName(oldHeadOfDeptName);

			String updatedHeadOfDeptName = request.getParameter("updatedheadofdept").trim();

			String updatedDepartment = request.getParameter("updateddept").trim();

			String page = "editDeptHeadInfo.jsp";

			/* if data are not null and have size perform transaction DB to update the head of department details*/
			if(updatedHeadOfDeptName.trim().length() >= 0 && updatedDepartment != null) {

				BaseService updateDeptService = new BaseServiceImpl();

				Department dept = new Department(); 

				dept.setId(idToUpdate);

				/*if they have done an update in the name use it*/
				if(!updatedDepartment.equals("")) {

					dept.setDeptName(updatedDepartment);
				}

				/*otherwise use the old value*/
				else {

					dept.setDeptName(oldDepartmentName);
				}

				if (!updatedHeadOfDeptName.equals("")) {

					dept.setHeadOfDept(updatedHeadOfDeptName);
				}

				else {

					dept.setHeadOfDept(oldHeadOfDeptName);
				}

				boolean flag = updateDeptService.updateHeadOfDeptInfo(dept);

				if(flag) {

					logger.info("Department Head info is updated");

					request.setAttribute("success", "Department Head info Updated Successfully!");

					page = "adminHomePage.jsp";   

				} else {

					/* if there is any error during DB transaction send an attribute to the JSP page*/
					request.setAttribute("nonSuccess", "Department Head info was not updated, Try again!!!");
				}

			} else {

				request.setAttribute("nonSuccess", "Please select department head name to update");
			}


			request.getRequestDispatcher(page).include(request, response);

		}

		/*This is called when the user clicks on the View button to select Head of Department name*/
		else {
			
			/*Get the required data from the request*/
			String headofdepartment = request.getParameter("headofdepartment");

			logger.info(headofdepartment);

			String page = "editDeptHeadInfo.jsp";

			Map<String,String> headDepartmentWithName = getDepartmentByHeadName(headofdepartment);

			for (Entry<String, String> entry : headDepartmentWithName.entrySet()) {

				request.setAttribute("departmentname", entry.getKey());
				request.setAttribute("headofdepartment", entry.getValue());
			}

			request.getRequestDispatcher(page).include(request, response);
		}

	}
	/*This method returns all the Head of department names it is used in getAllHeadOfDeptNamesFromDB*/
	public static Map<Long, List<String>> getAllHeadOfDeptsFromDB() {

		Map<Long, List<String>> headOfdeptsWithNames = new HashMap<Long, List<String>>();

		BaseService headOfDeptService = new BaseServiceImpl();

		headOfdeptsWithNames = headOfDeptService.getAllHeadOfDeptsNames();

		return headOfdeptsWithNames;

	}

	public static List<String> getAllHeadOfDeptNamesFromDB() {

		Map<Long, List<String>> headOfdeptsNamesWithDept = new HashMap<Long, List<String>>();

		headOfdeptsNamesWithDept = getAllHeadOfDeptsFromDB();

		List<String> headOfdeptsNames = new ArrayList<String>();

		List<String> tempList = null;

		for(Map.Entry<Long, List<String>> entry : headOfdeptsNamesWithDept.entrySet()){

			tempList =entry.getValue();

			for (String headOfDept: tempList) {

				if (headOfDept.startsWith("HeadOfDept")) {

					headOfdeptsNames.add(headOfDept.substring(headOfDept.indexOf(":")+1, headOfDept.length()).trim());
				}
			}
		}

		return headOfdeptsNames;

	}

	/*This method returns a map with key as Department and as value the Head of the corresponding department
	 * for Example IT => James Smith*/
	public static Map<String,String> getDepartmentByHeadName(String headDepartmentName) {

		Map<Long, List<String>> headOfdeptsWithNames = new HashMap<Long, List<String>>();

		BaseService headOfDeptService = new BaseServiceImpl();

		headOfdeptsWithNames = headOfDeptService.getAllHeadOfDeptsNames();

		String department = "";

		List<String> tempList = null;

		for (Entry<Long, List<String>> entry : headOfdeptsWithNames.entrySet()) {

			tempList = entry.getValue();

			for (String deptInfo: tempList) {

				if(deptInfo.contains(headDepartmentName.trim())) {

					department = tempList.toString().substring(tempList.toString().indexOf(":")+1, tempList.toString().indexOf(","));
				}
			}
		}

		Map<String,String> headNameWithDepartment = new HashMap<String,String>();

		headNameWithDepartment.put(department.trim(),headDepartmentName.trim());

		return headNameWithDepartment;

	}
	/*This method returns the ID for a requested Head of department name*/
	public Long getIdByHeadOfDepartmentName(String headOfDepartmentName) {

		Long id = 0L;
		Map<Long, List<String>> headOfdeptsWithNames = new HashMap<Long, List<String>>();

		BaseService headOfDeptService = new BaseServiceImpl();

		headOfdeptsWithNames = headOfDeptService.getAllHeadOfDeptsNames();

		for (Entry<Long, List<String>> entry : headOfdeptsWithNames.entrySet()) {

			if(entry.getValue().toString().contains(headOfDepartmentName)) {
				id = entry.getKey();
			}
		}

		return id;


	}

}
