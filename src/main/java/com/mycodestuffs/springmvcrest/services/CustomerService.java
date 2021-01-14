package com.mycodestuffs.springmvcrest.services;

import com.mycodestuffs.springmvcrest.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomerById(Long id);
}
