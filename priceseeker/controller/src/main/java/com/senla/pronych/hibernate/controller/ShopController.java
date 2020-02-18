package com.senla.pronych.hibernate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senla.pronych.hibernate.entity.Shop;
import com.senla.pronych.hibernate.service.ShopService;

@RestController
@RequestMapping("/Shop")
public class ShopController {
	@Autowired
	ShopService shopService;

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/list")//p1 list of product
    public ResponseEntity<List<Shop>> list() {
    	List < Shop > listData = shopService.getAll();
        return ResponseEntity.ok().body(listData);
    }
	
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/get/{id}")
	public ResponseEntity<?> get(@PathVariable("id") long id) {
		Shop obj = shopService.getById(id);		
    	return ResponseEntity.ok().body(obj);
	}	

	
	//p3 add update delete product
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		shopService.delete(id);
		return ResponseEntity.ok().body("Shop deleted");
	}	
    
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody Shop shop) {
        long newId = shopService.save(shop);
        return ResponseEntity.ok().body("Product with id="+newId+" added successfully!");
    }	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody Shop shop) {
        shopService.update(shop);
        return ResponseEntity.ok().body("Shop with id="+shop.getId()+" updated successfully!");
    }	
	
	
	
}
