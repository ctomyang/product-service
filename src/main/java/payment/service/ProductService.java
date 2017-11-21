package payment.service;

import payment.entity.Order;
import payment.entity.Product;

import java.util.List;

public interface ProductService {
    //purchase this product according to creditcard name, amount, product id and quantity, then send the email
    //if one of them fail, then throw exception.
    void purchaseProduct(Order order, String productId) throws Exception;
    //insert product
    void insertProduct(Product product);
    //list all products
    List<Product> findProduct(int firstResult);
}
