package com.mycodestuffs.springmvcrest.controllers.v1;

import com.mycodestuffs.springmvcrest.api.v1.model.CustomerDTO;
import com.mycodestuffs.springmvcrest.controllers.RestResponseEntityExceptionHandler;
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

import static com.mycodestuffs.springmvcrest.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
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
                .andExpect(jsonPath("$.firstname",equalTo("Michael")));
    }

    @Test
    void createCustomer() throws Exception {
        // given
         CustomerDTO customerDTO= new CustomerDTO();
         customerDTO.setFirstName("Fred");
         customerDTO.setLastName("Flintstome");

         CustomerDTO returnDto = new CustomerDTO();
         returnDto.setFirstName(customerDTO.getFirstName());
         returnDto.setLastName(customerDTO.getLastName());
         returnDto.setCustomerUrl("/api/v1/customer/1");

         when(customerService.createCustomer(customerDTO)).thenReturn(returnDto);

         mockMvc.perform(post("/api/v1/customers/")
         .contentType(MediaType.APPLICATION_JSON)
         .content(asJsonString(customerDTO)))
                 .andExpect(status().isCreated())
                 .andExpect(jsonPath("$.firstname",equalTo("Fred")))
                 .andExpect(jsonPath("$.customer_url",equalTo("/api/v1/customer/1")));
    }

    @Test
    void testUpdateCustomer() throws Exception {
        //given
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstName("Fred");
        customer.setLastName("Flintstome");

        CustomerDTO returnDto = new CustomerDTO();
        returnDto.setFirstName(customer.getFirstName());
        returnDto.setLastName(customer.getLastName());
        returnDto.setCustomerUrl("/api/v1/customers/1");

        when(customerService.saveCustomerByDTO(anyLong(),any(CustomerDTO.class))).thenReturn(returnDto);

        //when /then
        mockMvc.perform(put("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname",equalTo("Fred")))
                .andExpect(jsonPath("$.lastname",equalTo("Flintstome")))
                .andExpect(jsonPath("$.customer_url",equalTo("/api/v1/customers/1")));
    }

    @Test
    void testDeleteCustomer() throws Exception {
        mockMvc.perform(delete("/api/v1/customers/2")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

                verify(customerService).deleteCustomerById(anyLong());
    }
}