package com.mycodestuffs.springmvcrest.services;

import com.mycodestuffs.springmvcrest.api.v1.mapper.CustomerMapper;
import com.mycodestuffs.springmvcrest.api.v1.model.CustomerDTO;
import com.mycodestuffs.springmvcrest.repositories.CustomerRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerServiceImpl implements CustomerService{

    private final CustomerMapper customerMapper;

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository
                .findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl("/api/v1/customer/"+customer.getId());
                    return customerDTO;
                }).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerRepository
                .findById(id)
                .map(customerMapper::customerToCustomerDTO)
                .orElseThrow(RuntimeException::new);
        //todo: implement better exception, maybe 404
    }
}
