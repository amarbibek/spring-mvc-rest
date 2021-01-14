package com.mycodestuffs.springmvcrest.api.v1.mapper;

import com.mycodestuffs.springmvcrest.api.v1.model.CustomerDTO;
import com.mycodestuffs.springmvcrest.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);

    Customer custometDtoTOCustomer(CustomerDTO customerDTO);
}
