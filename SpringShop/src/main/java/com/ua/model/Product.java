package com.ua.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long productId;
	
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productDescription="
				+ productDescription + ", productCategory=" + productCategory + ", productPrice=" + productPrice
				+ ", productUnit=" + productUnit + ", image=" + image + ", created=" + created + ", userList="
				+ userList + "]";
	}

	@Column
	private String productName;
	
	private String productDescription;
	
	@Column
	private String productCategory;
	
	@Column
	private String productPrice;
	
	@Column
	private int productUnit;
	
	private String image;
	
	private String created;
	

	public String getCreated() {
		return created;
	}


	public void setCreated(String created) {
		this.created = created;
	}


	public String getProductCategory() {
		return productCategory;
	}


	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	@ManyToMany(mappedBy = "productList",fetch = FetchType.EAGER)
	private List<User> userList;
	


	public void removeCascade(User b) {
		userList.remove(b);
		b.getProductList().remove(this);
	}
	
	public Product() {
		this.productName = "";
		this.productDescription = "";
	}
	

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}


	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public int getProductUnit() {
		return productUnit;
	}

	public void setProductUnit(int productUnit) {
		this.productUnit = productUnit;
	}
	
	
}
