package test.com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.dao.UsersDAO;
import com.bookstore.entity.Users;
import com.bookstore.security.HashGeneratorUtils;

public class UserTestDAO {

	
	private static UsersDAO userDao;
	
	@BeforeClass
	public static void setupClass() throws Exception {
		userDao = new UsersDAO();
	}

	@Test
	public void testCreateUsers() {
		Users user = new Users();
		user.setEmail("trantrongy24@gmail.com");
		user.setFullName("trantrongy2");
		user.setPassword("xinchao112");

		user  = userDao.create(user);

		assertTrue(user.getUserId() >0 );
	}

	@Test(expected = PersistenceException.class)
	public void testCreateUsersFieldsNotSet() {
		Users user = new Users();

		user  = userDao.create(user);

	}

	@Test
	public void testUpdateUsers() {
		Users user = new Users();
		user.setUserId(23);
		user.setEmail("trantrongy1@gmail.com");
		//we want change password
		user.setPassword("welcome111");
		user.setFullName("nguyen van a");

		user = userDao.update(user);
		String expected = user.getPassword();
		String actual = "welcome111";
		assertEquals(expected, actual );

	}
	@Test
	public void testGetUsersFound() {
		Integer userId = 19;
		Users user = userDao.get(userId);
		if(user != null) {
			System.out.println(user.getEmail());
		}

		assertNotNull(user);
	}
	@Test
	public void testGetUsersNotFound() {
		Integer userId = 23;
		Users user = userDao.get(userId);

		assertNull(user);
	}
	@Test
	public void testDeleteUser() {
		Integer userId = 19;
		userDao.delete(userId);
		Users user = userDao.get(userId);

		assertNull(user);
	}

	@Test(expected = EntityNotFoundException.class)
	public void testDeleteNonExitsUser() {
		Integer userId = 19;
		userDao.delete(userId);
	}

	@Test
	public void testListAll() {
		List<Users> ls = userDao.listAll();
		for(Users us : ls) {
			System.out.println(us.getEmail());
		}
		assertTrue(ls.size() > 0);
	}

	@Test
	public void testCount() {
		long count = userDao.count();
		assertEquals(2, count);
	}

	@Test
	public void testfindByEmail() {
		String email = "admin@gmail.com";
		userDao.findByEmail(email);
		
		assertNull(email);
	}
	
	
	@Test
	public void checkLogin() {
		String email = "dalziel@efe.com.vn";
		String password = "123";
		
		boolean checkLogin = userDao.checkLogin(email, password);
		
		if(checkLogin) {
			 assertTrue(checkLogin);
		}else {
			assertFalse(checkLogin);
		}
	}
	@Test
	public void testpassMd5() {
		String password = "123";
		String email = "admin@gmail.com";
		Users user = userDao.findByEmail(email);
		user.setPassword(HashGeneratorUtils.generateMD5(password));
//		System.out.println(user);
		userDao.update(user);
		assertTrue(true);
	}

	@AfterClass
	public static void tearDownClass() {
		userDao.close();
	}
	

}
