package com.customerloginservice.domain;

import java.io.Serial;
import java.io.Serializable;

import com.customerloginservice.exceptions.StatusHandler;

public class OTPResponse implements Serializable{

    @Serial
    private static final long serialVersionUID = 1L;
	
	private String mobile;
	private String otp;
	private String message;
	private long custId;
	private String cust_name;
	private StatusHandler statusHandler;

	public StatusHandler getStatusHandler() {
		return statusHandler;
	}

	public void setStatusHandler(StatusHandler statusHandler) {
		this.statusHandler = statusHandler;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
