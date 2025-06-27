package com.customerloginservice.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table( name = "CUST_CREDENTIALS")
@IdClass(CustCredentials_PK.class)
public class CustCredentials implements Serializable{


    @Serial
    private static final long serialVersionUID = 2365402447685643213L;
    
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMERLOGIN_SEQ_GEN")
	@SequenceGenerator(name = "CUSTOMERLOGIN_SEQ_GEN", sequenceName = "CUSTOMERLOGIN_SEQ", allocationSize = 1)
	private long custId;
	
	private String custName;
	
	@Id
	private String mobile;
	
	
	private String email;
	
	private String otp;
	
	private String password;
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

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public CustCredentials(long custId, String custName, String mobile, String email, String otp, String password) {
		super();
		this.custId = custId;
		this.custName = custName;
		this.mobile = mobile;
		this.email = email;
		this.otp = otp;
		this.password = password;
	}

	public CustCredentials() {
		
	}

	@Override
	public String toString() {
		return "CustCredentials [custId=" + custId + ", custName=" + custName + ", mobile=" + mobile + ", email="
				+ email + ", otp=" + otp + ", password=" + password + "]";
	}

	
}
