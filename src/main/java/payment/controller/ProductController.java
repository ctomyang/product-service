package payment.controller;

import org.springframework.http.MediaType;
import payment.domain.ResponseContext;
import payment.entity.Order;
import payment.entity.Product;
import payment.exception.CreditCardException;
import payment.exception.EmailException;
import payment.exception.ProductException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import payment.service.ProductService;

import javax.websocket.server.PathParam;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity ShowSingleProduct() {
        return null;
    }

    @PostMapping(value = "/product/{productId}")
    //purchase "id" 's product and with creditcardName form, and url query variable
    public ResponseEntity PurchaseSingleProductJson(@RequestBody Order order, @PathVariable("productId") String productId) throws Exception {
        productService.purchaseProduct(order, productId);
        return new ResponseEntity(new ResponseContext("purchase success", HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping(value = "/product/{productId}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    //purchase "id" 's product and with creditcardName form, and url query variable
    public ResponseEntity PurchaseSingleProductForm(Order order, @PathVariable("productId") String productId) throws Exception {
        productService.purchaseProduct(order, productId);
        return new ResponseEntity(new ResponseContext("purchase success", HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping(value = "/product")
    //consume form data to product and insert to derby database
    public ResponseEntity InsertSingleProductJson(@RequestBody Product product) {
        productService.insertProduct(product);
        return new ResponseEntity(new ResponseContext("success", HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping(value = "/product", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    //consume form data to product and insert to derby database
    public ResponseEntity InsertSingleProductForm(Product product) {
        productService.insertProduct(product);
        return new ResponseEntity(new ResponseContext("success", HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/product")
    //get all products
    public ResponseEntity FindProduct(@RequestParam int firstResult) {
        return new ResponseEntity(productService.findProduct(firstResult), HttpStatus.OK);
    }

    @ExceptionHandler(value = {ProductException.class, EmailException.class})
    public ResponseEntity ProductError(Exception e) {
        return new ResponseEntity(new ResponseContext(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {CreditCardException.class})
    public ResponseEntity CreditCardError(Exception e) {
        return new ResponseEntity(new ResponseContext(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }
}
