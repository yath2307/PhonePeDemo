package com.phonepe.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phonepe.demo.entity.mongo.Customer;
import com.phonepe.demo.model.response.GetCustomerResponse;
import com.phonepe.demo.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customers")
    String saveCustomer(@RequestBody Customer customer) throws JsonProcessingException {
        log.info(new ObjectMapper().writeValueAsString(customer));
        return customerService.saveCustomer(customer);
    }

    @GetMapping("/customers/{customerUuid}")
    GetCustomerResponse getCustomerResponse(@PathVariable(value = "customerUuid") String customerUuid) {
        return customerService.getCustomer(customerUuid);
    }
}
