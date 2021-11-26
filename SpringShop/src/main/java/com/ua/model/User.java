package com.ua.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;

	@Size(min = 2, message = "Name should habe at least 2 symbols")
	@Column(nullable = false)
	private String firstName;

	@Size(min = 2, message = "Name should habe at least 2 symbols")
	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false, unique = true)
	@Email(regexp = ".+@.+\\..+", message = "email should be valid")
	private String email;

	@Column(nullable = false)
	@Size(min = 6, message = "Password should have at least 6")
	private String password;

	@Column(nullable = false)
	private String role;

	@Column
	private String status = "active";
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private int active;

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinTable(name = "userProductList", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "productId"))
	private List<Product> productList;
	


	public void removeCascade(Product b) {
		productList.remove(b);
		b.getUserList().remove(this);
	}


	public User() {
	}

	public User(long userId, String firstName, String lastName, String email, String password, String role) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.active = 1;
		this.role = role;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}


//	public List<Product> getProductList1() {
//		return productList1;
//	}
//
//	public void setProductList1(List<Product> productList1) {
//		this.productList1 = productList1;
//	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<String> getRoleList() {
		if (this.role.length() > 0) {
			return Arrays.asList(this.role.split(","));
		}

		return new ArrayList<String>();
	}

}
