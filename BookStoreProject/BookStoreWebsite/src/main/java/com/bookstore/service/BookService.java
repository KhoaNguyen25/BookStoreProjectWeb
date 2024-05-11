package com.bookstore.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookService {
	private BookDAO bookDao;
	private CategoryDAO categoryDao;

	private HttpServletRequest req;
	private HttpServletResponse resp;

	public BookService(HttpServletRequest req, HttpServletResponse resp) {
	
		this.req = req;
		this.resp = resp;
		bookDao = new BookDAO();
		categoryDao = new CategoryDAO();
	}

	public void listBooks() throws ServletException, IOException {
		listBooks(null);
	}

	public void listBooks(String message) throws ServletException, IOException {
		List<Book> listBook = bookDao.listAll();

		req.setAttribute("listBooks", listBook);
		if (message != null) {
			req.setAttribute("message", message);
		}
		req.getRequestDispatcher("book_list.jsp").forward(req, resp);
	}

	public void showCreateBook() throws ServletException, IOException {
		List<Category> lsCategory = categoryDao.listAll();
		req.setAttribute("listCategory", lsCategory);
		req.getRequestDispatcher("book_form.jsp").forward(req, resp);
	}

	public void createBook() throws IOException, ServletException {

		String title = req.getParameter("title");
		Book existBook = bookDao.findByTitle(title);
		if (existBook != null) {
			String message = "Could not create new book because the title '" + title + "' already exists.";
			req.setAttribute("message", message);
			req.getRequestDispatcher("message.jsp").forward(req, resp);
			return;
		}
		Book book = new Book();
		readFileBook(book);
		Book book2 = bookDao.create(book);

		if (book2.getBookId() > 0) {
			String message = "A new book has been created successfully.";
			listBooks(message);
		}

	}

	public void readFileBook(Book book) throws IOException, ServletException {
		DateFormat date = new SimpleDateFormat("mm/dd/YYYY");
		Integer categoryId = Integer.parseInt(req.getParameter("category"));

		String author = req.getParameter("author");
		String isbn = req.getParameter("isbn");
		float price = Float.parseFloat(req.getParameter("price"));
		String description = req.getParameter("description");
		Date publishDate = null;
		try {
			publishDate = date.parse(req.getParameter("publishDate"));
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		// check title exists
		String title = req.getParameter("title");
		Category category = categoryDao.get(categoryId);

		Part part = req.getPart("image");
		if (part != null & part.getSize() > 0) {
			long size = part.getSize();
			byte[] imageByte = new byte[(int) size];
			InputStream inputStream = part.getInputStream();
			inputStream.read(imageByte);
			inputStream.close();
			book.setImage(imageByte);
		}

		book.setTitle(title);
		book.setAuthor(author);
		book.setCategory(category);
		book.setIsbn(isbn);
		book.setPublishDate(publishDate);
		book.setPrice(price);
		book.setDescription(description);
	}

	public void editBook() throws ServletException, IOException {
		Integer bookId = Integer.parseInt(req.getParameter("id"));
		Book book = bookDao.get(bookId);
		String url_form = null;
		if (book == null) {
			String message = "Could not find category with: " + bookId;
			url_form = "message.jsp";
			req.setAttribute("message", message);
		} else {
			List<Category> lsCategory = categoryDao.listAll();
			req.setAttribute("listCategory", lsCategory);
			req.setAttribute("book", book);
			url_form = "book_form.jsp";
		}
		req.getRequestDispatcher(url_form).forward(req, resp);
	}

	public void updateBook() throws IOException, ServletException {
		Integer bookId = Integer.parseInt(req.getParameter("bookId"));
		Book exitsBook = bookDao.get(bookId);

		String title = req.getParameter("title");
		Book bookByTitle = bookDao.findByTitle(title);
		if (!exitsBook.equals(bookByTitle) && bookByTitle != null) {
			listBooks("Could not update book. Book width name " + title + " aleady exists.");
			return;
		}
		readFileBook(exitsBook);
		bookDao.update(exitsBook);
		listBooks("You are updated book Successfully");

	}

	public void deleteBook() throws ServletException, IOException {
		int bookId = Integer.parseInt(req.getParameter("id"));
		Book book = bookDao.get(bookId);
		if (book != null) {
			bookDao.delete(bookId);
			listBooks("The book has been deleted successfully");
		} else {
			String message = "Could not find book with ID " + bookId + ", or it might have been deleted";
			req.setAttribute("message", message);
			req.getRequestDispatcher("message.jsp").forward(req, resp);
		}
	}

	// frontend

	public void listBooksByCategory() throws ServletException, IOException {
		Integer categoryId = Integer.parseInt(req.getParameter("id"));
		Category category = categoryDao.get(categoryId);
		if (category == null) {
			String message = "Sorry, the category ID: " + categoryId + " is not available";
			req.setAttribute("message", message);
			req.getRequestDispatcher("frontend/message.jsp").forward(req, resp);
			return;
		}

		List<Book> listBook = bookDao.listByCategory(categoryId);

		if (listBook != null) {

			req.setAttribute("listBook", listBook);
			req.setAttribute("category", category);
			req.getRequestDispatcher("frontend/book_view.jsp").forward(req, resp);
		}
	}

	public void viewBookDetail() throws ServletException, IOException {
		Integer bookId = Integer.parseInt(req.getParameter("id"));
		Book book = bookDao.get(bookId);

		if (book != null) {
			req.setAttribute("book", book);
			req.getRequestDispatcher("frontend/book_detail.jsp").forward(req, resp);
		} else {
			String message = "Sorry, the book with ID " + bookId + " is not available.";
			req.setAttribute("message", message);
			req.getRequestDispatcher("frontend/message.jsp").forward(req, resp);
			return;
		}
	}

	public void search() throws IOException, ServletException {
		String keywork = req.getParameter("keywork");
		List<Book> result = null;
		if (keywork.equals("")) {
			result = bookDao.listAll();
		} else {
			result = bookDao.search(keywork);
		}
		req.setAttribute("keywork", keywork);
		req.setAttribute("result", result);
		String url_search = "frontend/result_search.jsp";
		req.getRequestDispatcher(url_search).forward(req, resp);
	}

}
