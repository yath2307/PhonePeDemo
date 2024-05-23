package com.phonepe.demo.service;

import com.phonepe.demo.entity.mongo.CustomerServiceAgent;
import com.phonepe.demo.entity.mongo.Issue;
import com.phonepe.demo.mapper.CustomerServiceAgentMapper;
import com.phonepe.demo.model.dto.ErrorDTO;
import com.phonepe.demo.model.request.CreateCustomerServiceAgentRequest;
import com.phonepe.demo.model.response.GetIssueResponse;
import com.phonepe.demo.model.response.GetWorkHistoryResponse;
import com.phonepe.demo.repository.mongo.CustomerServiceAgentRepository;
import com.phonepe.demo.repository.mongo.IssueRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceAgentService {

    private final CustomerServiceAgentRepository customerServiceAgentRepository;
    private final CustomerServiceAgentMapper customerServiceAgentMapper;
    private final IssueRepository issueRepository;


    public CustomerServiceAgentService(CustomerServiceAgentRepository customerServiceAgentRepository, CustomerServiceAgentMapper customerServiceAgentMapper,
                                       IssueRepository issueRepository) {
        this.customerServiceAgentRepository = customerServiceAgentRepository;
        this.customerServiceAgentMapper = customerServiceAgentMapper;
        this.issueRepository = issueRepository;
    }

    public String createAgent(CreateCustomerServiceAgentRequest request) {
        CustomerServiceAgent agent = customerServiceAgentRepository.save(customerServiceAgentMapper.map(request.getCustomerServiceAgentDTO()));
        return agent.getId();
    }

    public GetWorkHistoryResponse getWorkHistory(String agentUuid) {
        GetWorkHistoryResponse getWorkHistoryResponse = new GetWorkHistoryResponse();
        Optional<CustomerServiceAgent> customerServiceAgentOptional = customerServiceAgentRepository.findById(agentUuid);
        CustomerServiceAgent customerServiceAgent = customerServiceAgentOptional.orElse(null);
        if(customerServiceAgent == null) {
            ErrorDTO errorDTO = new ErrorDTO("CUSTOMER_SERVICE_AGENT_NOT_FOUND", "Unable to find customer service agent", 500);
            getWorkHistoryResponse.setErrors(List.of(errorDTO));
            return getWorkHistoryResponse;
        }
        List<Issue> issueList = issueRepository.findAllByIssueTypeIn(customerServiceAgent.getIssueTypeList());
        List<String> issueUuidList = new ArrayList<>();
        for(Issue issue:issueList) {
            if (issue.getCustomerServiceAgentId().equalsIgnoreCase(customerServiceAgent.getId())) {
                issueUuidList.add(issue.getId());
            }
        }
        getWorkHistoryResponse.setIssueUuidList(issueUuidList);
        return getWorkHistoryResponse;
    }
}
