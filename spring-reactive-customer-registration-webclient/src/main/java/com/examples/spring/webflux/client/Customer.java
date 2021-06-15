package com.examples.spring.webflux.client;

import org.springframework.data.annotation.Id;

public class Customer {

	@Id
	private Integer id;
	private String name;
	private String address;
	private String country;
	private String state;
	private String pancard;
	private String accounttype;

	public Customer() {

	}

	public Customer(Integer id, String name, String address, String country, String state, String pancard,String accounttype) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.country = country;
		this.state = state;
		this.pancard = pancard;
		this.accounttype = accounttype;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPancard() {
		return pancard;
	}

	public void setPancard(String pancard) {
		this.pancard = pancard;
	}

	public String getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}

}
