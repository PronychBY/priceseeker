package com.senla.pronych.hibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senla.pronych.hibernate.dao.RoleDao;
import com.senla.pronych.hibernate.entity.Role;
import com.senla.pronych.hibernate.entity.RoleName;

@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao;

    @Transactional
    public List < Role > getAll() {
        return roleDao.getAll();
    }

    @Transactional
    public long save(Role role) {
    	return roleDao.save(role);
    }

    @Transactional
    public Role getById(long theId) {
        return roleDao.getById(theId);
    }

    @Transactional
    public Role getByName(RoleName name) {
    	return roleDao.getByName(name);
    }
    
    @Transactional
    public Role getByName(String name) {
    	return roleDao.getByName(name);
    }
    
    @Transactional
    public void delete(long theId) {
    	roleDao.delete(theId);
    }
}
