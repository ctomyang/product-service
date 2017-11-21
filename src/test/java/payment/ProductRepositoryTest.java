package payment;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import payment.entity.Order;
import payment.entity.Product;
import payment.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    private Product product = null;
    private Order order = null;

    @Before
    public void init() {
        product = new Product("test", 1, 1.0);
        product.setId("1");
        order = new Order();
        order.setAmount(new BigDecimal("1"));
        order.setCreditCardNumber("test-card");
        order.setProduct(product);
        order.setQuantity(5);
    }

    @Transactional
    @Test
    public void CheckInventory_InsertProduct_DeductInventory_Test() {
        productRepository.insertProduct(product);

        //CheckInventory InsertProduct test
        Assert.assertTrue(productRepository.checkInventory(order, "1"));
        Assert.assertFalse(productRepository.checkInventory(order, "1"));

        //DeductInventory test
        productRepository.deductInventory(order.getQuantity(), "1");
        Assert.assertFalse(productRepository.checkInventory(order, "1"));
    }

    @Transactional
    @Test
    public void LoadAllProductTest() {
        productRepository.insertProduct(product);
        List<Product> list = productRepository.loadAllProduct(5);
        Assert.assertTrue(list != null && list.size() != 0);
    }
}
