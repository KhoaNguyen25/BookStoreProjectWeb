package test.com.bookstore.entity;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.bookstore.entity.Category;

public class CategoriesTest {
	public static void main(String[] args) {
		Category category = new Category("python");


		EntityManagerFactory createEntityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		EntityManager entityManager = createEntityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(category);
		entityManager.getTransaction().commit();

		entityManager.close();
		createEntityManagerFactory.close();
		System.out.println("a category object");

	}
}
