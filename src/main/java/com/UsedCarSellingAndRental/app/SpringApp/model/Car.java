 package com.UsedCarSellingAndRental.app.SpringApp.model;

import com.UsedCarSellingAndRental.app.SpringApp.enums.CarStatus;

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
	private String fuel_type;
	
	@Enumerated(EnumType.STRING)
	private CarStatus carStatus;
	
	private String body_type;
	
	private double price;
	
	private String car_condition;
	private String  verified;
	
	
	@OneToOne
	private Document document;

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

	public String getFuel_type() {
		return fuel_type;
	}

	public void setFuel_type(String fuel_type) {
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

	public String getVerified() {
		return verified;
	}

	public CarStatus getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(CarStatus carStatus) {
		this.carStatus = carStatus;
	}

	public void setVerified(String verified) {
		this.verified = verified;
	}


	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	
	
	
}
