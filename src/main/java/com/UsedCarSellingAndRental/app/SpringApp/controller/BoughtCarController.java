package com.UsedCarSellingAndRental.app.SpringApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.UsedCarSellingAndRental.app.SpringApp.service.BoughtCarService;

@RestController
public class BoughtCarController {
	
	@Autowired
	private BoughtCarService boughtCarService;
	
	
}
