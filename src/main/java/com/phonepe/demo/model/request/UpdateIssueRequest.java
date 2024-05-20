package com.phonepe.demo.model.request;

import com.phonepe.demo.model.IssueStatus;
import com.phonepe.demo.service.IssueService;
import lombok.Data;

@Data
public class UpdateIssueRequest {
    String resolution;
    IssueStatus status;
}
