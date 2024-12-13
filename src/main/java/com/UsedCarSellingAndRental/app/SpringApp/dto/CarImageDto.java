package com.UsedCarSellingAndRental.app.SpringApp.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.UsedCarSellingAndRental.app.SpringApp.enums.FuelType;
import com.UsedCarSellingAndRental.app.SpringApp.model.CarImage;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Component
public class CarImageDto {
	private int id;
	
	private String car_name;
	@Enumerated(EnumType.STRING)
	private FuelType fuel_type;
	private String body_type;
	private double price;
	
	
	private String distance_travelled;
	
	private String mileage;
	
	private String ownership_status;
	
	
	
	List<CarImage> images;
	
	
	
	
	public String getOwnership_status() {
		return ownership_status;
	}
	public void setOwnership_status(String ownership_status) {
		this.ownership_status = ownership_status;
	}
	
	
	public String getDistance_travelled() {
		return distance_travelled;
	}
	public void setDistance_travelled(String distance_travelled) {
		this.distance_travelled = distance_travelled;
	}
	public String getMileage() {
		return mileage;
	}
	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	public String getBody_type() {
		return body_type;
	}
	public void setBody_type(String body_type) {
		this.body_type = body_type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCar_name() {
		return car_name;
	}
	public void setCar_name(String car_name) {
		this.car_name = car_name;
	}
	
	
	public FuelType getFuel_type() {
		return fuel_type;
	}
	public void setFuel_type(FuelType fuel_type) {
		this.fuel_type = fuel_type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public List<CarImage> getImages() {
		return images;
	}
	public void setImages(List<CarImage> images) {
		this.images = images;
	}
	
	
}
