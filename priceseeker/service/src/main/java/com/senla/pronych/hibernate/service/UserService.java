package com.senla.pronych.hibernate.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.senla.pronych.hibernate.dao.UserDao;
import com.senla.pronych.hibernate.entity.User;

@Component("userService")
@Transactional
public class UserService {
	private final UserDao dao;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	public UserService(UserDao dao) {
		this.dao = dao;
	}
	
	public long save(User user) {
        return dao.save(user);
    }
	
	public void update(User user) {
        dao.update(user);
    }
    
    public void delete(long id) {
        dao.delete(id);
    }

    public User getById(long id) {
		User user = dao.getById(id);
        return user;
    }
    
    public User getByName(String name) {
		User user = dao.getByName(name);
        return user;
    }
    
    public List<User> getAll() {
    	List<User> users = dao.getAll();
        return users;
    }

    public boolean isUserAdmin(User user) {
    	return user.getRoles().contains(roleService.getByName("ROLE_ADMIN"));
    }
    
    
}
