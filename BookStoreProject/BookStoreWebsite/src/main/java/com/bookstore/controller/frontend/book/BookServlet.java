package com.bookstore.controller.frontend.book;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.BookService;

@WebServlet(urlPatterns = {"/view_category","/view_book","/search_book"})
public class BookServlet extends HttpServlet {
	private BookService bookService;
	
	private static final long serialVersionUID = 4130474996975682858L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		bookService = new BookService(req, resp);
		String urlCategory = req.getServletPath();
		
		switch (urlCategory) {
		case "/view_category":
			bookService.listBooksByCategory();
			break;
		case "/view_book":
			bookService.viewBookDetail();
			break;
		case "/search_book":
			bookService.search();
			break;
		default:
			break;
		}
	}

}
