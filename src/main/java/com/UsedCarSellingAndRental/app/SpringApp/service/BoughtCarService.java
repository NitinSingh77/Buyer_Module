package com.UsedCarSellingAndRental.app.SpringApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UsedCarSellingAndRental.app.SpringApp.model.BoughtCar;
import com.UsedCarSellingAndRental.app.SpringApp.repository.BoughtCarRepository;

@Service
public class BoughtCarService {
	
	@Autowired
	private BoughtCarRepository boughtCarRepository;

	public BoughtCar addBoughtCar(BoughtCar boughtCar) {
		return boughtCarRepository.save(boughtCar);
	}

	
	
}
