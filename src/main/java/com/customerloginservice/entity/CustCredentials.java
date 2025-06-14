package com.customerloginservice.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table( name = "CUST_CREDENTIALS")
@IdClass(CustCredentials_PK.class)
public class CustCredentials implements Serializable{


    @Serial
    private static final long serialVersionUID = 2365402447685643213L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long custId;
	
	private String cust_name;
	
	@Id
	private String mobile;
	
	
	private String email;
	
	private String otp;
	
	private String password;

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
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

	
	public CustCredentials(long custId, String cust_name, String mobile, String email, String otp, String password) {
		super();
		this.custId = custId;
		this.cust_name = cust_name;
		this.mobile = mobile;
		this.email = email;
		this.otp = otp;
		this.password = password;
	}

	@Override
	public String toString() {
		return "CustCredentials [custId=" + custId + ", cust_name=" + cust_name + ", mobile=" + mobile + ", email="
				+ email + ", otp=" + otp + ", password=" + password + "]";
	}

	public CustCredentials() {
		
	}

	
}
