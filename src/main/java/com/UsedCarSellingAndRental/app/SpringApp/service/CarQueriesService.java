package com.UsedCarSellingAndRental.app.SpringApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UsedCarSellingAndRental.app.SpringApp.model.CarQueries;
import com.UsedCarSellingAndRental.app.SpringApp.repository.CarQueriesRepository;

@Service
public class CarQueriesService {

	@Autowired
	private CarQueriesRepository carQueriesRepository;
	
	
	public CarQueries saveCarQuery(CarQueries carQueries) {
		return carQueriesRepository.save(carQueries);
	}


	public List<CarQueries> fetchAllQueries() {
		return carQueriesRepository.findAll();
	}

}
