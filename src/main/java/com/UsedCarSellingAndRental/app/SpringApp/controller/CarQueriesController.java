package com.UsedCarSellingAndRental.app.SpringApp.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.UsedCarSellingAndRental.app.SpringApp.dto.ResponseMessageDto;
import com.UsedCarSellingAndRental.app.SpringApp.exception.ResourceNotFoundException;
import com.UsedCarSellingAndRental.app.SpringApp.model.Buyer;
import com.UsedCarSellingAndRental.app.SpringApp.model.Car;
import com.UsedCarSellingAndRental.app.SpringApp.model.CarQueries;
import com.UsedCarSellingAndRental.app.SpringApp.service.BuyerService;
import com.UsedCarSellingAndRental.app.SpringApp.service.CarQueriesService;
import com.UsedCarSellingAndRental.app.SpringApp.service.CarService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;


@RestController
public class CarQueriesController {
	
	@Autowired
	private CarQueriesService carQueriesService;
	
	@Autowired
	BuyerService buyerService;
	
	@Autowired
	CarService carService;
	
	@PostMapping("/carQueries/add/{cId}/{bId}")
    public ResponseEntity<?> addCarQuery(@PathVariable int cId, @PathVariable int bId,@RequestBody CarQueries carQueries, ResponseMessageDto dto) throws ResourceNotFoundException {
		
        
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
		
		String message= carQueries.getMessage();
        carQueries.setCar(car);
        carQueries.setBuyer(buyer);
        carQueries.setDate(LocalDate.now()); 
        carQueries.setMessage(message);
        carQueries=carQueriesService.saveCarQuery(carQueries);
        dto.setMsg("Car query added successfully.");
        return ResponseEntity.ok(carQueries);
    }

	
	
}
