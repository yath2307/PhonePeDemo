package com.phonepe.demo.repository.mongo;

import com.phonepe.demo.entity.mongo.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
}
