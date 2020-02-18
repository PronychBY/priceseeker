package com.senla.pronych.hibernate.service.dts;

import com.senla.pronych.hibernate.entity.Category;

public class CategoryMsg {
	long id;
	String name;
	
	public CategoryMsg(){
		
	}
	
	public CategoryMsg(Category obj) {
		this.id = obj.getId();
		this.name = obj.getName();
	}
	
	public CategoryMsg(long id, String name) {
		this.id = id;
		this.name = name;
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
	

}
