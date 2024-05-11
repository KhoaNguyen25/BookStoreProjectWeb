package com.bookstore.dao;

import java.util.Date;
import java.util.List;

import com.bookstore.entity.Book;

public class BookDAO extends JpaDAO<Book> implements GenericDAO<Book>{

	
	public BookDAO() {}

	@Override
	public Book create(Book book) {
		book.setLastUpdateTime(new Date());
		return super.create(book);
	}

	@Override
	public Book update(Book book) {
		book.setLastUpdateTime(new Date());
		return super.update(book);
	}


	@Override
	public long countWidthNameQuery(String nameQuery) {
		return super.countWidthNameQuery(nameQuery);
	}



	@Override
	public List<Book> listAll() {
		return super.findWidthNameQuery("book.findByAll");
	}

	@Override
	public long count() {
		return super.countWidthNameQuery("book.countBook");
	}

	@Override
	public Book get(Object id) {
		return super.find(Book.class, id);
	}

	@Override
	public void delete(Object id) {
		super.delete(Book.class, id);
		
	}
	public Book findByTitle(String title) {
	List<Book> lsBook =	super.findWidthNameQuery("book.findByTitle", "title", title);
	if(! lsBook.isEmpty()) {
		return lsBook.get(0);
	}
		return null;
	}
	 public List<Book> listByCategory(int categoryId) {
		 return super.findWidthNameQuery("book.findListByCategory", "categoryId", categoryId );
	 }
	 public List<Book> listNewBooks(){
		 return super.findWidthNameQuery("book.listNewBooks", 0, 4);
	 }
	 public List<Book> search(String keyword){
		 return super.findWidthNameQuery("book.search", "keyword", keyword);
	 }
	 
	 public long countWidthByCategory(int categoryId) {
		 return super.countWidthByCategory("book.countWidthByCategory", "categoryId", categoryId);
	 }
}
