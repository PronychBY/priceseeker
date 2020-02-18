package com.senla.pronych.hibernate.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.senla.pronych.hibernate.entity.User;

@Repository("userDao")
public class UserDao  extends AbstractDao<User>{
	@Autowired
	private SessionFactory sessionFactory;
	
	public User getByUserName(String name) {
		Query q = sessionFactory.getCurrentSession().createQuery("from User as u where u.username = :name");
        q.setParameter("name", name);
    	return (User)q.uniqueResult();
	}

}
