package com.phonepe.demo.model.response;

import com.phonepe.demo.model.dto.CustomerDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class GetCustomerResponse extends Response implements Serializable {
    CustomerDTO customer;
}
