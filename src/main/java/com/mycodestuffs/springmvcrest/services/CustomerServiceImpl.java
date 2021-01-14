package com.mycodestuffs.springmvcrest.services;

import com.mycodestuffs.springmvcrest.api.v1.mapper.CustomerMapper;
import com.mycodestuffs.springmvcrest.api.v1.model.CustomerDTO;
import com.mycodestuffs.springmvcrest.domain.Customer;
import com.mycodestuffs.springmvcrest.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerServiceImpl implements CustomerService{

    private   CustomerMapper customerMapper;

    private   CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Autowired
    public void setCustomerMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }
    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


//    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
//        this.customerMapper = customerMapper;
//        this.customerRepository = customerRepository;
//    }


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
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
//        Customer customer = customerMapper.custometDtoTOCustomer(customerDTO);
//        Customer savedCustomer = customerRepository.save(customer);
//        CustomerDTO returnDto = customerMapper.customerToCustomerDTO(savedCustomer);
//        returnDto.setCustomerUrl("/api/v1/customer/"+savedCustomer.getId());
//        return returnDto;
        return saveAndReturnDTO(customerMapper.custometDtoTOCustomer(customerDTO));
    }

    @Override
    public CustomerDTO saveCustomerByDTO(Long id,CustomerDTO customerDTO) {
        Customer customer = customerMapper.custometDtoTOCustomer(customerDTO);
        customer.setId(id);
        return saveAndReturnDTO(customer);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id).map(customer -> {
            if (customerDTO.getFirstName()!= null){
                customer.setFirstName(customerDTO.getFirstName());
            }
            if (customerDTO.getLastName()!= null){
                customer.setLastName(customerDTO.getLastName());
            }

            CustomerDTO returnDTO =customerMapper.customerToCustomerDTO(customerRepository.save(customer));
             returnDTO.setCustomerUrl("/api/v1/customer/"+ id);
             return returnDTO;
        }).orElseThrow(ResourceNotFoundException::new);
    }

    private CustomerDTO saveAndReturnDTO(Customer customer){

        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO returnDTO = customerMapper.customerToCustomerDTO(savedCustomer);
        returnDTO.setCustomerUrl("/api/v1/customer/"+savedCustomer.getId());
        return returnDTO;
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }
}
