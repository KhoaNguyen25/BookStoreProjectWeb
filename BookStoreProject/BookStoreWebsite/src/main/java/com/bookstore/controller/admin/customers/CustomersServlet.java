package com.bookstore.controller.admin.customers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.CustomerService;

@WebServlet(urlPatterns = {"/admin/list_customers","/admin/create_customer","/admin/edit_customer","/admin/updated_customer","/admin/delete_customer"})
public class CustomersServlet extends HttpServlet {
	private static CustomerService customerService;

	private static final long serialVersionUID = 7527840220246499519L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		customerService = new CustomerService(req, resp);
		String url = req.getServletPath();
		switch (url) {
		case "/admin/list_customers":
			customerService.listCustomers();
			break;
		case "/admin/edit_customer":
			customerService.editCustomers();
			break;
		case "/admin/delete_customer":
			customerService.deleteCustomer();
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
		case "/admin/create_customer":
			customerService.createCustomer();
			break;
		case "/admin/updated_customer":
			customerService.updateCustomer();
			break;
		
		default:
			break;
		}
	}

}
