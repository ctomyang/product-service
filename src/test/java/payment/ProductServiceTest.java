package payment;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import payment.entity.Order;
import payment.exception.CreditCardException;
import payment.exception.EmailException;
import payment.exception.ProductException;
import payment.repository.ProductRepository;
import payment.service.ChargeService;
import payment.service.ProductService;
import payment.service.ProductServiceImpl;
import payment.utility.EmailUtility;

import java.math.BigDecimal;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ChargeService chargeService;

    @Autowired
    @Spy
    private EmailUtility emailUtility;

    private Order order;

    @Before
    public void init() {
        order = new Order();
        order.setQuantity(5);
        order.setAmount(new BigDecimal(5));
        order.setCreditCardNumber("test-name");
    }

    @Test(expected = Test.None.class)
    public void PurchaseProductTest() throws CreditCardException, ProductException, EmailException {
        when(productRepository.checkInventory(order, "1")).thenReturn(true);
        when(chargeService.chargePayment(order.getCreditCardNumber(), new BigDecimal(1.0))).thenReturn(true);
        when(productRepository.deductInventory(5, "1")).thenReturn(true);
        productService.purchaseProduct(order, "1");
    }

}
