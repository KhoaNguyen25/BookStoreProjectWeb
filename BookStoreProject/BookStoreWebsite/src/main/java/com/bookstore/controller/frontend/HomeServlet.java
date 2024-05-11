package com.bookstore.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Book;

@WebServlet("")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDAO categoryDAO;
	private BookDAO bookDAO;

	public HomeServlet() {
		categoryDAO = new CategoryDAO();
		bookDAO = new BookDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<Book> listBook = bookDAO.listNewBooks();
		request.setAttribute("listBook", listBook);
		
		String homepage = "frontend/index.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(homepage);
		rd.forward(request, response);
	}

}
