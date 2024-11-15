package com.UsedCarSellingAndRental.app.SpringApp.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class BoughtCar {
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable= false)
	private LocalDate date_of_purchase;

	@ManyToOne
	private Buyer buyer;
	
	@OneToOne
	private Car car;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate_of_purchase() {
		return date_of_purchase;
	}

	public void setDate_of_purchase(LocalDate date_of_purchase) {
		this.date_of_purchase = date_of_purchase;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
	
	
}
