package com.customerloginservice.domain;

import java.time.LocalDateTime;
import java.util.List;

public class CustomerDetailsDTO {

	private Long custId;
	private String cFirstname;
	private String cLastname;
	private String cMobile;
	private String cEmail;
	private String cAddress1;
	
	private String cCity;
	
	private String cState;
	
	private String cZipcode;
	
	private Double pickupLattitude;
	
	private Double pickupLongitude;
	private LocalDateTime createdAt = LocalDateTime.now();
    private String createdBy;
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public Long getCustId() {
		return custId;
	}


	public void setCustId(Long custId) {
		this.custId = custId;
	}


	public String getcFirstname() {
		return cFirstname;
	}


	public void setcFirstname(String cFirstname) {
		this.cFirstname = cFirstname;
	}


	public String getcLastname() {
		return cLastname;
	}


	public void setcLastname(String cLastname) {
		this.cLastname = cLastname;
	}


	public String getcMobile() {
		return cMobile;
	}


	public void setcMobile(String cMobile) {
		this.cMobile = cMobile;
	}


	public String getcEmail() {
		return cEmail;
	}


	public void setcEmail(String cEmail) {
		this.cEmail = cEmail;
	}


	public String getcAddress1() {
		return cAddress1;
	}


	public void setcAddress1(String cAddress1) {
		this.cAddress1 = cAddress1;
	}


	public String getcCity() {
		return cCity;
	}


	public void setcCity(String cCity) {
		this.cCity = cCity;
	}


	public String getcState() {
		return cState;
	}


	public void setcState(String cState) {
		this.cState = cState;
	}


	public String getcZipcode() {
		return cZipcode;
	}


	public void setcZipcode(String cZipcode) {
		this.cZipcode = cZipcode;
	}


	public Double getPickupLattitude() {
		return pickupLattitude;
	}


	public void setPickupLattitude(Double pickupLattitude) {
		this.pickupLattitude = pickupLattitude;
	}


	public Double getPickupLongitude() {
		return pickupLongitude;
	}


	public void setPickupLongitude(Double pickupLongitude) {
		this.pickupLongitude = pickupLongitude;
	}


	public CustomerDetailsDTO() {
		
	}
}
