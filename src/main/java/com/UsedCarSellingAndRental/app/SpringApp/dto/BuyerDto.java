package com.UsedCarSellingAndRental.app.SpringApp.dto;

import org.springframework.stereotype.Component;

 @Component
public class BuyerDto {
	private String email;

     
    private String phone;

    
    private String address;
    
    private String name;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
    
    

}
