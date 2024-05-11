package com.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.entity.Customer;
import com.bookstore.security.HashGeneratorUtils;

public class CustomerDAO extends JpaDAO<Customer> implements GenericDAO<Customer> {

	@Override
	public Customer create(Customer customer) {
		customer.setRegisterDate(new Date());
		String encryptedPassword = HashGeneratorUtils.generateMD5(customer.getPassword());
		customer.setPassword(encryptedPassword);
		return super.create(customer);
	}

	@Override
	public Customer get(Object id) {
		return super.find(Customer.class, id);
	}

	@Override
	public void delete(Object id) {
		super.delete(Customer.class, id);
	}

	@Override
	public List<Customer> listAll() {
		return super.findWidthNameQuery("Customer.listAll");
	}

	@Override
	public long count() {
		return super.countWidthNameQuery("Customer.count");
	}

	@Override
	public Customer update(Customer customer) {
		String encryptedPassword = HashGeneratorUtils.generateMD5(customer.getPassword());
		customer.setPassword(encryptedPassword);
		return super.update(customer);
	}

	public Customer findByEmail(String email) {
		List<Customer> lsCustomer = findWidthNameQuery("Customer.findByEmail", "email", email);

		if (!lsCustomer.isEmpty()) {
			return lsCustomer.get(0); // lấy ra vị trí đầu tiên
		}
		return null;
	}

	public Customer checkLogin(String email, String password) {
		Customer customer = new Customer();
		Map<String, Object> paramerter = new HashMap<String, Object>();
		String encryptedPassword = HashGeneratorUtils.generateMD5(password);
		paramerter.put("email", email);
		paramerter.put("password", encryptedPassword);
		List<Customer> listCustomer = super.findWidthNameQuery("Customer.checkLogin", paramerter);
		System.out.println(listCustomer);
		if (!listCustomer.isEmpty()) {
			return listCustomer.get(0);
		}
		return null;
	}

}
