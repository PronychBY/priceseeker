package com.senla.pronych.hibernate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senla.pronych.hibernate.entity.User;
import com.senla.pronych.hibernate.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

    @GetMapping("/list")
    public ResponseEntity<List<User>> list() {
    	List < User > theUsers = userService.getAll();
        return ResponseEntity.ok().body(theUsers);
    }
    
	@RequestMapping("/user/{id}")
	public ResponseEntity<User> get(@PathVariable("id") long id) {
		User user = userService.getById(id);		
		return ResponseEntity.ok().body(user); 
	}
	
	@RequestMapping("/update")
	public ResponseEntity<?> update(@RequestBody User user) {
		userService.update(user);
		return ResponseEntity.ok().body("User updated with id:"+user.getId()); 
	}
	    
	@RequestMapping("/isadmin")
	public ResponseEntity<?> isUserAdmin(@RequestBody User user) {
		return ResponseEntity.ok().body(userService.isUserAdmin(user)); 
	}
}

