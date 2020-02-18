package com.senla.pronych.hibernate.entity;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PRICE")
public class Price {
	
	@Id
	@GeneratedValue
	@Column(name = "price_id")
	private long id;
    
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "shop_id")  
	private Shop shop;
    
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")  
	private Product product;
    
	private double value;
	
	@Column(name = "date_of_value")
    @Temporal(TemporalType.DATE)
	private Date dateOfValue;
	
	public Price() {
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfValue == null) ? 0 : dateOfValue.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((shop == null) ? 0 : shop.hashCode());
		long temp;
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Price other = (Price) obj;
		if (dateOfValue == null) {
			if (other.dateOfValue != null)
				return false;
		} else if (!dateOfValue.equals(other.dateOfValue))
			return false;
		if (id != other.id)
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (shop == null) {
			if (other.shop != null)
				return false;
		} else if (!shop.equals(other.shop))
			return false;
		if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value))
			return false;
		return true;
	}
    
    

}
