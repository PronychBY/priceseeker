package com.senla.pronych.hibernate.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.senla.pronych.hibernate.dao.CategoryDao;
import com.senla.pronych.hibernate.entity.Category;
import com.senla.pronych.hibernate.entity.Product;
import com.senla.pronych.hibernate.service.dts.CategoryMsg;
import com.senla.pronych.hibernate.service.dts.ProductMsg;

@Component("categoryService")
@Transactional
public class CategoryService {
	private final CategoryDao dao;
	
	@Autowired
	public CategoryService(CategoryDao dao) {
		this.dao = dao;
	}
	
	public long save(Category obj) {
        return dao.save(obj);
    }
    
    public void delete(long id) {
        dao.delete(id);
    }

    public Category getById(long id) {
    	return dao.getById(id);
    }
    
    public Category getByName(String name) {
    	return dao.getByName(name);
    }
    
    public List<Category> getAll() {
    	return dao.getAll();
    }

    public List<CategoryMsg> getAllToJson() {
    	List<Category> products = dao.getAll();
    	ArrayList<CategoryMsg> categoryMsg = new ArrayList<CategoryMsg>();
    	
    	for (Category prod : products){
    		categoryMsg.add(new CategoryMsg(prod));
    	}
    	
        return categoryMsg;
    }
    
    
    
}
