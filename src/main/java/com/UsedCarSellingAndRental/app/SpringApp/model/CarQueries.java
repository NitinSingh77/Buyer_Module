package com.UsedCarSellingAndRental.app.SpringApp.model;

import java.time.LocalDate;

import com.UsedCarSellingAndRental.app.SpringApp.enums.QueryStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class CarQueries {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private LocalDate date;
	private String message;
	@Enumerated(EnumType.STRING)
	private QueryStatus queryStatus;

	public QueryStatus getQueryStatus() {
		return queryStatus;
	}

	public void setQueryStatus(QueryStatus queryStatus) {
		this.queryStatus = queryStatus;
	}

	@ManyToOne
	private Buyer buyer;
	
	@ManyToOne
	private Car car;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	@Override
	public String toString() {
		return "CarQueries [id=" + id + ", date=" + date + ", message=" + message + ", buyer=" + buyer + ", car=" + car
				+ "]";
	}

	
	
}
