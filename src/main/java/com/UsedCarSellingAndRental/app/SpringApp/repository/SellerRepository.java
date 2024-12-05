package com.UsedCarSellingAndRental.app.SpringApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.UsedCarSellingAndRental.app.SpringApp.model.Seller;

public interface SellerRepository extends JpaRepository<Seller, Integer>{
	
}


