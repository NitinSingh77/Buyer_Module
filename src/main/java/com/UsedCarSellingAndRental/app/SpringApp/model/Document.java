package com.UsedCarSellingAndRental.app.SpringApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Document {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable= false)
	private String document_upload;
	@Column(nullable= false)
	private String time;
	@Column(nullable= false)
	private String document_type;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDocument_upload() {
		return document_upload;
	}
	public void setDocument_upload(String document_upload) {
		this.document_upload = document_upload;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getDocument_type() {
		return document_type;
	}
	public void setDocument_type(String document_type) {
		this.document_type = document_type;
	}
	
	public Document() {}
	public Document(int id, String document_upload, String time, String document_type) {
		super();
		this.id = id;
		this.document_upload = document_upload;
		this.time = time;
		this.document_type = document_type;
	}
	
	
	
}

