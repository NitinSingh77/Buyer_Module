package com.UsedCarSellingAndRental.app.SpringApp.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.UsedCarSellingAndRental.app.SpringApp.enums.Role;
import com.UsedCarSellingAndRental.app.SpringApp.exception.InvalidUsernameException;
import com.UsedCarSellingAndRental.app.SpringApp.exception.ResourceNotFoundException;
import com.UsedCarSellingAndRental.app.SpringApp.model.Buyer;
import com.UsedCarSellingAndRental.app.SpringApp.model.User;
import com.UsedCarSellingAndRental.app.SpringApp.repository.BuyerRepository;
import com.UsedCarSellingAndRental.app.SpringApp.repository.UserRepository;

@Service
public class BuyerService {
	
	@Autowired
	private UserService userService;

		
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private BuyerRepository buyerRepository;
	

	
	/*-----------------------------------------get all buyers-----------------------------------*/
	public List<Buyer> getAllBuyer() {
		return buyerRepository.findAll();
	}

	
	/*-----------------------------------------save buyer----------------------------------------*/
	public void saveBuyer(Buyer buyer) {
		buyerRepository.save(buyer);
		
	}

	
	/*----------------------------------insert buyer through excel file--------------------------------*/
	
	public void uploadBuyerThruExcel(MultipartFile file) throws IOException, InvalidUsernameException {
		//Step 1: Convert file into InputStream
		InputStream inputStream= file.getInputStream();
		
		//Step 2: Give this inputStream to bufferedReader
		BufferedReader br= new BufferedReader(new InputStreamReader(inputStream));
		
		//Step 3: Read the content line by line using loop and save them in Buyer object
		br.readLine(); // this reads the header line, which we ignore
		String data="";
		
		List<Buyer> buyerList= new ArrayList<>();
		
		while((data=br.readLine())!=null) {
			Buyer buyer= new Buyer();
			User user= new User();
//			Role role = null;
			String[] str= data.split(",");
			
			buyer.setEmail(str[1]);
			buyer.setPhone(str[2]);
			buyer.setAddress(str[3]);
			user.setName(str[0]);
			user.setUsername(str[5]);
			user.setPassword(str[6]);
			user.setRole(Role.BUYER);
			user=userService.signUp(user);
			buyer.setUser(user);
			
			buyerList.add(buyer);	
			
		}
		buyerRepository.saveAll(buyerList);
	}

	
	/*-----------------------------------Logic to validate using ID----------------------------------*/
	public Buyer Validate(int id) throws ResourceNotFoundException {
		Optional<Buyer> optional= buyerRepository.findById(id);
		
		if(optional.isEmpty())
		{
			throw new ResourceNotFoundException("Invalid Id");
		}
		
		return optional.get();
		
	}
	
	
	
	/*----------------------------------Delete buyer data using ID---------------------------------------*/
	public void deleteBuyerById(int id) {
		Optional<Buyer> optional= buyerRepository.findById(id);
		Buyer buyer= optional.get();
		int user_id= buyer.getUser().getId();
		buyerRepository.deleteById(id);
		userRepository.deleteById(user_id);
		
	}


	public Buyer getBuyerById(int id) throws ResourceNotFoundException {
		Optional<Buyer> optional = buyerRepository.findById(id);

		if (optional.isEmpty()) {
			throw new ResourceNotFoundException("Invalid car ID");
		}

		return optional.get();

	}

}
