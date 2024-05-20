package com.phonepe.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phonepe.demo.entity.mongo.Customer;
import com.phonepe.demo.mapper.CustomerMapper;
import com.phonepe.demo.model.dto.ErrorDTO;
import com.phonepe.demo.model.response.GetCustomerResponse;
import com.phonepe.demo.repository.mongo.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public String saveCustomer(Customer customer) throws JsonProcessingException {
        log.info(new ObjectMapper().writeValueAsString(customer));
        customer = customerRepository.save(customer);
        return customer.getId();
    }

    public GetCustomerResponse getCustomer(String customerUuid) {
        GetCustomerResponse getCustomerResponse = new GetCustomerResponse();
        Optional<Customer> customer_optional = customerRepository.findById(customerUuid);
        Customer customer = customer_optional.orElse(null);
        if(customer == null) {
            ErrorDTO errorDTO = new ErrorDTO("CUSTOMER_NOT_FOUND", "Unable to find customer", 500);
            getCustomerResponse.setStatusCode(errorDTO.getErrorCode());
            getCustomerResponse.setErrors(List.of(errorDTO));
            return getCustomerResponse;
        } else {
            getCustomerResponse.setCustomer(customerMapper.map(customer));
            getCustomerResponse.setStatusCode(200);
            return getCustomerResponse;
        }
    }
}
