package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.CustomerDAO;
import com.bookstore.entity.Customer;

public class CustomerService {
	private CustomerDAO customerDAO;

	private HttpServletRequest req;
	private HttpServletResponse resp;

	public CustomerService(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
		customerDAO = new CustomerDAO();

	}

	public void listCustomers() throws ServletException, IOException {
		listCustomers(null);
	}

	public void listCustomers(String message) throws ServletException, IOException {
		List<Customer> listCustomers = customerDAO.listAll();
		req.setAttribute("listCustomers", listCustomers);
		if (message != null) {
			req.setAttribute("message", message);
		}
		CommonUtility.forwardToPage("customer_list.jsp", req, resp);

	}
	
	public void updateCustomerFromForm(Customer customer) {
		String email = req.getParameter("email");
		String fullname = req.getParameter("fullname");
		String address = req.getParameter("address");
		String city = req.getParameter("city");
		String country = req.getParameter("country");
		String phone = req.getParameter("phone");
		String zipcode = req.getParameter("zipcode");
		String password = req.getParameter("password");

		customer.setEmail(email);
		customer.setFullname(fullname);
		customer.setAddress(address);
		customer.setCity(city);
		customer.setCountry(country);
		customer.setPhone(phone);
		customer.setZipcode(zipcode);
		customer.setPassword(password);
	}

	public void createCustomer() throws ServletException, IOException {
		String email = req.getParameter("email");
		Customer customer = new Customer();
		updateCustomerFromForm(customer);
		// check email exist
		String message;
		Customer findByEmail = customerDAO.findByEmail(email);

		if (findByEmail == null) {
			message = "new customer created successfly";
			customerDAO.create(customer);
			listCustomers(message);
		} else {
			message = "Found not created customer. A customer " + email + " aleary exists";
			CommonUtility.forwardToPage("message.jsp", message, req, resp);
			return;
		}

	}

	public void registerCustomer() throws ServletException, IOException {

		Customer customer = new Customer();
		String email = req.getParameter("email");
		updateCustomerFromForm(customer);
		// check email exist
		String message = "";
		Customer findByEmail = customerDAO.findByEmail(email);

		if (findByEmail == null) {
			message = "You have successfully registered an account. Thank you !!! </br> "
					+ "<a href='login'>Here Login</a>";
			customerDAO.create(customer);
			CommonUtility.showMessageFrontend(message, req, resp);
		} else {
			message = "A customer " + email + " aleary exists";
			CommonUtility.showMessageFrontend(message, req, resp);
			return;
		}
	}

	public void editCustomers() throws ServletException, IOException {
		int getId = Integer.parseInt(req.getParameter("id"));
		Customer customer = customerDAO.get(getId);
		String url;
		if (customer != null) {
			req.setAttribute("customer", customer);
			url = "customer_form.jsp";
			CommonUtility.forwardToPage(url, req, resp);
		} else {
			String message = "Could not find customer with ID: " + getId;
			url = "message.jsp";
			CommonUtility.showMessageBackend(url, message, req, resp);
		}

	}


	public void updateCustomer() throws ServletException, IOException {

		int getId = Integer.parseInt(req.getParameter("customerId"));
		String email = req.getParameter("email");
		
		// check email exist
		Customer customerByEmail = customerDAO.findByEmail(email);

		if (customerByEmail != null && customerByEmail.getCustomerId() != getId) {
			listCustomers("Could not update Customer. Customer width email " + email + " in Action aleady exists.");
			return;
		} else {
			Customer customer = customerDAO.get(getId);
			updateCustomerFromForm(customer);
			customerDAO.update(customer);
			listCustomers("You are updated Customer Successfully");
		}

	}

	public void deleteCustomer() throws ServletException, IOException {
		int getId = Integer.parseInt(req.getParameter("id"));
		Customer findByCustomer = customerDAO.get(getId);
		if (findByCustomer == null) {
			String message = "Could not find customer with ID " + getId + ", or it has been deleted by another admin";
			CommonUtility.showMessageBackend("message.jsp", message, req, resp);
		} else {
			customerDAO.delete(getId);
			listCustomers("Delete successffly");
		}
	}

	public void showFormCustomer() throws ServletException, IOException {
		String url = "/frontend/register_form.jsp";
		CommonUtility.forwardToPage(url, req, resp);

	}

	public void showLogin() throws ServletException, IOException {
		String url = "/frontend/customer_login.jsp";
		CommonUtility.forwardToPage(url, req, resp);	
	}

	public void checkLogin() throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		Customer customer = customerDAO.checkLogin(email, password);
		String message = null;
		if(customer != null) {
			// lưu vào session
			req.getSession().setAttribute("customerLogin", customer);
			req.getRequestDispatcher("frontend/customer_profile.jsp").forward(req, resp);
		}else {
			message = "Email or Password login error";
			req.setAttribute("message", message);
			showLogin();
		}
		
	}

	public void logout() throws IOException {
	Object checkSession = req.getSession().getAttribute("customerLogin");
		if(checkSession != null) {
			req.getSession().removeAttribute("customerLogin");
			//redirect
			resp.sendRedirect(req.getContextPath());	
		}
		
	}

}
