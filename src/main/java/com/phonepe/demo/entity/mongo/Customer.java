package com.phonepe.demo.entity.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "customer")
@Data
public class Customer {
    @Id
    String id;
    String name;
    String phone;
    String email;
    String address;
}
