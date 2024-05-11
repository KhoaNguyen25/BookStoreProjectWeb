package com.bookstore.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.entity.Users;
import com.bookstore.security.HashGeneratorUtils;

public class UsersDAO extends JpaDAO<Users> implements GenericDAO<Users> {

	public UsersDAO() {}

	@Override
	public Users create(Users u) {
		String encryptedPassword = HashGeneratorUtils.generateMD5(u.getPassword());
		u.setPassword(encryptedPassword);	
		return super.create(u);
	}

	@Override
	public Users update(Users u) {
		return super.update(u);
	}

	@Override
	public List<Users> listAll() {

		return super.findWidthNameQuery("Users.findAll");
	}

	@Override
	public Users get(Object id) {
		// TODO Auto-generated method stub
		return super.find(Users.class, id);
	}

	@Override
	public void delete(Object id) {
		super.delete(Users.class, id);

	}

	@Override
	public long count() {
		return super.countWidthNameQuery("Users.count");
	}
	
	public Users findByEmail(String email) {
		  List<Users> listUser = super.findWidthNameQuery("Users.findByEmail", "email",email);
		  if(listUser != null && listUser.size() > 0) {
			  return listUser.get(0);
		  }
		  return null;
	}
	public boolean checkLogin(String email, String password) {
		Map<String, Object> paramteres = new HashMap<>();
		String encryptedPassword = HashGeneratorUtils.generateMD5(password);
		paramteres.put("email", email);
		paramteres.put("password", encryptedPassword);
		
		List<Users> listUser = super.findWidthNameQuery("Users.checkLogin", paramteres);
		
		if(listUser.size() == 1) {
			
			return true;
		}
		return false;
	}
}
