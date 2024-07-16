package com.abascase.abascase.service;

import com.abascase.abascase.model.Order;
import com.abascase.abascase.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public double getTotalPrice() {
        return getAllOrders().stream().mapToDouble(Order::getTotalPrice).sum();
    }

    public double getAveragePrice() {
        return getAllOrders().stream().mapToDouble(Order::getUnitPrice).average().orElse(0);
    }

    public Map<Integer, Double> getItemAveragePrice() {
        return getAllOrders().stream()
                .collect(Collectors.groupingBy(Order::getItemId,
                        Collectors.averagingDouble(Order::getUnitPrice)));
    }

    public Map<Integer, Map<Integer, Integer>> getItemOrderQuantity() {
        return getAllOrders().stream()
                .collect(Collectors.groupingBy(Order::getItemId,
                        Collectors.groupingBy(Order::getOrderId,
                                Collectors.summingInt(Order::getQuantity))));
    }

    public Order createOrder(Order order){
        return orderRepository.save(order);
    }

    public void printAllResults() {
        double totalPrice = getTotalPrice();
        System.out.println("Toplam Fiyat: " + totalPrice);

        double averagePrice = getAveragePrice();
        System.out.println("Ortalama Fiyat: " + averagePrice);

        Map<Integer, Double> itemAveragePrices = getItemAveragePrice();
        System.out.println("Ürün Başına Ortalama Fiyatlar:");
        itemAveragePrices.forEach((itemId, avgPrice) -> System.out.println("Ürün ID: " + itemId + ", Ortalama Fiyat: " + avgPrice));

        Map<Integer, Map<Integer, Integer>> itemOrderQuantities = getItemOrderQuantity();
        System.out.println("Ürün ve Sipariş Miktarları:");
        itemOrderQuantities.forEach((itemId, orderMap) -> {
            System.out.println("Ürün ID: " + itemId);
            orderMap.forEach((orderId, quantity) -> System.out.println("    Sipariş ID: " + orderId + ", Miktar: " + quantity));
        });
    }
}
