package com.UsedCarSellingAndRental.app.SpringApp.controller;

import java.security.Principal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.UsedCarSellingAndRental.app.SpringApp.dto.ResponseMessageDto;
import com.UsedCarSellingAndRental.app.SpringApp.enums.CarStatus;
import com.UsedCarSellingAndRental.app.SpringApp.exception.ResourceNotFoundException;
import com.UsedCarSellingAndRental.app.SpringApp.model.BoughtCar;
import com.UsedCarSellingAndRental.app.SpringApp.model.Buyer;
import com.UsedCarSellingAndRental.app.SpringApp.model.Car;
import com.UsedCarSellingAndRental.app.SpringApp.service.BoughtCarService;
import com.UsedCarSellingAndRental.app.SpringApp.service.BuyerService;
import com.UsedCarSellingAndRental.app.SpringApp.service.CarService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class BoughtCarController {
	
	@Autowired
	private BoughtCarService boughtCarService;
	
	@Autowired
	private BuyerService buyerService;
	
	@Autowired
	private CarService carService;
	
	
	/*---------------------------------------Inserting bought car details---------------------------------------------------------------*/
	
	@GetMapping("/insert/bought_car/details/{cId}/{bId}")
	public ResponseEntity<?> boughtCarDetails(@PathVariable int cId, @PathVariable int bId, ResponseMessageDto dto, Principal principal)
	{	
		Buyer buyer= null;
		Car car=null;
		
		
		try {
			 buyer= buyerService.Validate(bId);
		} catch (ResourceNotFoundException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
		try {
			 car= carService.validate(cId);
		} catch (ResourceNotFoundException e) {
			
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
		
		car.setCarStatus(CarStatus.Sold);
		car=carService.updateCar(car);
		BoughtCar boughtCar= new BoughtCar();
		
		boughtCar.setBuyer(buyer);
		boughtCar.setCar(car);
		boughtCar.setDate_of_purchase(LocalDate.now());
		
		boughtCar= boughtCarService.addBoughtCar(boughtCar);
		
		return ResponseEntity.ok(boughtCar);
		
	}
	
	
}
