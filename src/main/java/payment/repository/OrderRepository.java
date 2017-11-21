package payment.repository;

import payment.entity.Order;

import java.util.List;

public interface OrderRepository {
    //get orders
    List<Order> getOrder(int firstResult);
    //insert orders
    void insertOrder(Order order);
}
