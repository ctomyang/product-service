package payment.repository;

import payment.entity.Order;
import payment.entity.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository{

    @PersistenceContext
    EntityManager em;

    @Override
    public boolean checkInventory(Order order, String productId) {
        Product product = em.find(Product.class, productId);
        return product == null ? false : product.getQuantity() >= order.getQuantity();
    }

    @Override
    public boolean deductInventory(int qty, String productId) {
        Product product = em.find(Product.class, productId);
        if(product.getQuantity() - qty < 0)
            return false;
        product.setQuantity(product.getQuantity() - qty);
        return true;
    }

    @Override
    public void insertProduct(Product product) {
        em.persist(product);
    }

    @Override
    public List<Product> loadAllProduct(int firstResult) {
        return em.createQuery("select p from Product p", Product.class)
                .setMaxResults(10)
                .setFirstResult(firstResult)
                .getResultList();
    }

    @Override
    public Product getProductById(String productId) {
        return em.find(Product.class, productId);
    }
}
