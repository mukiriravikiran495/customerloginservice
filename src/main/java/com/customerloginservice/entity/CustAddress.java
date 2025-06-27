package com.customerloginservice.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table( name = "CUST_ADDRESS", schema = "CUSTOMER")
public class CustAddress {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private long cAddressId;
	
	private String cAddress1;
	
	private String cCity;
	
	private String cState;
	
	private String cZipcode;
	
	@OneToOne
    @JoinColumn(name = "custId") // FK in CUST_ADDRESS table
    private CustomerDetails cust;

	public CustAddress() {
		
	}

	public long getcAddressId() {
		return cAddressId;
	}

	public void setcAddressId(long cAddressId) {
		this.cAddressId = cAddressId;
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

	public CustomerDetails getCust() {
		return cust;
	}

	public void setCust(CustomerDetails cust) {
		this.cust = cust;
	}

}
