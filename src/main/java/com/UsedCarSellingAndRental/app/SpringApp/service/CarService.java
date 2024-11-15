package com.UsedCarSellingAndRental.app.SpringApp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UsedCarSellingAndRental.app.SpringApp.exception.ResourceNotFoundException;
import com.UsedCarSellingAndRental.app.SpringApp.model.Car;
import com.UsedCarSellingAndRental.app.SpringApp.repository.CarRepository;
import com.UsedCarSellingAndRental.app.SpringApp.repository.DocumentRepository;

@Service
public class CarService {

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private DocumentRepository documentRepository;
	
	
	/*-------------------------ID validation-----------------------------*/
	
	public Car validate(int id) throws ResourceNotFoundException {
		Optional<Car> optional = carRepository.findById(id);

		if (optional.isEmpty()) {
			throw new ResourceNotFoundException("Invalid car ID");
		}

		return optional.get();

	}


	
	/*-------------------------------------------Get car using ID----------------------------*/
	public Car getCarById(int id) throws ResourceNotFoundException {
		Optional<Car> optional = carRepository.findById(id);

		if (optional.isEmpty()) {
			throw new ResourceNotFoundException("Invalid car ID");
		}

		return optional.get();

	}

	
	/*------------------------------------------Save car----------------------------------------*/
	public void saveCar(Car car) {
		carRepository.save(car);
		
	}


	/*-------------------------------------delete car using ID--------------------------------------*/
	public void deletCarById(int id) {
		Optional<Car> optional= carRepository.findById(id);
		Car car= optional.get();
		int document_id= car.getDocument().getId();
		
		carRepository.deleteById(id);
		documentRepository.deleteById(document_id);
		
	}
}
