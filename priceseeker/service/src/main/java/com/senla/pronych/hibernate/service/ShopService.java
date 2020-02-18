package com.senla.pronych.hibernate.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.senla.pronych.hibernate.dao.ShopDao;
import com.senla.pronych.hibernate.entity.Shop;

@Component("shopService")
@Transactional
public class ShopService {

	private final ShopDao dao;
	
	@Autowired
	public ShopService(ShopDao dao) {
		this.dao = dao;
	}
	
    public long save(Shop obj) {
    	return dao.save(obj);
    }

    public void update(Shop obj) {
    	dao.update(obj);
    }

    public void delete(long id) {
        dao.delete(id);
    }

    public Shop getById(long id) {
    	return dao.getById(id);
    }
    
    public List<Shop> getAll() {
        return dao.getAll();
    }
    
}
