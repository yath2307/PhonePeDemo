package com.phonepe.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phonepe.demo.entity.mongo.Issue;
import com.phonepe.demo.model.request.UpdateIssueRequest;
import com.phonepe.demo.model.response.GetIssueResponse;
import com.phonepe.demo.service.IssueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/issues")
@Slf4j
public class IssueController {

    @Autowired
    private IssueService issueService;

    @PostMapping()
    String createIssue(@RequestBody Issue issue) throws JsonProcessingException {
        log.info(new ObjectMapper().writeValueAsString(issue));
        return issueService.createIssue(issue);
    }

    @GetMapping("/{issueUuid}")
    GetIssueResponse getCustomerResponse(@RequestParam(value = "issueUuid") String issueUuid, @RequestParam(value = "agentEmail") String agentemail) throws JsonProcessingException {
        return issueService.getIssue(issueUuid, agentemail);
    }

    @PatchMapping("/{issueUuid}")
    GetIssueResponse updateIssue(@PathVariable(value = "issueUuid") String issueUuid, @RequestBody UpdateIssueRequest updateIssueRequest) throws JsonProcessingException {
        log.info(new ObjectMapper().writeValueAsString(updateIssueRequest));
        return issueService.updateIssue(issueUuid, updateIssueRequest.getStatus(), updateIssueRequest.getResolution());
    }

    @PostMapping("/{issueUuid}/assign")
    GetIssueResponse updateIssue(@PathVariable(value = "issueUuid") String issueUuid) throws JsonProcessingException {
        return issueService.assignIssue(issueUuid);
    }
}
