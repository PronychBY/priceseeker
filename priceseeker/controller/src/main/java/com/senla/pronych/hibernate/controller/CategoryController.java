package com.senla.pronych.hibernate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senla.pronych.hibernate.entity.Category;
import com.senla.pronych.hibernate.service.CategoryService;
import com.senla.pronych.hibernate.service.dts.CategoryMsg;

@CrossOrigin("*")
@RestController
@RequestMapping("/category")
@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
public class CategoryController {
	@Autowired
	CategoryService categoryService;
    
	@GetMapping("/list")
    public ResponseEntity<List<CategoryMsg>> list() {
        List < CategoryMsg > listData = categoryService.getAllToJson();
        return ResponseEntity.ok().body(listData);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> get(@PathVariable("id") long id) {
		Category obj = categoryService.getById(id);		
    	
		CategoryMsg categoryMsg = new CategoryMsg();
		categoryMsg.setId(obj.getId());
		categoryMsg.setName(obj.getName());
    	return ResponseEntity.ok().body(categoryMsg);
	}	
	
}
