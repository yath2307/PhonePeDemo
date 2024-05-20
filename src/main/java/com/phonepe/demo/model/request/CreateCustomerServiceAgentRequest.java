package com.phonepe.demo.model.request;

import com.phonepe.demo.model.IssueType;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CreateCustomerServiceAgentRequest implements Serializable {

    String uuid;
    String name;
    String email;
    String phone;
    String address;
    List<IssueType> issueTypeList;
    Boolean issueAssigned;
}
