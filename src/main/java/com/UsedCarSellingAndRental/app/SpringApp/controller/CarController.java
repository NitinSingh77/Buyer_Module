package com.UsedCarSellingAndRental.app.SpringApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.UsedCarSellingAndRental.app.SpringApp.dto.ResponseMessageDto;
import com.UsedCarSellingAndRental.app.SpringApp.exception.InvalidCredentialsException;
import com.UsedCarSellingAndRental.app.SpringApp.exception.ResourceNotFoundException;
import com.UsedCarSellingAndRental.app.SpringApp.model.Car;
import com.UsedCarSellingAndRental.app.SpringApp.model.Document;
import com.UsedCarSellingAndRental.app.SpringApp.service.CarService;
import com.UsedCarSellingAndRental.app.SpringApp.service.DocumentService;

@RestController
public class CarController {

	@Autowired
	private CarService carService;
	
	@Autowired
	private DocumentService documentService;
	
	
	/*-----------------------------------------------------Car Entry----------------------------------------------------------*/
	@PostMapping("/car/entry")
	public ResponseEntity<?> CarEntry(@RequestBody Car car, ResponseMessageDto dto) throws InvalidCredentialsException
	{
		Document document= new Document();
		document.setDocument_upload(car.getDocument().getDocument_upload());
		document.setTime(car.getDocument().getTime());
		document.setVerified(car.getDocument().getVerified());
		document.setDocument_type(car.getDocument().getDocument_type());
		document.setPicture_path(car.getDocument().getPicture_path());
		
		documentService.saveDocument(document);
		car.setDocument(document);
		car.setCar_model(car.getCar_model());
		car.setPurchase_year(car.getPurchase_year());
		car.setFuel_type(car.getFuel_type());
		car.setCarStatus(car.getCarStatus());
		car.setBody_type(car.getBody_type());
		car.setPrice(car.getPrice());
		car.setCar_condition(car.getCar_condition());
		car.setVerified(car.getVerified());
		
		carService.saveCar(car);
		dto.setMsg("Car entry Successful!");
		return ResponseEntity.ok(dto);
		
	}
	
	/*---------------------------GET car details using ID(Executive functionality)-----------------------------*/
	@GetMapping("/car/details/{id}")
	public Car getCarDetails(@PathVariable int id) throws ResourceNotFoundException {
		return carService.getCarById(id);
	}

	
	
	/*-----------------------------------Delete car data using car Id(Executive functionality)--------------------------*/
	@DeleteMapping("/car/delete/{id}")
	public ResponseEntity<?> deleteCarById(@PathVariable int id, ResponseMessageDto dto) {
		try {
			carService.validate(id);
			carService.deletCarById(id);
			dto.setMsg("Car data deleted successfully");
			return ResponseEntity.ok(dto);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}

}
