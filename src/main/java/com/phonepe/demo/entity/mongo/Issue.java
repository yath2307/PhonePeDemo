package com.phonepe.demo.entity.mongo;

import com.phonepe.demo.model.IssueStatus;
import com.phonepe.demo.model.IssueType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "issue")
@Data
public class Issue {
    @Id
    String id;
    String title;
    String description;
    IssueStatus status;
    IssueType type;
    String resolution;
    String customerId;
    String customerName;
    String customerServiceAgentId;
    String customerServiceAgentName;
}
