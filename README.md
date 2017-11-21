# product-service

spring-boot, derby, mailsender. <br>
=======
 <br>
 
>please set the gmail username and password first to test <br>
>>spring.mail.username= <br>
>>spring.mail.password= <br>
>
> EmailUtility
>>public final static String toEmail = ""; <br>
>>public final static String fromEmail = ""; <br>
 <br>
 API
 ====
/order?firstResult=0 get <br>
retrieve 5 results from index 0; <br>
 <br>
 
/product?firstResult=0 get <br>
retrieve 5 results from index 0; <br>
<br>

/product post <br>
  insert product into derby <br>
  ````
  { 
    "name" : "phone", 
    "quantity" : 12,
    "price" : 20
  } 
  ````
  available for json and application/x-www-form-urlencoded both format <br>
<br>
/product/{productId}  post <br>
````
  { 
    "creditCardNumber" : 123,
    "quantity" : 20, 
    "amount" 50 
  } 
````
  
  purchase product and create order if success, then send the email. <br>
  available for json and application/x-www-form-urlencoded both format <br>


