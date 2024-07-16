package com.abascase.abascase.controller;

import com.abascase.abascase.model.Order;
import com.abascase.abascase.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/print")
        public void printAllResults(){
        orderService.printAllResults();
    }

    @GetMapping("/total-price")
    public double getTotalPrice() {
        return orderService.getTotalPrice();
    }

    @GetMapping("/average-price")
    public double getAveragePrice() {
        return orderService.getAveragePrice();
    }

    @GetMapping("/item-average-price")
    public Map<Integer, Double> getItemAveragePrice() {
        return orderService.getItemAveragePrice();
    }

    @GetMapping("/item-order-quantity")
    public Map<Integer, Map<Integer, Integer>> getItemOrderQuantity() {
        return orderService.getItemOrderQuantity();
    }

    @PostMapping("/create-order")
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
        Order newOrder = orderService.createOrder(order);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }
}
