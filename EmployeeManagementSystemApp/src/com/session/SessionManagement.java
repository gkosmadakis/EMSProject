package com.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ems.dao.BaseDaoImpl;

public class SessionManagement {
	
	private static final Logger logger = Logger.getLogger(SessionManagement.class);

	public static void checkUserActivity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if(session!= null){

			/*The container terminates the session if the app remains inactive more than the session.getMaxInactiveInterval which i set
			 * in the web.xml, so i check if the session is not valid and redirect the user to the login page*/

			if (!request.isRequestedSessionIdValid()) {

				logger.warn("Your session has expired due to inactivity");

				logger.info("Session timeout is set to: " +session.getMaxInactiveInterval() + " seconds");	 
				
				request.setAttribute("nonSuccess", "Due to inactivity you have been logged out of the system");
				
				request.getRequestDispatcher("index.jsp").forward(request, response);
				
			}
		}

		else {

			System.out.println("Session is null or has expired");
			
			request.setAttribute("nonSuccess", "Your session is terminated");
			
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}



}
