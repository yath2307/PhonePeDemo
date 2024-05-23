package com.phonepe.demo.model.request;

import com.phonepe.demo.model.dto.CustomerDTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class CreateCustomerRequest implements Serializable {
    CustomerDTO customer;
}
