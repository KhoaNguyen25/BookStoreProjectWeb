package com.bookstore.controller.admin;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/admin/*")
public class AdminLoginFilter implements Filter{

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//cover request của ServletRequest về HttpServletRequest
		HttpServletRequest httpServlet = (HttpServletRequest) request;
		HttpSession session = httpServlet.getSession(false);
		boolean loginIn = session != null && session.getAttribute("useremail") != null;
		String loginURI = httpServlet.getContextPath()+"/admin/login";
		boolean loginRequest = httpServlet.getRequestURI().equals(loginURI);
		boolean loginPage = httpServlet.getRequestURI().endsWith("login.jsp");
		if(loginIn && (loginRequest || loginPage)) {
			RequestDispatcher rd = httpServlet.getRequestDispatcher("/admin/");
			rd.forward(request, response);
		}
		
		if(loginIn || loginRequest) {
			chain.doFilter(request, response);
			
		}else {
			RequestDispatcher rd = httpServlet.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}



}
