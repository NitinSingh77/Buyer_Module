package com.UsedCarSellingAndRental.app.SpringApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.UsedCarSellingAndRental.app.SpringApp.exception.InvalidUsernameException;
import com.UsedCarSellingAndRental.app.SpringApp.model.Seller;
import com.UsedCarSellingAndRental.app.SpringApp.service.SellerService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class SellerController {
	
	@Autowired
	private SellerService sellerService;
	
	@GetMapping("/api/get/AllSeller")
	public List<Seller>getAllSeller(){
		return sellerService.getAllSeller();
	}
	
	@PostMapping("/api/add/seller")
	public Seller addSeller(@RequestBody Seller seller) throws InvalidUsernameException {
		return sellerService.addSeller(seller);
	}
	
}
