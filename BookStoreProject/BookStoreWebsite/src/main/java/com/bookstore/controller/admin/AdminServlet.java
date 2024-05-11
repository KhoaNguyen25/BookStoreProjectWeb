package com.bookstore.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.UserServices;


@WebServlet(urlPatterns = {"/admin/", "/admin/login"})
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminServlet() {
        super();
       
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String homepage = "index.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(homepage);
		rd.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserServices userServices = new UserServices(req, resp);
		userServices.login();
	}

}
