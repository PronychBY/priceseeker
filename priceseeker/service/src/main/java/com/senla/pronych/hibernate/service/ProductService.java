package com.senla.pronych.hibernate.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.senla.pronych.hibernate.dao.CategoryDao;
import com.senla.pronych.hibernate.dao.ProductDao;
import com.senla.pronych.hibernate.entity.Category;
import com.senla.pronych.hibernate.entity.Product;
import com.senla.pronych.hibernate.service.dts.CategoryMsg;
import com.senla.pronych.hibernate.service.dts.ProductMsg;


@Component("productService")
@Transactional
public class ProductService {
	private final ProductDao dao;
	
	private final CategoryDao categoryDao;
	
	@Autowired
	public ProductService(ProductDao dao, CategoryDao categoryDao) {
		this.dao = dao;
		this.categoryDao = categoryDao;
	}
	
    public long save(Product product) {
    	return dao.save(product);
    }

    public void update(Product product) {
    	dao.update(product);
    }

    public void delete(long id) {
        dao.delete(id);
    }

    public Product getById(long id) {
    	Product product = dao.getById(id);
        return product;
    }
    
    public List<Product> getAll() {
    	List<Product> products = dao.getAll();
        return products;
    }
    
    public List<Product> getProductsByCategory(Category category) {
    	List<Product> products = dao.getProductsByCategory(category);;
        return products;
    }
    
    private List<ProductMsg> productToProductMsg(List<Product> products){
    	ArrayList<ProductMsg> productMsg = new ArrayList<ProductMsg>();
    	for (Product prod : products){
    		productMsg.add(new ProductMsg(prod));
    	}
        return productMsg;
    }
    
    private Category getCategoryFromMsg(CategoryMsg categoryMsg) {
    	return categoryDao.getById(categoryMsg.getId());
    }
    
    public List<ProductMsg> getProductsByCategoryToJson(CategoryMsg categoryMsg) {
    	List<Product> products = dao.getProductsByCategory(getCategoryFromMsg(categoryMsg));
        return productToProductMsg(products);
    }
    
    public List<ProductMsg> getAllToJson() {
    	List<Product> products = dao.getAll();
        return productToProductMsg(products);
    }
    
    public ProductMsg getByIdToJson(long id) {
    	return new ProductMsg(dao.getById(id));
    }
    
}
