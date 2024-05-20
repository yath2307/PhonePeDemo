package com.phonepe.demo.entity.mongo;

import com.phonepe.demo.model.IssueType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(value = "customer")
@Data
public class CustomerServiceAgent {
    @Id
    String id;
    String name;
    String email;
    String phone;
    String address;
    List<IssueType> issueTypeList;
    Boolean issueAssigned;
}
