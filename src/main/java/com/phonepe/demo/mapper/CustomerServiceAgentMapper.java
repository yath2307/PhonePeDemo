package com.phonepe.demo.mapper;

import com.phonepe.demo.entity.mongo.CustomerServiceAgent;
import com.phonepe.demo.model.dto.CustomerServiceAgentDTO;
import com.phonepe.demo.model.request.CreateCustomerServiceAgentRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerServiceAgentMapper {

    @Mapping(source = "uuid", target = "id")
     CustomerServiceAgent map(CustomerServiceAgentDTO customerServiceAgentDTO);
}
