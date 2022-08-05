package edu.poly.site.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.poly.common.CookieUltis;
import edu.poly.common.SessionUltis;

/**
 * Servlet implementation class LogoffServler
 */
@WebServlet("/Logoff")
public class LogoffServler extends HttpServlet {
	
   
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CookieUltis.add("username",null,0,response);
		
		SessionUltis.invalidate(request);
		request.setAttribute("isLogin", false);
		request.getRequestDispatcher("/Homepage").forward(request, response);
	}

}
