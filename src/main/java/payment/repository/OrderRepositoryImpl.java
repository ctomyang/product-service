package payment.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import payment.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Order> getOrder(int firstResult) {
        return em.createQuery("select o from Order o", Order.class)
                .setMaxResults(10)
                .setFirstResult(firstResult)
                .getResultList();
    }

    @Override
    public void insertOrder(Order order) {
        em.persist(order);
    }
}
