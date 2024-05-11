package com.bookstore.controller.admin.category;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.CategoryService;

@WebServlet(urlPatterns = { "/admin/list_categories", "/admin/create_categories", "/admin/edit_categories",
		"/admin/update_categories", "/admin/delete_category" })
public class CategoryServlet extends HttpServlet {
	private CategoryService categoryService;

	public CategoryServlet() {

	}

	private static final long serialVersionUID = -2474368847761496696L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		categoryService = new CategoryService(req, resp);
//		String url = req.getRequestURI();
		String url = req.getServletPath();
		switch (url) {
		case "/admin/list_categories":
			categoryService.listCategory();
			break;
		case "/admin/edit_categories":
			categoryService.editCategory();
			break;
		case "/admin/delete_category":
			categoryService.deleteCategory();
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		categoryService = new CategoryService(req, resp);
		String name = req.getParameter("name");
//		String url = req.getRequestURI();
		String url = req.getServletPath();
		
		switch (url) {
		case "/admin/create_categories":
			categoryService.createCategory(name);
			break;
		case "/admin/update_categories":
			categoryService.updateCategory();
			break;
		

		}

	}

}
