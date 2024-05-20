package com.phonepe.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phonepe.demo.model.request.CreateCustomerServiceAgentRequest;
import com.phonepe.demo.service.CustomerServiceAgentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController("/agents")
@Slf4j
public class AgentController {

    private final CustomerServiceAgentService customerServiceAgentService;

    public AgentController(CustomerServiceAgentService customerServiceAgentService) {
        this.customerServiceAgentService = customerServiceAgentService;
    }

    @PostMapping()
    String createCustomerServiceAgent(@RequestBody CreateCustomerServiceAgentRequest request) throws JsonProcessingException {
        log.info(new ObjectMapper().writeValueAsString(request));
        return customerServiceAgentService.createAgent(request);
    }
}
