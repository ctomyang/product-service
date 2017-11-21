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
 <br>
 
 API
 ====
#### /order?firstResult=0 get <br>
- retrieve 5 results from index 0; <br>
 <br>
#### /product?firstResult=0 get <br>
- retrieve 5 results from index 0; <br>
<br>
#### /product post <br>
  insert product into derby <br>
  ````
  {  <br>
    "name" : "phone",  <br>
    "quantity" : 12,  <br>
    "price" : 20 <br>
  } <br>
  available for json and application/x-www-form-urlencoded both format <br>
<br>
#### /product/{productId}  post <br>
  { <br>
    "creditCardNumber" : 123, <br>
    "quantity" : 20, <br>
    "amount" 50 <br>
  } <br>
  
  purchase product and create order if success, then send the email. <br>
  available for json and application/x-www-form-urlencoded both format <br>


