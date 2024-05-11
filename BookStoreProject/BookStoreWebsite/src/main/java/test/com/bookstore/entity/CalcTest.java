package test.com.bookstore.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalcTest {

	@Test
	public void test() {
		Calculator c = new Calculator();
		int a = 1234;
		int b = 5678;

		int result = c.add(a, b);
		int ex = 6912;
		assertEquals(result, ex);
	}

}
