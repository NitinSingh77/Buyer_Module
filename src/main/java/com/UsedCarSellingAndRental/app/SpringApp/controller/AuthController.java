package com.UsedCarSellingAndRental.app.SpringApp.controller;

import java.io.IOException;
import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.UsedCarSellingAndRental.app.SpringApp.JwtUtil;
import com.UsedCarSellingAndRental.app.SpringApp.dto.JwtDto;
import com.UsedCarSellingAndRental.app.SpringApp.dto.ResponseMessageDto;
import com.UsedCarSellingAndRental.app.SpringApp.enums.Role;
import com.UsedCarSellingAndRental.app.SpringApp.exception.InvalidUsernameException;
import com.UsedCarSellingAndRental.app.SpringApp.exception.ResourceNotFoundException;
import com.UsedCarSellingAndRental.app.SpringApp.model.Buyer;
import com.UsedCarSellingAndRental.app.SpringApp.model.User;
import com.UsedCarSellingAndRental.app.SpringApp.service.BuyerService;
import com.UsedCarSellingAndRental.app.SpringApp.service.UserSecurityService;
import com.UsedCarSellingAndRental.app.SpringApp.service.UserService;
@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserSecurityService userSecurityService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BuyerService buyerService;
	
	Logger logger = LoggerFactory.getLogger(AuthController.class);
	

	/*---------------------Generating token-----------------------------------------------*/
	@PostMapping("/api/token")
	public ResponseEntity<?> getToken(@RequestBody User user, JwtDto dto ) {
		try {
		Authentication auth 
				= new UsernamePasswordAuthenticationToken
							(user.getUsername(), user.getPassword());
		
		authenticationManager.authenticate(auth);
		
		/*Check if username is in DB */
		user = (User) userSecurityService.loadUserByUsername(user.getUsername());
		
		String jwt = jwtUtil.generateToken(user.getUsername());
		dto.setUsername(user.getUsername());
		dto.setToken(jwt);
		return ResponseEntity.ok(dto);
		}
		catch(AuthenticationException ae) {
			return ResponseEntity.badRequest().body(ae.getMessage());
		}
		
	}
	
	
	
	/*================================Buyer functionalities=============================================*/
	
	/*-------------------------------buyer sign up (permit all)--------------------------------------*/
	@PostMapping("/auth/sigh-up/buyer")
	public ResponseEntity<?> buyerSignUp(@RequestBody Buyer buyer, ResponseMessageDto dto)
	{
//		System.out.println(buyer);
//		System.out.println("123");
//		System.out.println(buyer);
		try {
			User user = new User();
			user.setName(buyer.getUser().getName());
			user.setUsername(buyer.getUser().getUsername());
			user.setPassword(buyer.getUser().getPassword());
			user.setRole(Role.BUYER);			
			user= userService.signUp(user);
			buyer.setUser(user);
			buyer.setEmail(buyer.getEmail());
			buyer.setPhone(buyer.getPhone());
			buyer.setAddress(buyer.getAddress());
			
			
			buyerService.saveBuyer(buyer);
			dto.setMsg("Buyer Sign-up Successful!");
			return ResponseEntity.ok(dto);
		} catch (InvalidUsernameException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
				
		
	}
	
	
	
	
	/*-----------------------Executive functionality-------------------------------------*/
	
	@PostMapping("/api/buyer/batch-insert")
	public ResponseEntity<?> uploadBuyerThruExcel(@RequestBody MultipartFile file){
		
		try {
			buyerService.uploadBuyerThruExcel(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidUsernameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok("Records inserted in DB using spring batch");
	}
	
	
	/*---------------------------------Executive functionality---------------------------------------------------------------------------------*/
	
	@DeleteMapping("/api/buyer/delete/{id}")
	public ResponseEntity<?> deleteBuyerById(@PathVariable int id, ResponseMessageDto dto) throws ResourceNotFoundException
	{
		logger.info("Deleting buyer by id");
		buyerService.Validate(id);
		buyerService.deleteBuyerById(id);
		dto.setMsg("Buyer deleted");
		logger.info("buyer deleted with ID: " + id);
		return ResponseEntity.ok(dto);
	}
	
	/*========================================Buyer functionality end here=====================================================================*/
	
	@GetMapping("/auth/user")
	public User getUserDetails(Principal principal) {
		String loggedInsername= principal.getName();
		User user= (User) userSecurityService.loadUserByUsername(loggedInsername);
		return user;
	}
	
	@PostMapping("/auth/sign-up")
	public ResponseEntity<?> signUp(@RequestBody User user,ResponseMessageDto dto){
		try {
			return ResponseEntity.ok(userService.signUp(user));
		} catch (InvalidUsernameException e) {
			dto.setMsg(e.getMessage());
			 return ResponseEntity.badRequest().body(dto);
		}
	}
	
}


