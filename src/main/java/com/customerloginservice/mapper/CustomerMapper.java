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
    @Mapping(source = "cFirstname", target = "cFirstname")
    @Mapping(source = "cLastname", target = "cLastname")
    @Mapping(source = "cMobile", target = "cMobile")
    @Mapping(source = "cEmail", target = "cEmail")
    CustomerDetailsDTO toDto(CustomerDetails entity);


    // DTO to Entity (optional for POST/PUT)
    @Mapping(source = "custId", target = "custId")
    @Mapping(source = "cFirstname", target = "cFirstname")
    @Mapping(source = "cLastname", target = "cLastname")
    @Mapping(source = "cMobile", target = "cMobile")
    @Mapping(source = "cEmail", target = "cEmail")
    CustomerDetails toEntity(CustomerDetailsDTO dto);

    List<CustomerDetailsDTO> toDtoList(List<CustomerDetails> entities);

    
}