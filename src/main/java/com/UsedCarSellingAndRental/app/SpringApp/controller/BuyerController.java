package com.UsedCarSellingAndRental.app.SpringApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.UsedCarSellingAndRental.app.SpringApp.dto.BuyerDto;
import com.UsedCarSellingAndRental.app.SpringApp.exception.ResourceNotFoundException;
import com.UsedCarSellingAndRental.app.SpringApp.model.Buyer;
import com.UsedCarSellingAndRental.app.SpringApp.model.Car;
import com.UsedCarSellingAndRental.app.SpringApp.model.User;
import com.UsedCarSellingAndRental.app.SpringApp.service.BuyerService;
import com.UsedCarSellingAndRental.app.SpringApp.service.CarService;
import com.UsedCarSellingAndRental.app.SpringApp.service.UserService;


@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class BuyerController {
	
	@Autowired
	private BuyerService buyerService;
	
	@Autowired
	private CarService carService;
	@Autowired
	private UserService userService;
	
	/*-------------------------------------------Show all cars------------------------------------*/
	@GetMapping("/api/show/car/all")
	public List<Car> showAllCar(){
		List<Car> list= carService.getAllCar();
		return list;
	}
	
	
	/*-------------------------------------------Search car by price---------------------------------*/
	@GetMapping("/car/search/byPrice")
	public ResponseEntity<?> searchByPrice(@RequestParam double price){
		List<Car> cars= carService.searchByPrice(price);
		return ResponseEntity.ok(cars);
	}
	
	/*------------------------Executive functionality-----------------------*/
	@GetMapping("/buyer/all")
	public List<Buyer> getAllBuyer(){
		List<Buyer> list= buyerService.getAllBuyer();
		return list;
	}
	
	
	/*------------------Get buyer by id--------------------------------------------------*/
	@GetMapping("/buyer/details/{id}")
	public Buyer getbuyerDetails(@PathVariable int id) throws ResourceNotFoundException {
		return buyerService.getBuyerById(id);
	}
	
	/*--------------------------Get buyer details by username-------------------*/
	
	@GetMapping("/api/get/buyer/details")
	public Buyer getBuyerDetailsByUsername(@RequestParam String username) {
//		System.out.println(username);
		return buyerService.getBuyerDetailsByUsername(username);
	}
	
	
	
	/*----------------------------------Update Profile--------------------------------------------------------*/
	
	@PostMapping("/api/buyer/update/{id}")
	public Buyer postBuyer(@PathVariable int id,  @RequestBody BuyerDto buyerDto) throws ResourceNotFoundException {
		Buyer buyer = buyerService.getBuyerById(id);
		buyer.setPhone(buyerDto.getPhone());
		buyer.setAddress(buyerDto.getAddress());
		buyer.setEmail(buyerDto.getEmail());
		User user = buyer.getUser();
		user.setName(buyerDto.getName());
		user = userService.insert(user);
		buyer.setUser(user);
		
		return buyerService.insert(buyer);
	}
	
	
	
	
	
	
}
