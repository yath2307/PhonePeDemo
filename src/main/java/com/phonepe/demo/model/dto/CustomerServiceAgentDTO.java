package com.phonepe.demo.model.dto;

import com.phonepe.demo.model.IssueType;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CustomerServiceAgentDTO implements Serializable {
    String uuid;
    String name;
    String email;
    String phone;
    String address;
    List<IssueType> issueTypeList;
    Boolean issueAssigned;
}
