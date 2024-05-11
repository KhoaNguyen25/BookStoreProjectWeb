package com.bookstore.controller.admin.book;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.BookService;

@WebServlet(urlPatterns = {"/admin/list_books","/admin/create_book", "/admin/edit_book", "/admin/updated_book","/admin/delete_book"})
@MultipartConfig(
		fileSizeThreshold = 1024*10, //10KB => 10240/1024 = 10
		maxFileSize = 1024*300,
		maxRequestSize = 1024*1024
)

public class BookServlet extends HttpServlet{

//	private BookService bookService;
	public BookServlet(){}

	private static final long serialVersionUID = -7462942326239929112L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BookService bookService = new BookService(req, resp);
		String urlBook = req.getServletPath();
		String title = req.getParameter("title");
		
		switch (urlBook) {
		case "/admin/list_books":
			bookService.listBooks();
			break;
		case "/admin/create_book":
			bookService.showCreateBook();
			break;
		case "/admin/edit_book":
			bookService.editBook();
			break;
		case "/admin/delete_book":
			bookService.deleteBook();
			break;
		default:
			break;
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BookService bookService = new BookService(req, resp);
		String urlBook = req.getServletPath();
		
		switch (urlBook) {
		case "/admin/create_book":
			bookService.createBook();
			break;
		case "/admin/updated_book":
			bookService.updateBook();
			break;
		default:
			break;
		}
		
	}

}
