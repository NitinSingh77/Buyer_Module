package com.UsedCarSellingAndRental.app.SpringApp.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.UsedCarSellingAndRental.app.SpringApp.enums.QueryStatus;
import com.UsedCarSellingAndRental.app.SpringApp.exception.ResourceNotFoundException;
import com.UsedCarSellingAndRental.app.SpringApp.model.Buyer;
import com.UsedCarSellingAndRental.app.SpringApp.model.Car;
import com.UsedCarSellingAndRental.app.SpringApp.model.CarQueries;
import com.UsedCarSellingAndRental.app.SpringApp.service.BuyerService;
import com.UsedCarSellingAndRental.app.SpringApp.service.CarQueriesService;
import com.UsedCarSellingAndRental.app.SpringApp.service.CarService;


@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class CarQueriesController {
	
	@Autowired
	private CarQueriesService carQueriesService;
	
	@Autowired
	BuyerService buyerService;
	
	@Autowired
	CarService carService;
	
	@PostMapping("/carQueries/add/{cId}/{bId}")
    public CarQueries addCarQuery(@PathVariable int cId, @PathVariable int bId, @RequestParam String message) throws ResourceNotFoundException {
		
        
		Buyer buyer= buyerService.Validate(bId);
		Car car=carService.validate(cId);
		
		CarQueries carQueries= new CarQueries();
		 carQueries.setMessage(message);
        carQueries.setCar(car);
        carQueries.setBuyer(buyer);
        carQueries.setDate(LocalDate.now()); 
        carQueries.setQueryStatus(QueryStatus.Pending);
      
        carQueries=carQueriesService.saveCarQuery(carQueries);
      
        return carQueries;
    }

	/*-----------------------------------Fetch All Car Queries-------------------------------------------------*/
	
	@GetMapping("/api/fetch/all/carqueries")
	public List<CarQueries> fetchAllQueries(){
		return carQueriesService.fetchAllQueries();
	}
	
}
