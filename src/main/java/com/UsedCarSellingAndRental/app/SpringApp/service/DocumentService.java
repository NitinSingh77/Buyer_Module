package com.UsedCarSellingAndRental.app.SpringApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UsedCarSellingAndRental.app.SpringApp.model.Document;
import com.UsedCarSellingAndRental.app.SpringApp.repository.DocumentRepository;

@Service
public class DocumentService {

	@Autowired
	private DocumentRepository documentRepository;
	
	/*-------------------Save document--------------------------------*/
	public void saveDocument(Document document) {
		
		documentRepository.save(document);
	}

}
