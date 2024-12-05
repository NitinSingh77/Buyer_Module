package com.UsedCarSellingAndRental.app.SpringApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Seller {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int sellerId;
	
//	private String name;
    private String contactInfo; 
    private String location;
    
    @OneToOne
    private User user;

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	
	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
    

}
