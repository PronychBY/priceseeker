package com.senla.pronych.hibernate.service.dts;

import java.util.HashSet;
import java.util.Set;

import com.senla.pronych.hibernate.entity.Role;
import com.senla.pronych.hibernate.entity.User;

public class UserRolesMsg {
	private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private Set<Role> roles = new HashSet<>();
    
    UserRolesMsg(){
    	
    }
    
    UserRolesMsg(User user){
		this.id = user.getId();
		this.name = user.getName();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.roles = user.getRoles();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

    
}
