package com.bookstore.security;

import com.bookstore.dao.UsersDAO;
import com.bookstore.entity.Users;

public class HashGeneratorTest {
	public static void main(String[] args) {
		UsersDAO userDao = new UsersDAO();
		Users u = userDao.findByEmail("trantrongy24@gmail.com");
		
		String pass = "1234";
		String kq = HashGeneratorUtils.generateMD5(pass);
		System.out.println(u);
	}
}
