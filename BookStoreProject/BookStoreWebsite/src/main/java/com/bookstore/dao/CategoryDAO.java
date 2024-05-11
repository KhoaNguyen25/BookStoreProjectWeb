package com.bookstore.dao;

import java.util.List;

import com.bookstore.entity.Category;

public class CategoryDAO extends JpaDAO<Category> implements GenericDAO<Category> {

	public CategoryDAO(){}

	@Override
	public Category create(Category category) {
		
		return super.create(category);
	}

	@Override
	public Category get(Object id) {
		
		return super.find(Category.class, id);
	}

	
	@Override
	public Category update(Category entity) {
		return super.update(entity);
	}

	@Override
	public void delete(Object id) {
		super.delete(Category.class, id);
		
	}

	@Override
	public List<Category> listAll() {
		return super.findWidthNameQuery("Category.findAll");
	}

	@Override
	public long count() {
		return super.countWidthNameQuery("Category.count");
	}
	public Category findByName(String name) {
	
	List<Category> listCategory = super.findWidthNameQuery("Category.findByName", "name",name);
	if(listCategory != null && listCategory.size() > 0) {
		return listCategory.get(0);
	}
		return null;
	}
}
