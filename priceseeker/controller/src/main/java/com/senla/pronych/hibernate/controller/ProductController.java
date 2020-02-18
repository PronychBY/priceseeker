package com.senla.pronych.hibernate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senla.pronych.hibernate.entity.Product;
import com.senla.pronych.hibernate.service.CategoryService;
import com.senla.pronych.hibernate.service.ProductService;
import com.senla.pronych.hibernate.service.dts.CategoryMsg;
import com.senla.pronych.hibernate.service.dts.ProductMsg;

@CrossOrigin("*")
@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
    
	//@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/list")//p1 list of product
    public ResponseEntity<List<ProductMsg>> list() {
    	List < ProductMsg > listData = productService.getAllToJson();
        //return ResponseEntity.ok().header("Access-Control-Allow-Origin", "*").body(listData);
        return ResponseEntity.ok().body(listData);
    }
	
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/get/{id}")
	public ResponseEntity<?> get(@PathVariable("id") long id) {
		ProductMsg productMsg = productService.getByIdToJson(id);		
    	return ResponseEntity.ok().body(productMsg);
	}	
	
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@RequestMapping("/bycategory")//p2 search by category
    public ResponseEntity<List<ProductMsg>> listByCategory(@RequestBody CategoryMsg categoryMsg) {
    	List < ProductMsg > listData = productService.getProductsByCategoryToJson(categoryMsg);
        return ResponseEntity.ok().body(listData);
    }
	
	//p3 add update delete product
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		productService.delete(id);
		return ResponseEntity.ok().body(id);
	}	
    
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/add")
    public ResponseEntity<ProductMsg> add(@RequestBody ProductMsg productMsg) {
        Product product = new Product(productMsg.getName());
        if (productMsg.getCategoryId() == 0) {
        	if (productMsg.getCategory().getId() != 0) {
                product.setCategory(categoryService.getById(productMsg.getCategory().getId()));
        	}
        	else {
                product.setCategory(categoryService.getById(productMsg.getCategoryId()));
        	}
        }
        else {
            product.setCategory(categoryService.getById(productMsg.getCategoryId()));
        }
        
        long newId = productService.save(product);
        
        //return ResponseEntity.ok().body("Product with id="+newId+" added successfully!");
        //return new ResponseEntity<>("Hello World!", HttpStatus.OK);
        return ResponseEntity.ok().body(productMsg);
    }	
	
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody ProductMsg productMsg) {
        Product product = productService.getById(productMsg.getId());
        product.setCategory(categoryService.getById(productMsg.getCategoryId()));
        productService.update(product);
 		
        return ResponseEntity.ok().body("Product with id="+productMsg.getId()+" updated successfully!");
    }	
	
	
	
}
