package com.bookstore.controller.admin.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.UserServices;

@WebServlet(urlPatterns = { "/admin/list_users", "/admin/create_user", "/admin/edit_user", "/admin/update_user", "/admin/delete_user" })
public class UserServlet extends HttpServlet {

	UserServices userService;

	public UserServlet() {
	}

	private static final long serialVersionUID = -1652020375176650939L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		userService = new UserServices(req, resp);
		String url = req.getServletPath();

		switch (url) {
		case "/admin/list_users":
			userService.listUsers();
			break;
		case "/admin/edit_user":
			userService.editUser();
			break;
		case "/admin/delete_user":
			userService.deleteUser();
			break;

		default:
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		userService = new UserServices(req, resp);
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String fullname = req.getParameter("fullName");

		String url = req.getServletPath();
		switch (url) {
		case "/admin/create_user":
			userService.createUser(email, password, fullname);
			break;

		case "/admin/update_user":
			userService.updatesUser();
			break;

		default:
			break;
		}

	}

}
