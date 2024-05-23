package com.phonepe.demo.model.dto;

import com.phonepe.demo.model.IssueStatus;
import com.phonepe.demo.model.IssueType;
import lombok.Data;

import java.io.Serializable;

@Data
public class IssueDTO implements Serializable {
    String uuid;
    String title;
    String description;
    String resolution;
    IssueStatus status;
    IssueType type;
    String customerId;
    String customerName;
    String customerServiceAgentId;
    String customerServiceAgentName;
}
