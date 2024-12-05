package com.UsedCarSellingAndRental.app.SpringApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.UsedCarSellingAndRental.app.SpringApp.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

	@Query("SELECT c FROM Car c WHERE LOWER(c.body_type)=?1")
	List<Car> searchByBodyType(String bodyType);

	@Query("update Car c set carStatus='Sold' where id=?1")
	void updateStatus(int id);

	@Query("Select c from Car c where c.price<=?1")
	List<Car> searchByPrice(double price);
	
}