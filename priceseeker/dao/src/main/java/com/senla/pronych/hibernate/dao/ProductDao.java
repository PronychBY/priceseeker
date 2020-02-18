package com.senla.pronych.hibernate.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.senla.pronych.hibernate.entity.Product;
import com.senla.pronych.hibernate.entity.Category;

@Repository("productDao")
public class ProductDao extends AbstractDao<Product> {
	@Autowired
	private SessionFactory sessionFactory;

	public List<Product> getProductsByCategory(Category category){
		List<Product> products = new ArrayList<Product>();
		
		Query q = sessionFactory.getCurrentSession().createQuery("from Product as prod where prod.category=:paramcat");//  
		q.setParameter("paramcat", category);
		products = q.list();
		
		return products;
	}
}
