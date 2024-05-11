package com.bookstore.controller.frontend.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.CustomerService;

@WebServlet({"/register_form","/register_customer", "/show_login","/customer_login","/customer_logout"})
public class CustomerServlet extends HttpServlet{
	private CustomerService customerService;

	private static final long serialVersionUID = 6393009848371967487L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		customerService = new CustomerService(req, resp);
		String url = req.getServletPath();	
		switch (url) {
		case "/register_form":
			customerService.showFormCustomer();
			break;
		case "/show_login":
			customerService.showLogin();
			break;
		case "/customer_logout":
			customerService.logout();
			break;
		default:
			break;
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		customerService = new CustomerService(req, resp);
		String url = req.getServletPath();	
		switch (url) {
		case "/register_customer":
			customerService.registerCustomer();
			break;
		case "/customer_login":
			customerService.checkLogin();
			break;
			
		default:
			break;
		}
	}

}
