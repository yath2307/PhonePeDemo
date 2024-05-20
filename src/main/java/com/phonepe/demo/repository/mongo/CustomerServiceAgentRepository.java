package com.phonepe.demo.repository.mongo;

import com.phonepe.demo.entity.mongo.CustomerServiceAgent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerServiceAgentRepository extends MongoRepository<CustomerServiceAgent, String> {

    @Query("{ 'email' : ?0 }")
    CustomerServiceAgent findByEmail(String email);
}
