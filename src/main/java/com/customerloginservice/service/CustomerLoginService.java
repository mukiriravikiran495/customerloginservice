package com.customerloginservice.service;

import java.util.List;

import com.customerloginservice.domain.CustomerDetailsDTO;
import com.customerloginservice.domain.OTPRequest;
import com.customerloginservice.domain.OTPResponse;
import com.customerloginservice.domain.VerifyOTPResponse;
import com.customerloginservice.entity.CustCredentials;
import com.customerloginservice.exceptions.InvalidRequestException;
import com.customerloginservice.exceptions.StatusHandler;

public interface CustomerLoginService {

	List<CustCredentials> getAll();

	OTPResponse sendOTP(OTPRequest otpRequest, OTPResponse otpResponse, StatusHandler statusHandler) throws InvalidRequestException;

	VerifyOTPResponse verifyOTP(OTPRequest otpRequest, VerifyOTPResponse verifyOTPResponse, StatusHandler statusHandler) throws InvalidRequestException;

	List<CustomerDetailsDTO> getallcustomers();

}
