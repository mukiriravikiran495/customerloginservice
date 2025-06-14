package com.customerloginservice.entity;

import java.io.Serializable;
import java.util.Objects;

public class CustCredentials_PK implements Serializable{

	private long custId;
	
	private String mobile;

	

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	

	public CustCredentials_PK() {
		
	}

	
	
}
