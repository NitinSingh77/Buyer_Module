package com.UsedCarSellingAndRental.app.SpringApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "buyer_info")
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

 
    @Column(nullable= false)
    private String email;

    @Column(nullable= false)
    private String phone;

    @Column(nullable= false)
    private String address;

   
    @OneToOne
    private User user;


    // Getters and setters

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
  
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
    
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
   

   
}
