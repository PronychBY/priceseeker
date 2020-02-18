package com.senla.pronych.hibernate.service.dts;

import java.util.Set;

import com.senla.pronych.hibernate.entity.Category;
import com.senla.pronych.hibernate.entity.Product;

public class ProductMsg {

	private long id;
	private String name;
    private long categoryId;
    private String categoryName;
	private Category category;
	
	public ProductMsg(){
		
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public ProductMsg(long id, String name) {
		this.id = id;
		this.name = name;
	}
	public ProductMsg(long id, String name,long categoryId) {
		this.id = id;
		this.name = name;
		this.categoryId = categoryId;
	}
	public ProductMsg(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.categoryId = product.getCategory().getId();
		this.categoryName = product.getCategory().getName();
		//this.category = product.getCategory();
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
