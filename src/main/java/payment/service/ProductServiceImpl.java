package payment.service;

import payment.entity.Order;
import payment.entity.Product;
import payment.exception.CreditCardException;
import payment.exception.EmailException;
import payment.exception.ProductException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import payment.repository.OrderRepository;
import payment.repository.ProductRepository;
import payment.utility.EmailUtility;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ChargeService chargeService;
    private final EmailUtility emailUtility;
    private final OrderRepository orderRepository;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ChargeService chargeService, EmailUtility emailUtility, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.chargeService = chargeService;
        this.emailUtility = emailUtility;
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public void purchaseProduct(Order order, String productId) throws CreditCardException, ProductException, EmailException {
        if(productRepository.checkInventory(order, productId)) {
            if(chargeService.chargePayment(order.getCreditCardNumber(), order.getAmount())) {
                if (productRepository.deductInventory(order.getQuantity(), productId)) {
                    //send email and update ship(better to use jms handle it in asynchronized way)
                    order.setProduct(productRepository.getProductById(productId));
                    orderRepository.insertOrder(order);
                    emailUtility.sendEmail("ship " + order.getProduct().getId() + ", number:" + order.getQuantity() + ", to" + order.getCreditCardNumber(), EmailUtility.toEmail, EmailUtility.fromEmail);
                    return;
                }
                throw new ProductException("product not enough");
            }
            throw new CreditCardException("card not working");
        }
        throw new ProductException("product not enough");
    }

    @Override
    @Transactional
    //to insert the initial product data in table
    public void insertProduct(Product product) {
        productRepository.insertProduct(product);
    }

    @Override
    //load all products
    public List<Product> findProduct(int firstResult) {
        return productRepository.loadAllProduct(firstResult);
    }
}
