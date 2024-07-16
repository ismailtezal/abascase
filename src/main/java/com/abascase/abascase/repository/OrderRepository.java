package com.abascase.abascase.repository;

import com.abascase.abascase.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
