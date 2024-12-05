package com.UsedCarSellingAndRental.app.SpringApp.service;

import java.util.List;
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



	public List<Car> getAllCar() {
		
		return carRepository.findAll();
	}



	 public List<Car> searchByBodyType(String bodyType) {
	        return carRepository.searchByBodyType(bodyType);
	    }


	public void updateStatus(int id) {
		carRepository.updateStatus(id);
		
	}



	public Car updateCar(Car car) {
		return carRepository.save(car);
	}



	public List<Car> searchByPrice(double price) {
		
		return carRepository.searchByPrice(price);
	}
}
