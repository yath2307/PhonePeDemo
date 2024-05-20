package com.phonepe.demo.model.response;

import com.phonepe.demo.model.dto.IssueDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class GetIssueResponse extends Response implements Serializable {
    IssueDTO issue;
}
