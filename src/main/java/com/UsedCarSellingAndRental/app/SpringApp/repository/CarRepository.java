package com.UsedCarSellingAndRental.app.SpringApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.UsedCarSellingAndRental.app.SpringApp.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

	@Query("update Car c set carStatus='Sold' where id=?1")
	void updateStatus(int id);

	@Query("Select c from Car c where c.price<=?1")
	List<Car> searchByPrice(double price);
	
	@Query("select c from Car c where c.car_model=?1")
	Car getCarByName(String name);

	
	@Query("select c from Car c where c.carStatus='Available_for_sale'")
	List<Car> getAllCar();

	
	@Query("select c from Car c where c.car_model=?1")
	List<Car> getAllCarByName(String carName);

	
	@Query("select c from Car c where c.carStatus='Sold'")
	List<Car> getAllSoldCar();

	
	
	
}