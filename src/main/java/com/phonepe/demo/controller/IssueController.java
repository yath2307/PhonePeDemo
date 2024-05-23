package com.phonepe.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phonepe.demo.entity.mongo.Issue;
import com.phonepe.demo.model.request.CreateIssueRequest;
import com.phonepe.demo.model.request.UpdateIssueRequest;
import com.phonepe.demo.model.response.GetIssueResponse;
import com.phonepe.demo.service.IssueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@Slf4j
public class IssueController {

    @Autowired
    private IssueService issueService;

    @PostMapping("/issues")
    String createIssue(@RequestBody CreateIssueRequest createIssueRequest) throws JsonProcessingException {
        log.info(new ObjectMapper().writeValueAsString(createIssueRequest));
        return issueService.createIssue(createIssueRequest);
    }

    @GetMapping("/issues")
    GetIssueResponse getIssue(@RequestParam(value = "issueUuid") String issueUuid, @RequestParam(value = "agentEmail", required = false) String agentemail) throws JsonProcessingException {
        return issueService.getIssue(issueUuid, agentemail);
    }

    @PatchMapping("/issues/{issueUuid}")
    GetIssueResponse updateIssue(@PathVariable(value = "issueUuid") String issueUuid, @RequestBody UpdateIssueRequest updateIssueRequest) throws JsonProcessingException {
        log.info(new ObjectMapper().writeValueAsString(updateIssueRequest));
        return issueService.updateIssue(issueUuid, updateIssueRequest.getStatus(), updateIssueRequest.getResolution());
    }

    @PostMapping("/issues/{issueUuid}/assign")
    GetIssueResponse assignIssue(@PathVariable(value = "issueUuid") String issueUuid) throws JsonProcessingException {
        return issueService.assignIssue(issueUuid);
    }
}
