package com.UsedCarSellingAndRental.app.SpringApp.model;

import com.UsedCarSellingAndRental.app.SpringApp.enums.CarStatus;
import com.UsedCarSellingAndRental.app.SpringApp.enums.FuelType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String car_model;
	private String purchase_year;
	
	@Enumerated(EnumType.STRING)
	private FuelType fuel_type;

	@Enumerated(EnumType.STRING)
	private CarStatus carStatus;

	private String body_type;

	private double price;

	private String car_condition;
	
	private String ownership_status;
	private String distance_travelled;
	
	private String mileage;
	

	@OneToOne
	private Document document;
	
	
	

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCar_model() {
		return car_model;
	}

	public void setCar_model(String car_model) {
		this.car_model = car_model;
	}

	public String getPurchase_year() {
		return purchase_year;
	}

	public void setPurchase_year(String purchase_year) {
		this.purchase_year = purchase_year;
	}

	

	public FuelType getFuel_type() {
		return fuel_type;
	}

	public void setFuel_type(FuelType fuel_type) {
		this.fuel_type = fuel_type;
	}

	public String getBody_type() {
		return body_type;
	}

	public void setBody_type(String body_type) {
		this.body_type = body_type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCar_condition() {
		return car_condition;
	}

	public void setCar_condition(String car_condition) {
		this.car_condition = car_condition;
	}

	public CarStatus getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(CarStatus carStatus) {
		this.carStatus = carStatus;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Car(int id, String car_model, String purchase_year, FuelType fuel_type, CarStatus carStatus,
			String body_type, double price, String car_condition, String ownership_status, String distance_travelled,
			String mileage, Document document) {
		super();
		this.id = id;
		this.car_model = car_model;
		this.purchase_year = purchase_year;
		this.fuel_type = fuel_type;
		this.carStatus = carStatus;
		this.body_type = body_type;
		this.price = price;
		this.car_condition = car_condition;
		this.ownership_status = ownership_status;
		this.distance_travelled = distance_travelled;
		this.mileage = mileage;
		this.document = document;
	}
	
	public Car() {}

	
	

}
