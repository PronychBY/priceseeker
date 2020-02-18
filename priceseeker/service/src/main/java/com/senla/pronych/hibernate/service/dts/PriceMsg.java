package com.senla.pronych.hibernate.service.dts;

import java.util.Date;

import com.senla.pronych.hibernate.entity.Price;

public class PriceMsg {
	
	private long id;
	private long shopId;
	private long productId;
	private double value;
	private Date dateOfValue;

	public PriceMsg() {
	}

	public PriceMsg(long id, long shopId, long productId, double value, Date dateOfValue) {
		this.id = id;
		this.shopId = shopId;
		this.productId = productId;
		this.value = value;
		this.dateOfValue = dateOfValue;
	}
	
	public PriceMsg(Price price) {
		this.id = price.getId();
		this.shopId = price.getShop().getId();
		this.productId = price.getProduct().getId();
		this.value = price.getValue();
		this.dateOfValue = price.getDateOfValue();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getShopId() {
		return shopId;
	}

	public void setShopId(long shopId) {
		this.shopId = shopId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Date getDateOfValue() {
		return dateOfValue;
	}

	public void setDateOfValue(Date dateOfValue) {
		this.dateOfValue = dateOfValue;
	}	
	
}
