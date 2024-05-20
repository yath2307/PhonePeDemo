package com.phonepe.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phonepe.demo.entity.mongo.CustomerServiceAgent;
import com.phonepe.demo.entity.mongo.Issue;
import com.phonepe.demo.mapper.IssueMapper;
import com.phonepe.demo.model.IssueStatus;
import com.phonepe.demo.model.IssueType;
import com.phonepe.demo.model.dto.ErrorDTO;
import com.phonepe.demo.model.response.GetIssueResponse;
import com.phonepe.demo.repository.mongo.CustomerServiceAgentRepository;
import com.phonepe.demo.repository.mongo.IssueRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.loadtime.Agent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class IssueService {
    private final IssueRepository issueRepository;
    private final CustomerServiceAgentRepository customerServiceAgentRepository;
    private final IssueMapper issueMapper;

    public IssueService(IssueRepository issueRepository, CustomerServiceAgentRepository customerServiceAgentRepository, IssueMapper issueMapper) {
        this.issueRepository = issueRepository;
        this.customerServiceAgentRepository = customerServiceAgentRepository;
        this.issueMapper = issueMapper;
    }

    public String createIssue(Issue issue) throws JsonProcessingException {
        log.info(new ObjectMapper().writeValueAsString(issue));
        issue = issueRepository.save(issue);
        return issue.getId();
    }

    public GetIssueResponse getIssue(String issueUuid, String agentEmail) {
        GetIssueResponse getIssueResponse = new GetIssueResponse();
        if(issueUuid == null && agentEmail == null) {
            ErrorDTO errorDTO = new ErrorDTO("INVALID_REQUEST", "Invalid request", 400);
            getIssueResponse.setStatusCode(errorDTO.getErrorCode());
            getIssueResponse.setErrors(List.of(errorDTO));
            return getIssueResponse;
        }
        Issue issue;
        if(issueUuid != null) {
            Optional<Issue> issueOptional = issueRepository.findById(issueUuid);
            issue = issueOptional.orElse(null);
        } else {
            CustomerServiceAgent customerServiceAgent = customerServiceAgentRepository.findByEmail(agentEmail);
            Optional<Issue> issueOptional = Optional.ofNullable(issueRepository.findByAgentId(customerServiceAgent.getId()));
            issue = issueOptional.orElse(null);
        }
        if(issue == null) {
            ErrorDTO errorDTO = new ErrorDTO("ISSUE_NOT_FOUND", "Unable to find issue", 500);
            getIssueResponse.setStatusCode(errorDTO.getErrorCode());
            getIssueResponse.setErrors(List.of(errorDTO));
            return getIssueResponse;
        } else {
            getIssueResponse.setIssue(issueMapper.map(issue));
            getIssueResponse.setStatusCode(200);
            return getIssueResponse;
        }
    }

    public GetIssueResponse updateIssue(String issueUuid, IssueStatus status, String resolution) {
        GetIssueResponse getIssueResponse = new GetIssueResponse();
        List<ErrorDTO> errorDTOList = new ArrayList<>();
        getIssueResponse.setErrors(errorDTOList);
        Optional<Issue> issueOptional = issueRepository.findById(issueUuid);
        Issue issue = issueOptional.orElse(null);
        if(issue == null) {
            ErrorDTO errorDTO = new ErrorDTO("ISSUE_NOT_FOUND", "Unable to find issue", 500);
            getIssueResponse.setStatusCode(errorDTO.getErrorCode());
            getIssueResponse.setErrors(List.of(errorDTO));
            return getIssueResponse;
        } else {
            issue.setStatus(status);
            if(IssueStatus.RESOLVED.name().equalsIgnoreCase(status.name())) {
                issue.setResolution(resolution);
            }
            issue = issueRepository.save(issue);
            assignAnotherIssueToAgent(issue.getCustomerServiceAgentId(), getIssueResponse.getErrors());
            getIssueResponse.setIssue(issueMapper.map(issue));
            getIssueResponse.setStatusCode(200);
            return getIssueResponse;
        }
    }

    private void assignAnotherIssueToAgent(String agentId, List<ErrorDTO> errors) {
        Optional<CustomerServiceAgent> agentOptional = customerServiceAgentRepository.findById(agentId);
        CustomerServiceAgent agent = agentOptional.orElse(null);
        if(agent == null) {
            errors.add(new ErrorDTO("AGENT_NOT_FOUND", "Unable to find agent", 500));
            return;
        } else {
            List<IssueType> issueTypeList = agent.getIssueTypeList();
            List<Issue> issues = issueRepository.findAllByIssueTypeIn(issueTypeList);
            if(issues == null || issues.isEmpty()) {
                agent.setIssueAssigned(false);
                customerServiceAgentRepository.save(agent);
                return;
            } else {
                Issue issue = issues.getFirst();
                issue.setCustomerServiceAgentId(agentId);
                issue.setCustomerServiceAgentName(agent.getName());
                agent.setIssueAssigned(true);
                customerServiceAgentRepository.save(agent);
                issueRepository.save(issue);
            }
        }
    }

    public GetIssueResponse assignIssue(String issueUuid) {
        GetIssueResponse response = new GetIssueResponse();
        Issue issue = issueRepository.findById(issueUuid).orElse(null);
        if(issue == null) {
            ErrorDTO errorDTO = new ErrorDTO("ISSUE_NOT_FOUND", "Issue not found", 500);
            response.setErrors(List.of(errorDTO));
            return response;
        } else {
            List<CustomerServiceAgent> customerServiceAgents = customerServiceAgentRepository.findAll();
            for(CustomerServiceAgent customerServiceAgent : customerServiceAgents) {
                if(!customerServiceAgent.getIssueAssigned() && customerServiceAgent.getIssueTypeList().contains(issue.getType())) {
                    customerServiceAgent.setIssueAssigned(true);
                    issue.setCustomerServiceAgentId(customerServiceAgent.getId());
                    issue.setCustomerServiceAgentName(customerServiceAgent.getName());
                    customerServiceAgentRepository.save(customerServiceAgent);
                    issue = issueRepository.save(issue);
                    break;
                }
            }
            response.setIssue(issueMapper.map(issue));
            return response;
        }
    }
}
