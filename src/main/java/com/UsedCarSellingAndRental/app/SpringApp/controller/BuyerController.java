package com.UsedCarSellingAndRental.app.SpringApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.UsedCarSellingAndRental.app.SpringApp.model.Buyer;
import com.UsedCarSellingAndRental.app.SpringApp.service.BuyerService;

@RestController
public class BuyerController {
	
	@Autowired
	private BuyerService buyerService;
	
	
	@GetMapping("/buyer/all")
	public List<Buyer> getAllBuyer(){
		List<Buyer> list= buyerService.getAllBuyer();
		return list;
	}
	
	
}
