package com.phonepe.demo.model.request;

import com.phonepe.demo.model.IssueType;
import com.phonepe.demo.model.dto.CustomerServiceAgentDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CreateCustomerServiceAgentRequest implements Serializable {
    CustomerServiceAgentDTO customerServiceAgentDTO;
}
