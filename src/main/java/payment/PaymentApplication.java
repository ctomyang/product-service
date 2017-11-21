package payment;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class PaymentApplication{

        public static void main(String[] args) {
            new SpringApplicationBuilder(PaymentApplication.class).web(true).run(args);
        }

}
