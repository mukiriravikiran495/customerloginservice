package com.customerloginservice.service;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.customerloginservice.constants.AppConstants;
import com.customerloginservice.domain.CustomerDetailsDTO;
import com.customerloginservice.domain.CustomerResponse;
import com.customerloginservice.domain.OTPRequest;
import com.customerloginservice.domain.OTPResponse;
import com.customerloginservice.domain.VerifyOTPResponse;
import com.customerloginservice.entity.CustCredentials;
import com.customerloginservice.entity.CustomerDetails;
import com.customerloginservice.exceptions.InvalidRequestException;
import com.customerloginservice.exceptions.StatusHandler;
import com.customerloginservice.mapper.CustomerMapper;
import com.customerloginservice.repository.CustomerDetailsRepository;
import com.customerloginservice.repository.CustomerLoginRepository;
import com.customerloginservice.utils.CustomerUtils;



@Service("CustomerService")
public class CustomerLoginServiceImpl implements CustomerLoginService {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	
	private final CustomerLoginRepository customerLoginRepository;
	private final CustomerDetailsRepository customerDetailsRepository;
	private final CustomerMapper mapper;
	private final CustomerUtils customerUtils;

	@Autowired
    public CustomerLoginServiceImpl(CustomerLoginRepository customerLoginRepository, CustomerDetailsRepository customerDetailsRepository,
    		CustomerMapper mapper, CustomerUtils customerUtils) {
        this.customerLoginRepository = customerLoginRepository;
        this.customerDetailsRepository = customerDetailsRepository;
        this.mapper = mapper;
        this.customerUtils = customerUtils;
    }

	public List<CustCredentials> getAll() {
		
		return customerLoginRepository.findAll();
	}

	@Override
	@Transactional
	public OTPResponse sendOTP(OTPRequest otpRequest, OTPResponse otpResponse, StatusHandler statusHandler) throws InvalidRequestException {
		logger.info("START : sendOTP Service : "+ otpRequest);
		
		String otp = generateOTP();
		
		Optional<CustCredentials> optionalCust = customerLoginRepository.findByMobile(otpRequest.getMobile());

        if (optionalCust.isEmpty()) {
            // New Customer
            CustCredentials cust = new CustCredentials();
            cust.setMobile(otpRequest.getMobile());
            cust.setOtp(otp);
            customerLoginRepository.save(cust);

            otpResponse.setMessage(AppConstants.OTPSENT);
        } else {
            // Existing Customer - update OTP
            CustCredentials cust = optionalCust.get();
            cust.setOtp(otp);
            customerLoginRepository.save(cust);

            otpResponse.setCustId(cust.getCustId());
            otpResponse.setCust_name(cust.getCustName());
            otpResponse.setMessage(AppConstants.ACCOUNT_ALREADY_EXISTS);
        }
        
        otpResponse.setOtp(otp);
        otpResponse.setMobile(otpRequest.getMobile());
        statusHandler.setStatusCode("200");
        statusHandler.setMessage("OTP Sent Successfully");
        otpResponse.setStatusHandler(statusHandler);
        logger.info("END : sendOTP Service : "+ otpResponse);
        return otpResponse;
		
	}

	@Override
	@Transactional
	public VerifyOTPResponse verifyOTP(OTPRequest otpRequest, VerifyOTPResponse verifyOTPResponse, StatusHandler statusHandler) throws InvalidRequestException{
		logger.info("START : verifyOTP Service : "+ otpRequest);
		
		Optional<CustCredentials> optionalCust = customerLoginRepository.findByMobile(otpRequest.getMobile());

        if (optionalCust.isEmpty()) {
            statusHandler.setMessage("USER_NOT_FOUND");
            logger.error("USER_NOT_FOUND in DB");
            return verifyOTPResponse;
        }

        CustCredentials cust = optionalCust.get();
        
        if (cust.getOtp().equals(otpRequest.getOtp())) {
        	if(otpRequest.getMessage().equalsIgnoreCase(AppConstants.ACCOUNT_ALREADY_EXISTS)) {
        		verifyOTPResponse.setOtpVerified(true);
                verifyOTPResponse.setMessage(AppConstants.OTP_VERIFIED_SUCCESFULLY);
                verifyOTPResponse.setOtp(otpRequest.getOtp());
                verifyOTPResponse.setMobile(cust.getMobile());
                verifyOTPResponse.setCustId(cust.getCustId());
                verifyOTPResponse.setMobile(cust.getMobile());
                statusHandler.setStatusCode("200");
                statusHandler.setMessage("OTP VERIFICATION SUCCESSFUL");
                verifyOTPResponse.setStatusHandler(statusHandler);
        	}else {
        		CustomerDetailsDTO custDetailsDTO = new CustomerDetailsDTO();
                custDetailsDTO.setcMobile(cust.getMobile());
                custDetailsDTO.setCustId(cust.getCustId());
                
                CustomerResponse savedCustomer = customerUtils.createCustomer(custDetailsDTO);
                Optional.ofNullable(savedCustomer).orElseThrow(() -> new RuntimeException(AppConstants.CUSTOMER_DETAILS_NOT_SAVED));
                System.out.println(savedCustomer.toString());
                verifyOTPResponse.setCustId(savedCustomer.getDetailsDTO().getCustId());
                verifyOTPResponse.setMobile(savedCustomer.getDetailsDTO().getcMobile());
                statusHandler.setStatusCode("200");
                statusHandler.setMessage("CUSTOMER CREATED SUCCESFULLY");
                verifyOTPResponse.setStatusHandler(statusHandler);
//                customerDetailsRepository.save(custDetails);
        	}
              
            logger.info("END : VerifyOTP Repository : " + verifyOTPResponse);
            return verifyOTPResponse;
        } else {
            verifyOTPResponse.setOtpVerified(false);
            verifyOTPResponse.setMessage(AppConstants.OTP_VERIFIED_FAILED);
            statusHandler.setStatusCode("400");
            statusHandler.setMessage("OTP VERIFICATION FAILED");
            return verifyOTPResponse;
        }
        
        
        
	}
	
	private String generateOTP() {
        Random random = new Random();
        int otp = 1000 + random.nextInt(9000); // ensures 4-digit OTP
        return String.valueOf(otp);
    }

	@Override
	@Transactional(readOnly = true)
	public List<CustomerDetailsDTO> getallcustomers() {
		List<CustomerDetails> list =  customerDetailsRepository.findAll();
		return mapper.toDtoList(list);
	}

}
