package cn.easybuy.utils;


import cn.easybuy.pojo.Product;

import java.io.Serializable;

public class ShoppingCartItem implements Serializable{
	private Product product;//商品
	private Integer quantity;//数量
	private float cost;//价格

	public ShoppingCartItem(Product product, Integer quantity) {
		this.product = product;
		this.quantity = quantity;
		this.cost = product.getPrice() * quantity;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
		this.cost = product.getPrice() * quantity;
	}

	public Product getProduct() {
		return product;
	}

	public float getCost() {
		return cost;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}
}
