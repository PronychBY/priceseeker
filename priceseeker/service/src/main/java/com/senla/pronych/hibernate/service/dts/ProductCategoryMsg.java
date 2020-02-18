package com.senla.pronych.hibernate.service.dts;

import java.util.HashSet;
import java.util.Set;

import com.senla.pronych.hibernate.entity.Product;
import com.senla.pronych.hibernate.entity.Role;

public class ProductCategoryMsg {
	private long id;
	private String name;
    private Set<Role> roles = new HashSet<>();
    
	private long categoryId;
    private String categoryName;
	
	
	public ProductCategoryMsg(){
		
	}
	public ProductCategoryMsg(long id, String name) {
		this.id = id;
		this.name = name;
	}
	public ProductCategoryMsg(long id, String name,long categoryId) {
		this.id = id;
		this.name = name;
		this.categoryId = categoryId;
	}
	public ProductCategoryMsg(Product product) {
		this.id = product.getId();
		this.name = product.getName();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}



}
