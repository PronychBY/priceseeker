package com.senla.pronych.hibernate.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.senla.pronych.hibernate.entity.Role;
import com.senla.pronych.hibernate.entity.RoleName;

@Repository("roleDao")
public class RoleDao extends AbstractDao<Role> {
	@Autowired
	private SessionFactory sessionFactory;
	
	public Role getByName(RoleName name) {
        Query q = sessionFactory.getCurrentSession().createQuery("from Role as u where u.name = :name");
        q.setParameter("name", name);
    	return (Role)q.uniqueResult();
    }
	
}
