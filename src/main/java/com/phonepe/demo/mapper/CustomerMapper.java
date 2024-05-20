package com.phonepe.demo.mapper;

import com.phonepe.demo.entity.mongo.Customer;
import com.phonepe.demo.model.dto.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(source = "id", target = "uuid")
    CustomerDTO map(Customer customer);
}
