package test.com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;

public class CategoryTestDAO {
	private static CategoryDAO categoryDAO;

	@Before
	public void setUp() throws Exception {	
		categoryDAO = new CategoryDAO();
	}

	@After
	public void tearDown() {
		categoryDAO.close();
	}

	@Test
	public void testCreateCategory() {
		Category category = new Category("Java Core");
		Category cag = categoryDAO.create(category);
		assertTrue(cag != null && cag.getCategoryId() > 0);
	}

	
	@Test
	public void testGet() {
		int id = 14;
		Category category = categoryDAO.get(id);
		assertNull(category);
	}
	
	@Test
	public void testUpdateCategory() {
		Category category = new Category("Html");
		category.setCategoryId(11);
		Category cat = categoryDAO.update(category);
		assertEquals(category.getName(), cat.getName());
	}
	
	@Test
	public void testDeleteObject() {
		int categoryId = 14;
		categoryDAO.delete(categoryId);
		Category cat = categoryDAO.get(categoryId);
		assertNull(cat);
	}

	@Test
	public void testListAll() {
		List<Category> listCategory = categoryDAO.listAll();
		listCategory.forEach(c -> System.out.println(c.getName() + ", "));
		assertEquals(3, listCategory.size());
	}

	@Test
	public void testCount() {
	long count = categoryDAO.count();
		assertEquals(7, count);
	}
	@Test
	public void testFindByNameNotNull() {
		Category byName = categoryDAO.findByName("nodejs");
		
		if(byName != null) {
			assertNotNull(byName);
		}else {
			assertNull(byName);
		}
	}

}
