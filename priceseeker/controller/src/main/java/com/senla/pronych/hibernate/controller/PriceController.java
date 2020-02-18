package com.senla.pronych.hibernate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senla.pronych.hibernate.entity.Price;
import com.senla.pronych.hibernate.service.PriceService;
import com.senla.pronych.hibernate.service.dts.PriceMsg;
import com.senla.pronych.hibernate.service.dts.ProductMsg;

@CrossOrigin("*")
@RestController
@RequestMapping("/price")
public class PriceController {

	@Autowired
	PriceService priceService;

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/list")
    public ResponseEntity<List<PriceMsg>> list() {
    	List < PriceMsg > listData = priceService.getAllToJson();
        return ResponseEntity.ok().body(listData);
    }
	
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/get/{id}")
	public ResponseEntity<?> get(@PathVariable("id") long id) {
		PriceMsg priceMsg = priceService.getByIdToJson(id);		
    	return ResponseEntity.ok().body(priceMsg);
	}	
	
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/byproduct")//p7 price period
    public ResponseEntity<List<PriceMsg>> priceByProduct(@RequestBody ProductMsg productMsg) {
    	List < PriceMsg > listData = priceService.getPriceByProductToJson(productMsg);
        return ResponseEntity.ok().body(listData);
    }
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		priceService.delete(id);
		return ResponseEntity.ok().body("Product deleted");
	}	
    
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("/add")
    public ResponseEntity<String> add(@RequestBody PriceMsg priceMsg) {
		Price price = priceService.addNewPrice(priceMsg);
        return ResponseEntity.ok().body("Price with id="+price.getId()+" added successfully!");
    }	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("/addcurentdate")
    public ResponseEntity<String> addCurentDate(@RequestBody PriceMsg priceMsg) {
		Price price = priceService.addNewPriceCurentDate(priceMsg);
        return ResponseEntity.ok().body("Price with id="+price.getId()+" added successfully!");
    }	

	
	
	
}
