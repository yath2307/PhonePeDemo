package com.phonepe.demo.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerDTO implements Serializable {
    String uuid;
    String name;
    String phone;
    String email;
    String address;
}
