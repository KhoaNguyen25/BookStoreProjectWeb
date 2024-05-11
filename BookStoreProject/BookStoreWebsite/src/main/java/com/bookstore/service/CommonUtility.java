package com.bookstore.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommonUtility {
	
	public static void forwardToPage(String page, String message, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("message", message);
		req.getRequestDispatcher(page).forward(req, resp);
		
	}
	public static void showMessageFrontend(String message, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		forwardToPage("frontend/message.jsp", message, req, resp);
		
	}

	public static void showMessageBackend(String page,String message, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		forwardToPage(page, message, req, resp);
	}
	public static void forwardToPage(String page,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(page).forward(req, resp);
		
	}
	
}
