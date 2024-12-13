package com.UsedCarSellingAndRental.app.SpringApp.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.UsedCarSellingAndRental.app.SpringApp.exception.ResourceNotFoundException;
import com.UsedCarSellingAndRental.app.SpringApp.model.Car;
import com.UsedCarSellingAndRental.app.SpringApp.model.CarImage;
import com.UsedCarSellingAndRental.app.SpringApp.repository.CarImageRepository;
import com.UsedCarSellingAndRental.app.SpringApp.repository.CarRepository;
import com.UsedCarSellingAndRental.app.SpringApp.repository.DocumentRepository;

@Service
public class CarService {

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private DocumentRepository documentRepository;
	
	@Autowired
	private CarImageRepository carImageRepository;
	
	
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
		
		return carRepository.getAllCar();
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



	public Car addSellingCars(Car car) {
		// TODO Auto-generated method stub
		return null;
	}



/*------------------------------Car filter functionality--------------------------*/


	public Car getCarByName(String name) {
		
		return carRepository.getCarByName(name);
	}



/*------------------------------add car image-----------------------------------*/
	public CarImage addCarImage(CarImage ci) {
		return carImageRepository.save(ci);
	}
	
	
/*--------------------------------Upload Image--------------------------------------------------*/

	public CarImage uploadImage(int pid, MultipartFile file) throws ResourceNotFoundException, IOException {
//		System.out.println(file.getOriginalFilename());

		String location= "E:/Angular Project/my-module-app/src/assets";
		Path path= Path.of(location, file.getOriginalFilename());
		
		try {
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw e; 
		}
		
		Car car=null;
		try {
			car = getCarById(pid);
		} catch (ResourceNotFoundException e) {
			 throw e; 
		}
		
		CarImage ci= new CarImage();
		ci.setFileName(file.getOriginalFilename());
		ci.setPath(path.toString());
		ci.setCar(car);
		
		return addCarImage(ci);
		
	}



	public List<CarImage> getAllCarImages() {
		
		return carImageRepository.findAll();
	}



	public void saveAllCars(List<Car> cars) {
		carRepository.saveAll(cars);
		
	}



	public List<Car> getAllCarByName(String carName) {
		
		return carRepository.getAllCarByName(carName);
	}




	public List<Car> getAllSoldCar() {
		
		return carRepository.getAllSoldCar();
	}
	
	

}
