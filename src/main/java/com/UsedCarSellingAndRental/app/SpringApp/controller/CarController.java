package com.UsedCarSellingAndRental.app.SpringApp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.UsedCarSellingAndRental.app.SpringApp.dto.CarImageDto;
import com.UsedCarSellingAndRental.app.SpringApp.dto.ResponseMessageDto;
import com.UsedCarSellingAndRental.app.SpringApp.exception.InvalidCredentialsException;
import com.UsedCarSellingAndRental.app.SpringApp.exception.ResourceNotFoundException;
import com.UsedCarSellingAndRental.app.SpringApp.model.Car;
import com.UsedCarSellingAndRental.app.SpringApp.model.CarImage;
import com.UsedCarSellingAndRental.app.SpringApp.model.Document;
import com.UsedCarSellingAndRental.app.SpringApp.service.CarService;
import com.UsedCarSellingAndRental.app.SpringApp.service.DocumentService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class CarController {

	@Autowired
	private CarService carService;
	
	@Autowired
	private DocumentService documentService;
	
	
	/*-----------------------------------------------------Batch Car Entry----------------------------------------------------------*/
	@PostMapping("/api/cars/add/batchAPI")
	public ResponseEntity<?> batchCarEntry(@RequestBody List<Car> cars, ResponseMessageDto dto) throws InvalidCredentialsException {
	    List<Document> documents = new ArrayList<>();
	    
	    for (Car car : cars) {
	        Document document = new Document();
	        document.setDocument_upload(car.getDocument().getDocument_upload());
	        document.setTime(car.getDocument().getTime());
	        document.setDocument_type(car.getDocument().getDocument_type());
	        documents.add(document);
	        
	       
	        documentService.saveDocument(document);
	        car.setDocument(document);
	    }

	   
	    carService.saveAllCars(cars);

	    
	    dto.setMsg("Batch car entry successful!");
	    return ResponseEntity.ok(dto);
	}
	
	
	/*------------------------------List of cars by name------------------------------*/
	
	@GetMapping("/api/get/all/cars/byname")
	public List<Car> getAllCarByName(@RequestParam String carName){
		return carService.getAllCarByName(carName);
	}
	
	
	/*-----------------------------------------------------Car Entry----------------------------------------------------------*/
	@PostMapping("/api/car/add")
	public ResponseEntity<?> CarEntry(@RequestBody Car car, ResponseMessageDto dto) throws InvalidCredentialsException
	{
		Document document= new Document();
		document.setDocument_upload(car.getDocument().getDocument_upload());
		document.setTime(car.getDocument().getTime());
		document.setDocument_type(car.getDocument().getDocument_type());
		
		
		documentService.saveDocument(document);
		car.setDocument(document);
		car.setCar_model(car.getCar_model());
		car.setPurchase_year(car.getPurchase_year());
		car.setFuel_type(car.getFuel_type());
		car.setCarStatus(car.getCarStatus());
		car.setBody_type(car.getBody_type());
		car.setPrice(car.getPrice());
		car.setCar_condition(car.getCar_condition());
		car.setDistance_travelled(car.getDistance_travelled());
		car.setMileage(car.getMileage());
		
		
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
	
	
	/*--------------------------------------Filter Functionality-----------------------------*/
	
	/*------------------------------------------Get all SUV Electric car-------------------------*/
	@GetMapping("/api/car/all/filter/suvEv")
	public List<CarImageDto> getAllCarsfilterSuvEv(){
		List<Car> cList= carService.getAllCar();
		List<CarImage> imageList= carService.getAllCarImages();
		
		List<CarImageDto> ListDto= new ArrayList<>();
		for(Car c : cList) {
			
			 if ("SUV".equalsIgnoreCase(c.getBody_type()) && "EV".equalsIgnoreCase(c.getFuel_type().toString())) {
			CarImageDto dto = new CarImageDto();
			dto.setId(c.getId());
			dto.setCar_name(c.getCar_model());
			dto.setFuel_type(c.getFuel_type());
			dto.setPrice(c.getPrice());
			dto.setBody_type(c.getBody_type());
			dto.setMileage(c.getMileage());
			dto.setDistance_travelled(c.getDistance_travelled());
			dto.setOwnership_status(c.getOwnership_status());

			
			List<CarImage> iList=
					imageList.stream()
					.filter(i->i.getCar().getId() == c.getId())
					.toList();
			
			dto.setImages(iList);
			ListDto.add(dto);	
			
		}
		}
		return ListDto;
		
		
	}
	
	/*------------------------------------------Get all SUV Petrol car-------------------------*/
	@GetMapping("/api/car/all/filter/suvpetrol")
	public List<CarImageDto> getAllCarsfilterSuvPetrol(){
		List<Car> cList= carService.getAllCar();
		List<CarImage> imageList= carService.getAllCarImages();
		
		List<CarImageDto> ListDto= new ArrayList<>();
		for(Car c : cList) {
			
			 if ("SUV".equalsIgnoreCase(c.getBody_type()) && "Petrol".equalsIgnoreCase(c.getFuel_type().toString())) {
			CarImageDto dto = new CarImageDto();
			dto.setId(c.getId());
			dto.setCar_name(c.getCar_model());
			dto.setFuel_type(c.getFuel_type());
			dto.setPrice(c.getPrice());
			dto.setBody_type(c.getBody_type());
			dto.setMileage(c.getMileage());
			dto.setDistance_travelled(c.getDistance_travelled());
			dto.setOwnership_status(c.getOwnership_status());
			
			List<CarImage> iList=
					imageList.stream()
					.filter(i->i.getCar().getId() == c.getId())
					.toList();
			
			dto.setImages(iList);
			ListDto.add(dto);	
			
		}
		}
		return ListDto;
		
		
	}
	
	/*------------------------------------------Get all SUV Diesel car-------------------------*/
	@GetMapping("/api/car/all/filter/suvDiesel")
	public List<CarImageDto> getAllCarsfilterSuvDiesel(){
		List<Car> cList= carService.getAllCar();
		List<CarImage> imageList= carService.getAllCarImages();
		
		List<CarImageDto> ListDto= new ArrayList<>();
		for(Car c : cList) {
			
			 if ("SUV".equalsIgnoreCase(c.getBody_type()) && "Diesel".equalsIgnoreCase(c.getFuel_type().toString())) {
			CarImageDto dto = new CarImageDto();
			dto.setId(c.getId());
			dto.setCar_name(c.getCar_model());
			dto.setFuel_type(c.getFuel_type());
			dto.setPrice(c.getPrice());
			dto.setBody_type(c.getBody_type());
			dto.setMileage(c.getMileage());
			dto.setDistance_travelled(c.getDistance_travelled());
			dto.setOwnership_status(c.getOwnership_status());
			
			List<CarImage> iList=
					imageList.stream()
					.filter(i->i.getCar().getId() == c.getId())
					.toList();
			
			dto.setImages(iList);
			ListDto.add(dto);	
			
		}
		}
		return ListDto;
		
		
	}
	
	/*------------------------------------------Get all SUV Hybrid car-------------------------*/
	@GetMapping("/api/car/all/filter/suvHybrid")
	public List<CarImageDto> getAllCarsfilterSuvHybrid(){
		List<Car> cList= carService.getAllCar();
		List<CarImage> imageList= carService.getAllCarImages();
		
		List<CarImageDto> ListDto= new ArrayList<>();
		for(Car c : cList) {
			
			 if ("SUV".equalsIgnoreCase(c.getBody_type()) && "Hybrid".equalsIgnoreCase(c.getFuel_type().toString())) {
			CarImageDto dto = new CarImageDto();
			dto.setId(c.getId());
			dto.setCar_name(c.getCar_model());
			dto.setFuel_type(c.getFuel_type());
			dto.setPrice(c.getPrice());
			dto.setBody_type(c.getBody_type());
			dto.setMileage(c.getMileage());
			dto.setDistance_travelled(c.getDistance_travelled());
			dto.setOwnership_status(c.getOwnership_status());
			
			List<CarImage> iList=
					imageList.stream()
					.filter(i->i.getCar().getId() == c.getId())
					.toList();
			
			dto.setImages(iList);
			ListDto.add(dto);	
			
		}
		}
		return ListDto;
		
	}
	
	/*------------------------------------------Get all Sedan Electric car-------------------------*/
	@GetMapping("/api/car/all/filter/sedanEv")
	public List<CarImageDto> getAllCarsfilterSedanEv(){
		List<Car> cList= carService.getAllCar();
		List<CarImage> imageList= carService.getAllCarImages();
		
		List<CarImageDto> ListDto= new ArrayList<>();
		for(Car c : cList) {
			
			 if ("Sedan".equalsIgnoreCase(c.getBody_type()) && "EV".equalsIgnoreCase(c.getFuel_type().toString())) {
			CarImageDto dto = new CarImageDto();
			dto.setId(c.getId());
			dto.setCar_name(c.getCar_model());
			dto.setFuel_type(c.getFuel_type());
			dto.setPrice(c.getPrice());
			dto.setBody_type(c.getBody_type());
			dto.setMileage(c.getMileage());
			dto.setDistance_travelled(c.getDistance_travelled());
			dto.setOwnership_status(c.getOwnership_status());
			
			List<CarImage> iList=
					imageList.stream()
					.filter(i->i.getCar().getId() == c.getId())
					.toList();
			
			dto.setImages(iList);
			ListDto.add(dto);	
			
		}
		}
		return ListDto;
		
		
	}
	/*------------------------------------------Get all Sedan Petrol car-------------------------*/
	@GetMapping("/api/car/all/filter/sedanpetrol")
	public List<CarImageDto> getAllCarsfilterSedanPetrol(){
		List<Car> cList= carService.getAllCar();
		List<CarImage> imageList= carService.getAllCarImages();
		
		List<CarImageDto> ListDto= new ArrayList<>();
		for(Car c : cList) {
			
			 if ("Sedan".equalsIgnoreCase(c.getBody_type()) && "Petrol".equalsIgnoreCase(c.getFuel_type().toString())) {
			CarImageDto dto = new CarImageDto();
			dto.setId(c.getId());
			dto.setCar_name(c.getCar_model());
			dto.setFuel_type(c.getFuel_type());
			dto.setPrice(c.getPrice());
			dto.setBody_type(c.getBody_type());
			dto.setMileage(c.getMileage());
			dto.setDistance_travelled(c.getDistance_travelled());
			dto.setOwnership_status(c.getOwnership_status());
			
			List<CarImage> iList=
					imageList.stream()
					.filter(i->i.getCar().getId() == c.getId())
					.toList();
			
			dto.setImages(iList);
			ListDto.add(dto);	
			
		}
		}
		return ListDto;
		
		
	}
	/*------------------------------------------Get all Sedan Diesel car-------------------------*/
	@GetMapping("/api/car/all/filter/sedandiesel")
	public List<CarImageDto> getAllCarsfilterSedanDiesel(){
		List<Car> cList= carService.getAllCar();
		List<CarImage> imageList= carService.getAllCarImages();
		
		List<CarImageDto> ListDto= new ArrayList<>();
		for(Car c : cList) {
			
			 if ("Sedan".equalsIgnoreCase(c.getBody_type()) && "Diesel".equalsIgnoreCase(c.getFuel_type().toString())) {
			CarImageDto dto = new CarImageDto();
			dto.setId(c.getId());
			dto.setCar_name(c.getCar_model());
			dto.setFuel_type(c.getFuel_type());
			dto.setPrice(c.getPrice());
			dto.setBody_type(c.getBody_type());
			dto.setMileage(c.getMileage());
			dto.setDistance_travelled(c.getDistance_travelled());
			dto.setOwnership_status(c.getOwnership_status());
			
			List<CarImage> iList=
					imageList.stream()
					.filter(i->i.getCar().getId() == c.getId())
					.toList();
			
			dto.setImages(iList);
			ListDto.add(dto);	
			
		}
		}
		return ListDto;
		
		
	}
	/*------------------------------------------Get all Sedan Hybrid car-------------------------*/
	@GetMapping("/api/car/all/filter/sedanhybrid")
	public List<CarImageDto> getAllCarsfilter(){
		List<Car> cList= carService.getAllCar();
		List<CarImage> imageList= carService.getAllCarImages();
		
		List<CarImageDto> ListDto= new ArrayList<>();
		for(Car c : cList) {
			
			 if ("Sedan".equalsIgnoreCase(c.getBody_type()) && "Hybrid".equalsIgnoreCase(c.getFuel_type().toString())) {
			CarImageDto dto = new CarImageDto();
			dto.setId(c.getId());
			dto.setCar_name(c.getCar_model());
			dto.setFuel_type(c.getFuel_type());
			dto.setPrice(c.getPrice());
			dto.setBody_type(c.getBody_type());
			dto.setMileage(c.getMileage());
			dto.setDistance_travelled(c.getDistance_travelled());
			dto.setOwnership_status(c.getOwnership_status());
			
			List<CarImage> iList=
					imageList.stream()
					.filter(i->i.getCar().getId() == c.getId())
					.toList();
			
			dto.setImages(iList);
			ListDto.add(dto);	
			
		}
		}
		return ListDto;
		
		
	}
	/*---------------------------------Search Car By Name--------------------------------------------------*/
	@GetMapping("/api/get/car/byname")
	public Car getCarByName(@RequestParam String name) {
		return carService.getCarByName(name);
	}

	/*-------------------Upload car Image file ---------------------------------------------------------------------*/
	@PostMapping("/api/car/image/upload/{pid}")
	public CarImage uploadImage(@PathVariable int pid, @RequestParam MultipartFile file)
			throws ResourceNotFoundException, IOException
	{
		return carService.uploadImage(pid, file);
		
	}
	
	
	
/*-----------------------get car with image and data--------------------------------*/
	
	@GetMapping("/api/car/all")
	public List<CarImageDto> getAllCars(){
		List<Car> cList= carService.getAllCar();
		List<CarImage> imageList= carService.getAllCarImages();
		
		List<CarImageDto> ListDto= new ArrayList<>();
		for(Car c : cList) {
			
			CarImageDto dto = new CarImageDto();
			dto.setId(c.getId());
			dto.setCar_name(c.getCar_model());
			dto.setFuel_type(c.getFuel_type());
			dto.setPrice(c.getPrice());
			dto.setBody_type(c.getBody_type());
			dto.setMileage(c.getMileage());
			dto.setDistance_travelled(c.getDistance_travelled());
			dto.setOwnership_status(c.getOwnership_status());
			
			
			List<CarImage> iList=
					imageList.stream()
					.filter(i->i.getCar().getId() == c.getId())
					.toList();
			
			dto.setImages(iList);
			ListDto.add(dto);	
			
		}
		return ListDto;
		
		
	}
	
	
	
	
	/*-----------------------GetById-----------------------------------------*/
	@GetMapping("/api/car/one")
	public CarImageDto getById(@RequestParam int id) throws ResourceNotFoundException {
		Car c= carService.getCarById(id);
		
		List<CarImage> imageList= carService.getAllCarImages();
		
		CarImageDto dto = new CarImageDto();
		
		dto.setId(c.getId());
		dto.setCar_name(c.getCar_model());
		dto.setFuel_type(c.getFuel_type());
		dto.setPrice(c.getPrice());
		dto.setBody_type(c.getBody_type());
		dto.setMileage(c.getMileage());
		dto.setDistance_travelled(c.getDistance_travelled());
		dto.setOwnership_status(c.getOwnership_status());
		
		
		List<CarImage> iList=
				imageList.stream()
				.filter(i->i.getCar().getId() == c.getId())
				.toList();
		
		dto.setImages(iList);
		return dto;
	}

	
	/*-----------------------get all cars which are sold --------------------------------*/
	@GetMapping("/api/car/all/sold")
	public List<CarImageDto> getAllSoldCars() {
	    List<Car> cList = carService.getAllSoldCar();
	    List<CarImage> imageList = carService.getAllCarImages();

	    List<CarImageDto> ListDto = new ArrayList<>();
	    for (Car c : cList) {
	        
	       
	            CarImageDto dto = new CarImageDto();
	            dto.setId(c.getId());
	            dto.setCar_name(c.getCar_model());
	            dto.setFuel_type(c.getFuel_type());
	            dto.setPrice(c.getPrice());
	            dto.setBody_type(c.getBody_type());
	            dto.setMileage(c.getMileage());
	            dto.setDistance_travelled(c.getDistance_travelled());
	            dto.setOwnership_status(c.getOwnership_status());
	        
	           
	            List<CarImage> iList = imageList.stream()
	                    .filter(i -> i.getCar().getId() == c.getId())
	                    .toList();

	            dto.setImages(iList);
	            ListDto.add(dto);
	    }
	    return ListDto;
	}

	
	/*----------------------------------------Get All Sold cars-----------------------------------------------------*/
	
	@GetMapping("/api/get/all/soldcar")
	public List<Car> getAllSoldCar(){
		return carService.getAllSoldCar();
	}
	
	
	/*-----------------------------------------Get car by name-----------------------------------*/
	@GetMapping("/api/get/all/car/byname")
	public List<CarImageDto> getCname(@RequestParam String cName) {
	    List<Car> cList = carService.getAllCar();
	    List<CarImage> imageList = carService.getAllCarImages();
//	    System.out.println(cName);

	    List<CarImageDto> ListDto = new ArrayList<>();
	    for (Car c : cList) {
	    		if(c.getCar_model().equalsIgnoreCase(cName.trim())) {
	            CarImageDto dto = new CarImageDto();
	            dto.setId(c.getId());
	            dto.setCar_name(c.getCar_model());
	            dto.setFuel_type(c.getFuel_type());
	            dto.setPrice(c.getPrice());
	            dto.setBody_type(c.getBody_type());
	            dto.setMileage(c.getMileage());
	            dto.setDistance_travelled(c.getDistance_travelled());
	            dto.setOwnership_status(c.getOwnership_status());

	           
	            List<CarImage> iList = imageList.stream()
	                    .filter(i -> i.getCar().getId() == c.getId())
	                    .toList();

	            dto.setImages(iList);
	            ListDto.add(dto);
	    		}
	    }
	    return ListDto;
	}


	
}

	
