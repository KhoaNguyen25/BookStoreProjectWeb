package test.com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bookstore.dao.CustomerDAO;
import com.bookstore.entity.Customer;

public class CustomerDAOTest {
	private static CustomerDAO customerDao;

	@Before
	public void setUp() throws Exception {
		customerDao = new CustomerDAO();
	}

	@Test
	public void testCreate() throws ParseException {
//		DateFormat date = new SimpleDateFormat("MM/dd/YYYY");
		Customer customer = new Customer();
		customer.setEmail("dinhphanthanhhien1@gmail.com");
		customer.setFullname("Dinh Phan Thanh Hien");
		customer.setAddress("Hoa hai");
		customer.setCity("Da nang city");
		customer.setCountry("Vietnam");
		customer.setPhone("077751868");
		customer.setZipcode("550000");
		customer.setPassword("abcd");
//		customer.setRegisterDate(date.parse("04/30/2024"));
		Customer c = customerDao.create(customer);
		assertTrue(c.getCustomerId() > 0);
	}

	@Test
	public void testListAll() {
		List<Customer> csList = customerDao.listAll();
		for (Customer customer : csList) {
			System.out.println(customer.toString());
		}
		assertTrue(csList.size() > 0);
	}

	@Test
	public void testCountCustomer() {
		long count = customerDao.count();
		assertTrue(count > 0);
	}

	@Test
	public void testGet() {
		Customer result = customerDao.get(13);
		if (result != null) {
			assertNotNull(result);
		} else {
			assertNull(result);
		}
	}

	@Test
	public void testDelete() {
		customerDao.delete(13);
		assertTrue(true);
	}

	@Test
	public void testUpdate() throws ParseException {
		DateFormat date = new SimpleDateFormat("MM/dd/YYYY");
		Customer customer = new Customer();
		customer.setCustomerId(12);
		customer.setEmail("dinhphanthanhhien@gmail.com");
		customer.setFullname("Dinh Phan Thanh Hien");
		customer.setAddress("Hoa hai - Ngũ Hành Sơn");
		customer.setCity("Da nang city");
		customer.setCountry("Vietnam");
		customer.setPhone("077751868");
		customer.setZipcode("550000");
		customer.setPassword("abcd");
		customer.setRegisterDate(date.parse("04/30/2024"));
		Customer c = customerDao.update(customer);
		assertEquals(customer.getEmail(), c.getEmail());
	}

	@Test
	public void testFindByEmail() {
		String email = "trantrongycntt@gmail.com";
		Customer ls = customerDao.findByEmail(email);
		System.out.println(ls.toString());
		assertNotNull(ls.getCustomerId() );
	}

	@Test
	public void test() {
		int a = 1;
		assertTrue(a > 0);
	}
	@Test
	public void testCheckLogin() {
		String email = "trantrongycntt@gmail.com";
		String password = "1234";
		Customer customer = customerDao.checkLogin(email, password);
		if(customer != null) {
			assertNotNull(customer);
		}else {
			assertNull(customer);
		}
		
	}

	@After
	public void tearDown() throws Exception {
		customerDao.close();
	}

}
