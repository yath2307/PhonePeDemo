package com.phonepe.demo.service;

import com.phonepe.demo.entity.mongo.CustomerServiceAgent;
import com.phonepe.demo.mapper.AgentMapper;
import com.phonepe.demo.model.request.CreateCustomerServiceAgentRequest;
import com.phonepe.demo.repository.mongo.CustomerServiceAgentRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceAgentService {

    private final CustomerServiceAgentRepository customerServiceAgentRepository;
    private final AgentMapper agentMapper;


    public CustomerServiceAgentService(CustomerServiceAgentRepository customerServiceAgentRepository, AgentMapper agentMapper) {
        this.customerServiceAgentRepository = customerServiceAgentRepository;
        this.agentMapper = agentMapper;
    }

    public String createAgent(CreateCustomerServiceAgentRequest request) {
        CustomerServiceAgent agent = customerServiceAgentRepository.save(agentMapper.map(request));
        return agent.getId();
    }
}
