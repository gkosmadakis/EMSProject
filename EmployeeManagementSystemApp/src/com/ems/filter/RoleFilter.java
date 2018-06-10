package com.ems.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.ems.dao.BaseDaoImpl;
import com.ems.model.Employee;
import com.ems.service.BaseService;
import com.ems.service.BaseServiceImpl;
import com.ems.util.Constants;

/**
 * Servlet Filter implementation class RoleFilter
 */
@WebFilter("/login")
public class RoleFilter implements Filter {

	private static final Logger logger = Logger.getLogger(RoleFilter.class);
	/**
	 * Default constructor. 
	 */
	public RoleFilter() {

	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	/* I use this filter to separate the type of user Employee, Head of Department and Admin*/
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		logger.info("Role filter before chain");

		/*Get the required data from the request*/
		String username = request.getParameter(Constants.USERNAME);

		String password = request.getParameter(Constants.PASSWORD);

		if(username.trim().length() >= 0 && username != null &&
				password.trim().length() >= 0 && password != null) {

			BaseService loginService = new BaseServiceImpl();

			/*Call the DB to find out the role of the user*/   

			Employee employeeRetrieved = loginService.findTypeOfEmployee(username);

			if (employeeRetrieved == null) {

				logger.log(Level.ERROR, "No employee found with this username");

				request.setAttribute("msg", "Wrong Username or Password, Try again!!!");
			}

			else {

				/*According to the role i set the home page, role, id and department are send to LoginController*/
				if(employeeRetrieved.getRole().equals("employee")) {

					request.setAttribute("homePage", Constants.EMPLOYEEHOMEPAGE);
					/*Store the role in the request  and then store it in the session in the LoginController */
					request.setAttribute("role", employeeRetrieved.getRole());
					request.setAttribute("id", employeeRetrieved.getId());
					request.setAttribute("department", employeeRetrieved.getDepartment());

				}
				else if(employeeRetrieved.getRole().equals("headOfDepartment")) {

					request.setAttribute("homePage", Constants.HEADOFDEPARTMENTHOMEPAGE);
					/*Store the role in the request  and then store it in the session in the LoginController */
					request.setAttribute("role", employeeRetrieved.getRole());
					request.setAttribute("id", employeeRetrieved.getId());
					request.setAttribute("department", employeeRetrieved.getDepartment());
				}

				else if(employeeRetrieved.getRole().equals("admin")) {

					request.setAttribute("homePage", Constants.ADMINHOMEPAGE);
					/*Store the role in the request  and then store it in the session in the LoginController */
					request.setAttribute("role", employeeRetrieved.getRole());
					request.setAttribute("id", employeeRetrieved.getId());
					request.setAttribute("department", employeeRetrieved.getDepartment());
				}

			}

			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
