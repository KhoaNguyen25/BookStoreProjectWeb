package test.com.bookstore.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bookstore.dao.BookDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookDAOTest {
	private static BookDAO bookDao;

	@Before
	public void setUp() throws Exception {
		
		bookDao = new BookDAO();
	}

	@Test
	public void testCreateBook() throws ParseException, IOException {
		Book book = new Book();
		Category cate = new Category();
		cate.setCategoryId(12);
		book.setCategory(cate);
		book.setTitle("Effective Java (2nd Edition) copy");
		book.setAuthor("Joshua Bloch");
		book.setDescription(
				"Are you looking for a deeper understanding of the Java™ programming language so that you can write code that is clearer, more correct, more robust, and more reusable? Look no further! Effective Java™, Second Edition, brings together seventy-eight indispensable programmer’s rules of thumb: working, best-practice solutions for the programming challenges you encounter every day.\r\n"
						+ " \r\n"
						+ "This highly anticipated new edition of the classic, Jolt Award-winning work has been thoroughly updated to cover Java SE 5 and Java SE 6 features introduced since the first edition. Bloch explores new design patterns and language idioms, showing you how to make the most of features ranging from generics to enums, annotations to autoboxing.\r\n"
						+ " \r\n"
						+ "Each chapter in the book consists of several “items” presented in the form of a short, standalone essay that provides specific advice, insight into Java platform subtleties, and outstanding code examples. The comprehensive descriptions and explanations for each item illuminate what to do, what not to do, and why.\r\n"
						+ " \r\n" + "Highlights include:\r\n"
						+ "New coverage of generics, enums, annotations, autoboxing, the for-each loop, varargs, concurrency utilities, and much more\r\n"
						+ "Updated techniques and best practices on classic topics, including objects, classes, libraries, methods, and serialization\r\n"
						+ "How to avoid the traps and pitfalls of commonly misunderstood subtleties of the language\r\n"
						+ "Focus on the language and its most fundamental libraries: java.lang, java.util, and, to a lesser extent, java.util.concurrent and java.io\r\n"
						+ "Simply put, Effective Java™, Second Edition, presents the most practical, authoritative guidelines available for writing efficient, well-designed programs.\r\n"
						+ "");
		book.setIsbn("0321356683");
		String imagePath = "D:\\javaservlet\\Bài 54 Book Management\\dummy-data-books\\books\\Effective Java.jpg";
		// đọc tất cả các byte
		byte[] imageByte = Files.readAllBytes(Paths.get(imagePath));
		book.setImage(imageByte);
		book.setPrice(38.87f);
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date datePublish = dateFormat.parse("01/28/2008");
		book.setPublishDate(datePublish);
		Book result = bookDao.create(book);
		assertTrue(result.getBookId() > 0);
	}

	@Test
	public void testUpdateBook() throws IOException, ParseException {
		Book book = new Book();
		// để update content thì phải lấy id
		book.setBookId(37); // đầu tiền phải có id, id = 37

		Category cate = new Category();
		cate.setCategoryId(13);
		book.setCategory(cate);
		book.setTitle("Effective Java (3nd Edition)");// unique
		book.setAuthor("Joshua Bloch");
		book.setDescription(
				"Are you looking for a deeper understanding of the Java™ programming language so that you can write code that is clearer, more correct, more robust, and more reusable? Look no further! Effective Java™, Second Edition, brings together seventy-eight indispensable programmer’s rules of thumb: working, best-practice solutions for the programming challenges you encounter every day.\r\n"
						+ " \r\n"
						+ "This highly anticipated new edition of the classic, Jolt Award-winning work has been thoroughly updated to cover Java SE 5 and Java SE 6 features introduced since the first edition. Bloch explores new design patterns and language idioms, showing you how to make the most of features ranging from generics to enums, annotations to autoboxing.\r\n"
						+ " \r\n"
						+ "Each chapter in the book consists of several “items” presented in the form of a short, standalone essay that provides specific advice, insight into Java platform subtleties, and outstanding code examples. The comprehensive descriptions and explanations for each item illuminate what to do, what not to do, and why.\r\n"
						+ " \r\n" + "Highlights include:\r\n"
						+ "New coverage of generics, enums, annotations, autoboxing, the for-each loop, varargs, concurrency utilities, and much more\r\n"
						+ "Updated techniques and best practices on classic topics, including objects, classes, libraries, methods, and serialization\r\n"
						+ "How to avoid the traps and pitfalls of commonly misunderstood subtleties of the language\r\n"
						+ "Focus on the language and its most fundamental libraries: java.lang, java.util, and, to a lesser extent, java.util.concurrent and java.io\r\n"
						+ "Simply put, Effective Java™, Second Edition, presents the most practical, authoritative guidelines available for writing efficient, well-designed programs.\r\n"
						+ "");
		book.setIsbn("0321356683");
		String imagePath = "D:\\javaservlet\\Bài 54 Book Management\\dummy-data-books\\books\\Effective Java.jpg";
		// đọc tất cả các byte
		byte[] imageByte = Files.readAllBytes(Paths.get(imagePath));
		book.setImage(imageByte);
		book.setPrice(38.87f);
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date datePublish = dateFormat.parse("04/06/2024");
		book.setPublishDate(datePublish);
		Book result = bookDao.update(book);
		assertEquals(book.getTitle(), result.getTitle());
	}

	@Test(expected = EntityNotFoundException.class)
	public void testDeleteFail() {
		int deleteId = 100;
		bookDao.delete(deleteId);
	}

	@Test()
	public void testDeleteSuccess() {
		int deleteId = 37;
		bookDao.delete(deleteId);
		assertTrue(true);
	}

	@Test()
	public void testGetList() {
		int getList = 35;
		Book result = bookDao.get(getList);

		if (result != null) {
			assertNotNull(result);
		} else {
			assertNull(result);
		}
	}

	@Test()
	public void testCountBook() {
		long count = bookDao.count();
		assertTrue(count > 0);
	}

	@Test()
	public void testListAllBook() {
		List<Book> listBook = bookDao.listAll();
		assertTrue(listBook.size() > 0);
	}

	@Test()
	public void testFindByTitleNotExits() {
		Book listBook = bookDao.findByTitle("Effective Java (2nd Edition)s");
		assertNull(listBook);
	}

	@Test()
	public void testFindByTitleExits() {
		Book listBook = bookDao.findByTitle("Effective Java (2nd Edition)");
		System.out.println(listBook.getAuthor());
		assertNotNull(listBook);
	}

	@Test()
	public void testListByCategory() {
		int categoryId = 14;
		List<Book> listBook = bookDao.listByCategory(categoryId);
		assertTrue(listBook.size() > 0);
	}

	@Test()
	public void testListNewBooks() {
		List<Book> ls = bookDao.listNewBooks();
		for(Book item : ls) {
			System.out.println(item.getTitle() + "-" + item.getPublishDate());
		}
		assertEquals(4, ls.size());
	}
	@Test()
	public void testSearchBookInTitle() {
		List<Book> ls = bookDao.search("H");
		for(Book item : ls) {
			System.out.println(item.getTitle() + "-" + item.getPublishDate());
		}
		assertTrue(ls.size() > 0);
	}
	@Test()
	public void testSearchBookInAuthor() {
		List<Book> ls = bookDao.search("Kathy");
		for(Book item : ls) {
			System.out.println(item.getAuthor() + "-" + item.getPublishDate());
		}
		assertTrue(ls.size() > 0);
	}
	@Test()
	public void testSearchBookInDescription() {
		List<Book> ls = bookDao.search("The book covers lambdas");
		for(Book item : ls) {
			System.out.println(item.getDescription() + "-" + item.getPublishDate());
		}
		assertTrue(ls.size() > 0);
	}
	@Test()
	public void testCountWidthByCategory() {
		int categoryId = 13;
		long countCategory = bookDao.countWidthByCategory(categoryId);
		assertTrue(countCategory  == 6);
	}

	@After
	public void tearDown() throws Exception {
		bookDao.close();
	}

}
