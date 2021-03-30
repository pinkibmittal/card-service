package com.publicis.card.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.publicis.card.dao.domain.CreditCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

@ExtendWith(SpringExtension.class)
public class CreditCardTest {

    public static final String CARD_NUMBER = "12345";
    public static final String CARD_HOLDER_NAME ="sample name";
    public static final BigDecimal CARD_LIMIT = BigDecimal.TEN;
    public static final BigDecimal CARD_BALANCE = BigDecimal.ZERO;

    @Test
    public void testCreditCard(){
        CreditCard creditCard = CreditCard.builder()
                .cardNumber(CARD_NUMBER)
                .cardHolderName(CARD_HOLDER_NAME)
                .cardlimit(CARD_LIMIT)
                .cardBalance(CARD_BALANCE)
                .build();
        Assertions.assertAll(
                () -> {
                    assertEquals(creditCard.getCardNumber(),CARD_NUMBER);
                    assertEquals(creditCard.getCardHolderName(),CARD_HOLDER_NAME);
                    assertEquals(creditCard.getCardlimit(),CARD_LIMIT);
                    assertEquals(creditCard.getCardBalance(),CARD_BALANCE);
                }
        );
    }
}
