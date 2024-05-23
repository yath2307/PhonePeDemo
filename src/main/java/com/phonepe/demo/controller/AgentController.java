package com.phonepe.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phonepe.demo.model.request.CreateCustomerServiceAgentRequest;
import com.phonepe.demo.model.response.GetWorkHistoryResponse;
import com.phonepe.demo.service.CustomerServiceAgentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

@RestController()
@Slf4j
public class AgentController {

    private final CustomerServiceAgentService customerServiceAgentService;

    public AgentController(CustomerServiceAgentService customerServiceAgentService) {
        this.customerServiceAgentService = customerServiceAgentService;
    }

    @PostMapping("/agents")
    String createCustomerServiceAgent(@RequestBody CreateCustomerServiceAgentRequest request) throws JsonProcessingException {
        log.info(new ObjectMapper().writeValueAsString(request));
        return customerServiceAgentService.createAgent(request);
    }

    @GetMapping("/agents/{agentUuid}/history")
    GetWorkHistoryResponse getAgentWorkHistory(@PathVariable(value = "agentUuid") String agentUuid) throws JsonProcessingException {
        return customerServiceAgentService.getWorkHistory(agentUuid);
    }
}
