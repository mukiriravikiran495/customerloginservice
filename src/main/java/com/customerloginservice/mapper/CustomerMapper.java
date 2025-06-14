package com.customerloginservice.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.customerloginservice.domain.CustAddressDTO;
import com.customerloginservice.domain.CustomerDetailsDTO;
import com.customerloginservice.entity.CustAddress;
import com.customerloginservice.entity.CustomerDetails;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    // Entity to DTO
    @Mapping(source = "custId", target = "custId")
    @Mapping(source = "c_firstName", target = "c_firstName")
    @Mapping(source = "c_lastName", target = "c_lastName")
    @Mapping(source = "c_mobile", target = "c_mobile")
    @Mapping(source = "c_email", target = "c_email")
    @Mapping(source = "custAddress", target = "address")
    CustomerDetailsDTO toDto(CustomerDetails entity);

    @Mapping(source = "c_address_id", target = "c_address_Id")
    CustAddressDTO toDto(CustAddress entity);

    // DTO to Entity (optional for POST/PUT)
    @Mapping(source = "custId", target = "custId")
    @Mapping(source = "c_firstName", target = "c_firstName")
    @Mapping(source = "c_lastName", target = "c_lastName")
    @Mapping(source = "c_mobile", target = "c_mobile")
    @Mapping(source = "c_email", target = "c_email")
    @Mapping(source = "address", target = "custAddress")
    CustomerDetails toEntity(CustomerDetailsDTO dto);

    @Mapping(source = "c_address_Id", target = "c_address_id")
    CustAddress toEntity(CustAddressDTO dto);

    List<CustomerDetailsDTO> toDtoList(List<CustomerDetails> entities);

    
}