package com.senla.pronych.hibernate.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.senla.pronych.hibernate.dao.PriceDao;
import com.senla.pronych.hibernate.dao.ProductDao;
import com.senla.pronych.hibernate.dao.ShopDao;
import com.senla.pronych.hibernate.entity.Price;
import com.senla.pronych.hibernate.entity.Product;
import com.senla.pronych.hibernate.entity.Shop;
import com.senla.pronych.hibernate.service.dts.PriceMsg;
import com.senla.pronych.hibernate.service.dts.ProductMsg;

@Component("priceService")
@Transactional
public class PriceService {
	private final PriceDao dao;
	private final ShopDao shopDao;
	private final ProductDao productDao;
	
	@Autowired
	public PriceService(PriceDao dao, ShopDao shopDao, ProductDao productDao) {
		this.dao = dao;
		this.shopDao = shopDao;
		this.productDao = productDao;
	}

	public Price addNewPrice(Date date,Shop shop,Product product, double value) {
		Price price = new Price();
		price.setDateOfValue(date);
		price.setShop(shop);
		price.setProduct(product);
		price.setValue(value);
		
		dao.save(price);
		return price;
	}
	
	public Price addNewPrice(PriceMsg priceMsg) {
		return addNewPrice(priceMsg.getDateOfValue(),
				shopDao.getById(priceMsg.getShopId()),
				productDao.getById(priceMsg.getProductId()), 
				priceMsg.getValue());
	}
	
	public Price addNewPriceCurentDate(PriceMsg priceMsg) {
		Date date = new Date(System.currentTimeMillis());
		Price price = addNewPrice(date,
				shopDao.getById(priceMsg.getShopId()),
				productDao.getById(priceMsg.getProductId()), 
				priceMsg.getValue()); 
		return price;
	}
	
	public Price addNewPriceCurentDate(Shop shop,Product product, double value) {
		Date date = new Date(System.currentTimeMillis());
		Price price = addNewPrice(date,shop,product,value); 
		return price;
	}
	
	public List<PriceMsg> getPriceByProductToJson(ProductMsg productMsg) {
		List<Price> prices = dao.getAll();
		
		ArrayList<PriceMsg> priceMsgs = new ArrayList<PriceMsg>(); 
		for (Price price:prices) {
    		priceMsgs.add(new PriceMsg(price));
		}
		return priceMsgs;
	}
	
    public long save(Price price) {
    	return dao.save(price);
    }

    public void update(Price price) {
    	dao.update(price);
    }

    public void delete(long id) {
        dao.delete(id);
    }

    public Price getById(long id) {
    	return dao.getById(id);
    }
    
    public List<Price> getAll() {
    	return dao.getAll();
    }
	
    private List<PriceMsg> priceToPriceMsg(List<Price> prices){
    	ArrayList<PriceMsg> priceMsg = new ArrayList<PriceMsg>();
    	for (Price price : prices){
    		priceMsg.add(new PriceMsg(price));
    	}
        return priceMsg;
    }
    
    
    public List<PriceMsg> getAllToJson() {
    	List<Price> prices = dao.getAll();
        return priceToPriceMsg(prices);
    }
    
    public PriceMsg getByIdToJson(long id) {
    	return new PriceMsg(dao.getById(id));
    }
    
	
}
