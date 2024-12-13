package com.UsedCarSellingAndRental.app.SpringApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.UsedCarSellingAndRental.app.SpringApp.model.Buyer;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Integer>{

	
	@Query("select b from Buyer b join b.user u where u.username=?1")
	Buyer getBuyerDetailsByUsername(String username);

}

