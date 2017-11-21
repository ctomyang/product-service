package payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import payment.entity.Order;
import payment.repository.OrderRepository;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getOrder(int firstResult) {
        return orderRepository.getOrder(firstResult);
    }

    @Override
    @Transactional
    public void insertOrder(Order order) {
        orderRepository.insertOrder(order);
    }
}
