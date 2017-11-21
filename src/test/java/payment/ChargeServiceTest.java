package payment;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import payment.service.ChargeService;

import java.math.BigDecimal;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ChargeServiceTest {

    @Autowired
    private ChargeService chargeService;

    @Test
    public void ChargePaymentTest() {
        Assert.assertTrue(chargeService.chargePayment("", new BigDecimal(5.0)) == true);
    }
}
