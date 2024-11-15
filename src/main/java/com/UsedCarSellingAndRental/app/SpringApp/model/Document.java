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
	private String verified;
	@Column(nullable= false)
	private String document_type;
	@Column(nullable= false)
	private String picture_path;
	
	
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
	public String getVerified() {
		return verified;
	}
	public void setVerified(String verified) {
		this.verified = verified;
	}
	public String getDocument_type() {
		return document_type;
	}
	public void setDocument_type(String document_type) {
		this.document_type = document_type;
	}
	public String getPicture_path() {
		return picture_path;
	}
	public void setPicture_path(String picture_path) {
		this.picture_path = picture_path;
	}
	@Override
	public String toString() {
		return "Document [id=" + id + ", document_upload=" + document_upload + ", time=" + time + ", verified="
				+ verified + ", document_type=" + document_type + ", picture_path=" + picture_path + "]";
	}
	
	
}

