package com.UsedCarSellingAndRental.app.SpringApp.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.UsedCarSellingAndRental.app.SpringApp.dto.ResponseMessageDto;
import com.UsedCarSellingAndRental.app.SpringApp.exception.ResourceNotFoundException;
import com.UsedCarSellingAndRental.app.SpringApp.model.Buyer;
import com.UsedCarSellingAndRental.app.SpringApp.model.Car;
import com.UsedCarSellingAndRental.app.SpringApp.model.CarQueries;
import com.UsedCarSellingAndRental.app.SpringApp.service.BuyerService;
import com.UsedCarSellingAndRental.app.SpringApp.service.CarQueriesService;
import com.UsedCarSellingAndRental.app.SpringApp.service.CarService;


@RestController
public class CarQueriesController {
	
	@Autowired
	private CarQueriesService carQueriesService;
	
	@Autowired
	BuyerService buyerService;
	
	@Autowired
	CarService carService;
	
	@PostMapping("/carQueries/add")
    public ResponseEntity<?> addCarQuery(@RequestParam int carId, @RequestParam int buyerId, ResponseMessageDto dto) throws ResourceNotFoundException {
		
		
        
        Buyer buyer = buyerService.getBuyerById(buyerId);
        Car car = carService.getCarById(carId);

        if (car == null) {
            return ResponseEntity.badRequest().body("Car not found with the provided ID.");
        }
        if (buyer == null) {
            return ResponseEntity.badRequest().body("Buyer not found with the provided ID.");
        }
        CarQueries carQueries = new CarQueries();
        carQueries.setCar(car);
        carQueries.setBuyer(buyer);
        carQueries.setDate(LocalDate.now()); 
        carQueries.setMessage(carQueries.getMessage());
        carQueriesService.saveCarQuery(carQueries);
        dto.setMsg("Car query added successfully.");
        return ResponseEntity.ok(dto);
    }

	
}
