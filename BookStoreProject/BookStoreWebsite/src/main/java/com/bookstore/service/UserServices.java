package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.UsersDAO;
import com.bookstore.entity.Users;
import com.bookstore.security.HashGeneratorUtils;

public class UserServices {
	private UsersDAO userDAO;

	private HttpServletRequest req;
	private HttpServletResponse resp;

	public UserServices(HttpServletRequest req, HttpServletResponse resp) {

		userDAO = new UsersDAO();
		this.req = req;
		this.resp = resp;
	}
	
	public void listUsers() throws ServletException, IOException {
		listUsers(null);
	}
	
	public void listUsers(String message) throws ServletException, IOException{
		List<Users> listUser = userDAO.listAll();
		
		req.setAttribute("listUser", listUser);
		if(message != null) {
			req.setAttribute("message", message);
		}

		String listPage = "user_list.jsp";
		CommonUtility.forwardToPage(listPage, req, resp);
	}
	
	public void createUser(String email, String password, String fullname) throws ServletException, IOException {
		if(userDAO.findByEmail(email) != null) {
			String message = "Found not created user. A user "+ email +" aleary exists";
			CommonUtility.showMessageBackend("message.jsp", message, req, resp);
		}else {
			Users user = new Users(email, password, fullname);
			userDAO.create(user);
			listUsers("new users created successfly");
		}
		
	}

	public void editUser() throws ServletException, IOException {
		int userId =Integer.parseInt(req.getParameter("id"));
		Users user = userDAO.get(userId);
		
		String editPage;
		if(user == null) {
			String message = "Could not find user with "+userId+" ";	
			editPage = "message.jsp";
			CommonUtility.showMessageBackend(editPage, message, req, resp);

		}else {
			user.setPassword(null);
			req.setAttribute("user", user);
			editPage = "user_form.jsp";
			CommonUtility.forwardToPage(editPage, req, resp);
		}

		
	}
	
	public void updatesUser() throws ServletException, IOException {
		Integer userId = Integer.parseInt(req.getParameter("userId"));
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String fullName = req.getParameter("fullName");
		
		Users userById = userDAO.get(userId);
		Users userByEmail = userDAO.findByEmail(email);
		
		if(userByEmail != null && userByEmail.getUserId() != userById.getUserId()) {
			String message = "Could not update users. User width email "+email+" aleady exists.";

			CommonUtility.showMessageBackend("message.jsp", message, req, resp);
		}else {
			String encryptedPassword = HashGeneratorUtils.generateMD5(password);
			Users users = new Users(userId ,email, encryptedPassword, fullName);
			userDAO.update(users);
			
			listUsers("Users has been update successffly !");
		}
		
	}

	public void deleteUser() throws ServletException, IOException {
		Integer userId = Integer.parseInt(req.getParameter("id"));
		//tìm user có tồn tại trong db không
		Users user = userDAO.get(userId);
		String message = "Users has been delete successffly !";
		if(user == null) {
			message = "Could not find user with ID " + userId
					+ ", or it might have been deleted by another admin";

			CommonUtility.showMessageBackend("message.jsp", message, req, resp);
		}else if(userId == 1) {
			message ="The default admin user account cannot be deleted.";
			CommonUtility.showMessageBackend("message.jsp", message, req, resp);
			return;
		}else {
			userDAO.delete(userId);
			listUsers(message);
		}
		
	}

	public void login() throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		boolean checkLogin = userDAO.checkLogin(email, password);
		if(checkLogin) {
			req.getSession().setAttribute("useremail", email);
			resp.sendRedirect("/BookStoreWebsite/admin/");
		}else {
			String message = "Login faild";
			CommonUtility.showMessageBackend("login.jsp", message, req, resp);
		}
		
	}

}
