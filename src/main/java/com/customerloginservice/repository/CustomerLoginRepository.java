package com.customerloginservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customerloginservice.entity.CustCredentials;
import com.customerloginservice.entity.CustCredentials_PK;

@Repository
public interface CustomerLoginRepository extends JpaRepository<CustCredentials, CustCredentials_PK> {

    Optional<CustCredentials> findByMobile(String mobile);  // JPA will auto-generate this method
}
