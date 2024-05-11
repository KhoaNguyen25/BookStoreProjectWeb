package test.com.bookstore.entity;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.bookstore.entity.Users;

public class UsersTest {
	public static void main(String[] args) {
		Users user = new Users();
		user.setEmail("nguyenvana@gmail.com");
		user.setFullName("nguyen van a");
		user.setPassword("xinchao1");

		EntityManagerFactory createEntityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		EntityManager entityManager = createEntityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();

		entityManager.close();
		createEntityManagerFactory.close();
		System.out.println("a users object");

	}
}
