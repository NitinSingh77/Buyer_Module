package com.UsedCarSellingAndRental.app.SpringApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UsedCarSellingAndRental.app.SpringApp.enums.Role;
import com.UsedCarSellingAndRental.app.SpringApp.exception.InvalidUsernameException;
import com.UsedCarSellingAndRental.app.SpringApp.model.Seller;
import com.UsedCarSellingAndRental.app.SpringApp.model.User;
import com.UsedCarSellingAndRental.app.SpringApp.repository.SellerRepository;

@Service
public class SellerService {

	@Autowired
	private SellerRepository sellerRepository;
	
	@Autowired
	private UserService userService;
	
	public List<Seller> getAllSeller() {
		return sellerRepository.findAll();
	}


	public Seller addSeller(Seller seller) throws InvalidUsernameException {
		User user= new User();
		
//		user.setName(seller.getUser().getName());
		user.setPassword(seller.getUser().getPassword());
		user.setRole(Role.SELLER);
		user= userService.signUp(user);
		
		seller.setUser(user);
		seller.setContactInfo(seller.getContactInfo());
		seller.setLocation(seller.getLocation());
		
		return sellerRepository.save(seller);
	}

}
