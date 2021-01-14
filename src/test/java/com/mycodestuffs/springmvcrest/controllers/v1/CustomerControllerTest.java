package com.mycodestuffs.springmvcrest.controllers.v1;

import com.mycodestuffs.springmvcrest.api.v1.model.CategoryDTO;
import com.mycodestuffs.springmvcrest.api.v1.model.CustomerDTO;
import com.mycodestuffs.springmvcrest.services.CategoryService;
import com.mycodestuffs.springmvcrest.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerControllerTest {
    public static final Long ID = 2L;
    public static final String NAME = "Michael";

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void getAllCustomers() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("John");
        customerDTO.setLastName("Doe");
        customerDTO.setCustomerUrl("/api/v1/customer/1");

        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setFirstName("Michael");
        customerDTO1.setLastName("Weston");
        customerDTO1.setCustomerUrl("/api/v1/customer/2");


        List<CustomerDTO> customers = Arrays.asList(customerDTO,customerDTO1);

        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get("/api/v1/customers/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers",hasSize(2)));


    }

    @Test
    void getCustomerById() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Michael");
        customerDTO.setLastName("Weston");
        customerDTO.setCustomerUrl("/api/v1/customer/2");

        when(customerService.getCustomerById(anyLong())).thenReturn(customerDTO);

        mockMvc.perform(get("/api/v1/customers/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName",equalTo("Michael")));
    }
}