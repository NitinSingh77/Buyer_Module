package com.UsedCarSellingAndRental.app.SpringApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.UsedCarSellingAndRental.app.SpringApp.dto.ResponseMessageDto;
import com.UsedCarSellingAndRental.app.SpringApp.exception.InvalidCredentialsException;
import com.UsedCarSellingAndRental.app.SpringApp.exception.InvalidUsernameException;
import com.UsedCarSellingAndRental.app.SpringApp.exception.ResourceNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	@Autowired
	private ResponseMessageDto dto;
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(Exception e){
		dto.setMsg(e.getMessage());
		return ResponseEntity.badRequest().body(dto);
	}
	
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<?> handleInvalidCredentialsException(Exception e){
		dto.setMsg(e.getMessage());
		return ResponseEntity.badRequest().body(dto);
	}
	
	@ExceptionHandler(InvalidUsernameException.class)
	public ResponseEntity<?> handleInvalidUsernameException(Exception e){
		dto.setMsg(e.getMessage());
		return ResponseEntity.badRequest().body(dto);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGeneralException(Exception e){
		dto.setMsg(e.getMessage());
		return ResponseEntity.badRequest().body(dto);
	}
	
	
	
	
}
