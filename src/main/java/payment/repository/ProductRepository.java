package payment.repository;

import payment.entity.Order;
import payment.entity.Product;

import java.util.List;

public interface ProductRepository {
    //check available quantity for this product
    boolean checkInventory(Order order, String productId);
    //deduct quantity for this product
    boolean deductInventory(int qty, String productId);
    //insert product
    void insertProduct(Product product);
    //list all products
    List<Product> loadAllProduct(int firstResult);
    //get product by id
    Product getProductById(String productId);
}
