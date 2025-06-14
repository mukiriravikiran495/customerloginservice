package com.customerloginservice.controller;

import java.lang.invoke.MethodHandles;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customerloginservice.constants.AppConstants;
import com.customerloginservice.domain.CustomerDetailsDTO;
import com.customerloginservice.domain.OTPRequest;
import com.customerloginservice.domain.OTPResponse;
import com.customerloginservice.domain.TokenID;
import com.customerloginservice.domain.VerifyOTPResponse;
import com.customerloginservice.entity.CustCredentials;
import com.customerloginservice.exceptions.InvalidRequestException;
import com.customerloginservice.exceptions.StatusHandler;
import com.customerloginservice.service.CustomerLoginService;
import com.customerloginservice.utils.JwtUtil;
import com.google.gson.Gson;

@RestController
@RequestMapping( path = "/v1/api/customer")
public class CustomerLoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	
	private final CustomerLoginService service;
    private final JwtUtil jwtUtil;
    
    @Autowired
    public CustomerLoginController(CustomerLoginService service, JwtUtil jwtUtil) {
    	this.service = service;
    	this.jwtUtil = jwtUtil;
    }

	@GetMapping( value = "/auth/token")
	public ResponseEntity<TokenID> getToken() {
		String token = jwtUtil.generateTokenId();
		TokenID tokenId = new TokenID();
		tokenId.setToken(token);
		long timestamp = new Date().getTime();
		tokenId.setExpires(timestamp);
		tokenId.setStatus("200");
		tokenId.setResult(AppConstants.TOKEN_GENERATED_SUCCESSFULLY);
		return new ResponseEntity<>(tokenId, HttpStatus.OK);
	}
    
	
	@GetMapping( value = "/getall")
	public List<CustCredentials> getAll(){
		return service.getAll();
	}
	
	
	@PostMapping( value = "/sendotp")
	public ResponseEntity<OTPResponse> sendOTP( @RequestBody OTPRequest otpRequest) throws InvalidRequestException {
		logger.info("Strat : Send OTP Controller :"+otpRequest.toString());
		logger.warn("SendOTP Method : OTPRequest : ".formatted(new Gson().toJson(otpRequest).toString()));
		
		OTPResponse otpResponse = new OTPResponse();
		StatusHandler statusHandler = new StatusHandler();
		try {
			if(null == otpRequest.getMobile() || otpRequest.getMobile().isEmpty()) {
				throw new InvalidRequestException(AppConstants.MOBILE_NUMBER_IS_REQUIRED);
			}
			
			if(!otpRequest.getMobile().matches("\\d{10}")) {
				throw new InvalidRequestException(AppConstants.ENTER_10_DIGIT_MOBILENUMBER);
			}
			
			otpResponse =  service.sendOTP(otpRequest, otpResponse, statusHandler);
			ResponseEntity<OTPResponse> responseEntity = new ResponseEntity<>(otpResponse, HttpStatus.OK);
			
			logger.info("END : Send OTP Controller :"+otpResponse.toString());
			logger.warn("SendOTP Method : OTPResponse : ".formatted(new Gson().toJson(otpResponse)));
			return responseEntity;
		}catch(InvalidRequestException ex) {
			logger.error(" Error in SendOTP : "+ ex.getMessage());
			statusHandler.setStatusCode("400");
			statusHandler.setMessage(ex.getMessage());
			otpResponse.setStatusHandler(statusHandler);
			return new ResponseEntity<>(otpResponse,HttpStatus.BAD_REQUEST);
		}catch(Exception ex) {
			logger.error(" Error in SendOTP : "+ ex.getMessage());
			statusHandler.setStatusCode("500");
			statusHandler.setMessage(ex.getMessage());
			otpResponse.setStatusHandler(statusHandler);
			return new ResponseEntity<>(otpResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping( value = "/verifyotp")
	public ResponseEntity<VerifyOTPResponse> verifyOTP( @RequestBody OTPRequest otpRequest) throws InvalidRequestException {
		logger.info("START : VERIFY OTP Controller : "+ otpRequest.toString());
		logger.warn("VERIFY OTP Method Request : ".formatted(new Gson().toJson(otpRequest).toString()));
		
		VerifyOTPResponse verifyOTPResponse = new VerifyOTPResponse();
		StatusHandler statusHandler = new StatusHandler();
		try {
			if(null == otpRequest.getOtp() || otpRequest.getOtp().isEmpty()) {
				throw new InvalidRequestException(AppConstants.OTP_NOT_FOUND);
			}
			
			if(!otpRequest.getOtp().matches("\\d{4}")) {
				throw new InvalidRequestException(AppConstants.ENTER_VALID_OTP);
			}
			String token = jwtUtil.generateToken(otpRequest.getMobile());
			verifyOTPResponse.setToken(token);
			
			verifyOTPResponse = service.verifyOTP(otpRequest, verifyOTPResponse, statusHandler);
			ResponseEntity<VerifyOTPResponse> responseEntity = new ResponseEntity<>(verifyOTPResponse, HttpStatus.OK);
			
			logger.info("END : Send OTP Controller :"+verifyOTPResponse.toString());
			logger.warn("SendOTP Method : OTPResponse : ".formatted(new Gson().toJson(verifyOTPResponse)));
			return responseEntity;
		}catch(InvalidRequestException ex) {
			logger.error(" Error in SendOTP : "+ ex.getMessage());
			statusHandler.setStatusCode("400");
			statusHandler.setMessage(ex.getMessage());
			verifyOTPResponse.setStatusHandler(statusHandler);
			return new ResponseEntity<>(verifyOTPResponse,HttpStatus.BAD_REQUEST);
		}catch(Exception ex) {
			logger.error(" Error in SendOTP : "+ ex.getMessage());
			statusHandler.setStatusCode("500");
			statusHandler.setMessage(ex.getMessage());
			verifyOTPResponse.setStatusHandler(statusHandler);
			return new ResponseEntity<>(verifyOTPResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@PostMapping( value = "/resendotp")
	public ResponseEntity<OTPResponse> resendOTP( @RequestBody OTPRequest otpRequest) throws InvalidRequestException {
		logger.info("Strat : Reend OTP Controller :"+otpRequest.toString());
		logger.warn("reendOTP Method : OTPRequest : ".formatted(new Gson().toJson(otpRequest).toString()));
		
		OTPResponse otpResponse = new OTPResponse();
		StatusHandler statusHandler = new StatusHandler();
		try {
			if(null == otpRequest.getMobile() || otpRequest.getMobile().isEmpty()) {
				throw new InvalidRequestException(AppConstants.MOBILE_NUMBER_IS_REQUIRED);
			}
			
			if(!otpRequest.getMobile().matches("\\d{10}")) {
				throw new InvalidRequestException(AppConstants.ENTER_10_DIGIT_MOBILENUMBER);
			}
			
			otpResponse =  service.sendOTP(otpRequest, otpResponse, statusHandler);
			ResponseEntity<OTPResponse> responseEntity = new ResponseEntity<>(otpResponse, HttpStatus.OK);
			
			logger.info("END : Reend OTP Controller :"+otpResponse.toString());
			logger.warn("ReendOTP Method : OTPResponse : ".formatted(new Gson().toJson(otpResponse)));
			return responseEntity;
		}catch(InvalidRequestException ex) {
			logger.error(" Error in ReendOTP : "+ ex.getMessage());
			statusHandler.setStatusCode("400");
			statusHandler.setMessage(ex.getMessage());
			otpResponse.setStatusHandler(statusHandler);
			return new ResponseEntity<>(otpResponse,HttpStatus.BAD_REQUEST);
		}catch(Exception ex) {
			logger.error(" Error in ReendOTP : "+ ex.getMessage());
			statusHandler.setStatusCode("500");
			statusHandler.setMessage(ex.getMessage());
			otpResponse.setStatusHandler(statusHandler);
			return new ResponseEntity<>(otpResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
}
