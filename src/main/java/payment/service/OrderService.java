package payment.service;

import payment.entity.Order;

import java.util.List;

public interface OrderService {
    //load orders
    List<Order> getOrder(int firstResult);
    //insert oder
    void insertOrder(Order order);
}
