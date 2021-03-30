package com.publicis.card.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import com.publicis.card.dao.domain.CreditCard;
import com.publicis.card.dto.CreditCardRequestDto;
import com.publicis.card.dto.CreditCardResponseDto;
import com.publicis.card.exception.ApplicationException;
import com.publicis.card.repository.CardRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RunWith(JUnitPlatform.class)
@ExtendWith(SpringExtension.class)
public class CreditCardDaoImplTest {

    public static final String CARD_NUMBER = "1234";
    public static final String CARD_HOLDER_NAME ="sample name";
    public static final BigDecimal CARD_LIMIT = BigDecimal.TEN;

    @InjectMocks
    private CreditCardDaoImpl creditCardDao;

    @Mock
    private CardRepository cardRepository;

    @Test
    public void testAddCreditCard_succcess() {
        CreditCardRequestDto creditCardRequestDto = mock(CreditCardRequestDto.class);
        when(creditCardRequestDto.getCardNumber()).thenReturn(CARD_NUMBER);
        when(creditCardRequestDto.getCardLimit()).thenReturn(BigDecimal.valueOf(5000.0));
        when(creditCardRequestDto.getCardHolderName()).thenReturn("Test Name");

        creditCardDao.addCreditCard(creditCardRequestDto);

        verify(cardRepository, times(1)).save(any(CreditCard.class));
    }

    @Test
    public void testAddCreditCard_throwsException() {
        CreditCardRequestDto creditCardRequestDto = mock(CreditCardRequestDto.class);
        when(cardRepository.save(any(CreditCard.class))).thenThrow(new RuntimeException());

        assertThrows("ERROR! while inserting", ApplicationException.class,
                () -> creditCardDao.addCreditCard(creditCardRequestDto));
        verify(cardRepository, times(1)).save(any(CreditCard.class));
    }

    @Test
    public void testGetCreditCardByNumber_Success_NullResults_whenNoEntryExistedInDB() {
        // setting up and mocking
        Optional<CreditCard> creditCardEntity = Optional.empty();
        when(cardRepository.findByCardNumber(CARD_NUMBER)).thenReturn(creditCardEntity);

        // testing the service
        CreditCardResponseDto responseDto = creditCardDao.getCreditCardByNumber(CARD_NUMBER);
        assertEquals(null, responseDto);

        // verifying
        verify(cardRepository, times(1)).findByCardNumber(CARD_NUMBER);
    }

    @Test
    public void testGetCreditCardByNumber_success() {
        // setting up and mocking
        CreditCard creditCardEntity = mock(CreditCard.class);
        when(cardRepository.findByCardNumber(CARD_NUMBER)).thenReturn(Optional.ofNullable(creditCardEntity));

        // testing the service
        creditCardDao.getCreditCardByNumber(CARD_NUMBER);

        verify(cardRepository, times(1)).findByCardNumber(CARD_NUMBER);
    }


    @Test
    public void testGetAllCreditCards_Success_EmptyList_whenNoEntryExistedInDB() {
        // setting up and mocking
        Iterable<CreditCard> cards = null;
        when(cardRepository.findAll()).thenReturn(cards);

        // testing the service
        List<CreditCardResponseDto> responseDtos = creditCardDao.getAllCreditCards();
        assertTrue(responseDtos.isEmpty());

        // verifying
        verify(cardRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllCreditCards_success() {
        Iterable<CreditCard> cards = mock(Iterable.class);
        when(cardRepository.findAll()).thenReturn(cards);

        creditCardDao.getAllCreditCards();

        verify(cardRepository, times(1)).findAll();
    }
}
