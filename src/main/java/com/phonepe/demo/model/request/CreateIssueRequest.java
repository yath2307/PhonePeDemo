package com.phonepe.demo.model.request;

import com.phonepe.demo.model.dto.IssueDTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class CreateIssueRequest implements Serializable {
    IssueDTO issueDTO;
}
