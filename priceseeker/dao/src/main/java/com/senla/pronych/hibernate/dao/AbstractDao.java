package com.senla.pronych.hibernate.dao;

import org.hibernate.Session;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<T> implements GenericDao<T> {
	private Class<T> type;
	
	@Autowired
	private SessionFactory sessionFactory;
	
    public AbstractDao() {
        this.type = (Class<T>) ((ParameterizedType) getClass()  
                .getGenericSuperclass()).getActualTypeArguments()[0];          
    }
    
	public long save(T t) {
		return (Long)sessionFactory.getCurrentSession().save(t);
	}

	public void update(T t) {
		sessionFactory.getCurrentSession().update(t);
	}

	public void delete(long id) {
		Session session = sessionFactory.getCurrentSession();
		T t = session.get(type, id);
		if (t != null) {
			session.delete(t);
			//System.out.println("category is deleted");
		}
	}

	public T getById(long id) {
        return (T) sessionFactory.getCurrentSession().get(type, id);
	}

	public T getByName(String name) {
		Query q = sessionFactory.getCurrentSession().createQuery("from " + type.getName() + " as u where u.name = :name");
        q.setParameter("name", name);
    	return (T)q.uniqueResult();
	}

	public List<T> getAll() {
		return (List<T>)sessionFactory.getCurrentSession().createQuery("from " + type.getName()).list();
	}

}
