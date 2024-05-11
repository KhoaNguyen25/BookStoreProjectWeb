package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;

public class CategoryService {

	private CategoryDAO categoryDAO;
	private HttpServletRequest req;
	private HttpServletResponse resp;

	public CategoryService(HttpServletRequest req, HttpServletResponse resp) {
		
		this.req = req;
		this.resp = resp;
		categoryDAO = new CategoryDAO();
	}

	public void listCategory() throws ServletException, IOException {
		listCategory(null);
	}

	public void listCategory(String message) throws ServletException, IOException {
		List<Category> listCategory = categoryDAO.listAll();
		req.setAttribute("listCategory", listCategory);
		if (message != null) {
			req.setAttribute("message", message);
		}
		String homePage = "category_list.jsp";
		CommonUtility.forwardToPage(homePage, req, resp);
	}

	public void createCategory(String name) throws ServletException, IOException {

		if (categoryDAO.findByName(name) != null) {
			String message = "Found not created category. A category " + name + " aleary exists";
			CommonUtility.showMessageBackend("message.jsp", message, req, resp);
		} else {
			Category category = new Category(name);
			categoryDAO.create(category);
			listCategory("new category created successfly");
		}

	}

	public void editCategory() throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));

		Category category = categoryDAO.get(id);
		String editCategory ;
		if(category == null) {
			String message = "Could not find category with "+id+" ";
			editCategory = "message.jsp";
			CommonUtility.showMessageBackend(editCategory, message, req, resp);
		}else {
			req.setAttribute("category", category);
			editCategory = "category_form.jsp";
			CommonUtility.forwardToPage(editCategory, req, resp);
		}
		
	}

	public void updateCategory() throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("categoryId"));
		String name = req.getParameter("name");
		
		Category categoryId = categoryDAO.get(id);
		Category categoryByName = categoryDAO.findByName(name);
		if(categoryByName != null && categoryByName.getCategoryId() != categoryId.getCategoryId()) {
			String message = "Could not update category. Category width name "+name+" aleady exists.";

			CommonUtility.forwardToPage("message.jsp", message, req, resp);
			
		}else {
			Category cat = new Category(id, name);
			categoryDAO.update(cat);
			listCategory("edit category created successffly");
		}
	}

	public void deleteCategory() throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		BookDAO bookDao = new BookDAO();
		long numberOfBook	= bookDao.countWidthByCategory(id);
		//find exixts
		Category category = categoryDAO.get(id);
		String message;
		if(category != null) {
			
			if(numberOfBook > 0) {
				message = "Could not delete the category with (ID: %d) because it contains some books";
				message = String.format(message, numberOfBook);
			}else {
				categoryDAO.delete(id);
				message = "delete successffly";
			}
			listCategory(message);
		}else {
			 message = "Could not find category with ID " + id
					+ ", or it might have been deleted";
			CommonUtility.forwardToPage("message.jsp", message, req, resp);
		}
		
		
	}
}
