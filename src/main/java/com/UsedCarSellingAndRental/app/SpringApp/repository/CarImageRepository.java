package com.UsedCarSellingAndRental.app.SpringApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.UsedCarSellingAndRental.app.SpringApp.model.CarImage;

public interface CarImageRepository extends JpaRepository<CarImage, Integer>{

}
