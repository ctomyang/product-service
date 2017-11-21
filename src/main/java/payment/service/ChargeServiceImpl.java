package payment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class ChargeServiceImpl implements ChargeService{

    @Transactional
    @Override
    public boolean chargePayment(String creditCardNumber, BigDecimal amount) {
        return true;
    }
}
