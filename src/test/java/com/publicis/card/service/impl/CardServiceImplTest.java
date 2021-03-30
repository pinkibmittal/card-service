package com.publicis.card.service.impl;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import com.publicis.card.dao.impl.CreditCardDaoImpl;
import com.publicis.card.dto.CreditCardRequestDto;
import com.publicis.card.dto.CreditCardResponseDto;
import com.publicis.card.exception.ApplicationException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;



import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(JUnitPlatform.class)
@ExtendWith(value = SpringExtension.class)
public class CardServiceImplTest {

    public static final String CARD_NUMBER = "1234567890";

    @InjectMocks
    private CardServiceImpl cardService;

    @Mock
    private CreditCardDaoImpl creditCardDao;

    @Test
    public void testAddCreditCard_success() {
        CreditCardRequestDto creditCardRequestDto = mock(CreditCardRequestDto.class);

        cardService.addCreditCard(creditCardRequestDto);

        verify(creditCardDao, times(1)).addCreditCard(creditCardRequestDto);
    }

    @Test
    public void testAddCreditCard_throwsException() {
        CreditCardRequestDto creditCardRequestDto = mock(CreditCardRequestDto.class);
        doThrow(new ApplicationException()).when(creditCardDao).addCreditCard(creditCardRequestDto);

        assertThrows("ERROR! while inserting", ApplicationException.class,
                () -> cardService.addCreditCard(creditCardRequestDto));

        verify(creditCardDao, times(1)).addCreditCard(creditCardRequestDto);
    }

    @Test
    public void testGetCreditCardByNumber_success() {
        // setting up and mocking
        CreditCardResponseDto creditCardResponseDto = mock(CreditCardResponseDto.class);
        when(creditCardDao.getCreditCardByNumber(CARD_NUMBER)).thenReturn(creditCardResponseDto);

        // testing the service
        cardService.getCreditCardByNumber(CARD_NUMBER);

        // verifying DAO's are called appropriately.
        verify(creditCardDao, times(1)).getCreditCardByNumber(CARD_NUMBER);
    }

    @Test
    public void testGetCreditCardByNumber_should_throwException() {
        doThrow(new ApplicationException()).when(creditCardDao).getCreditCardByNumber(CARD_NUMBER);

        assertThrows("Card not found", ApplicationException.class,
                () -> cardService.getCreditCardByNumber(CARD_NUMBER));
        verify(creditCardDao, times(1)).getCreditCardByNumber(CARD_NUMBER);
    }

    @Test
    public void testGetAllCreditCards_success_upon_EmptyList() {
        when(creditCardDao.getAllCreditCards()).thenReturn(Collections.emptyList());

        // No null pointer was thrown upon receiving empty list from dao.
        List<CreditCardResponseDto> responseList = cardService.getAllCreditCards();

        assertEquals(responseList, Collections.emptyList());
        verify(creditCardDao, times(1)).getAllCreditCards();
    }

    @Test
    public void testGetAllCreditCards_successWithSomeData() {
        List<CreditCardResponseDto> responseDtos = new ArrayList<>();
        responseDtos.add(new CreditCardResponseDto());

        when(creditCardDao.getAllCreditCards()).thenReturn(responseDtos);

        List<CreditCardResponseDto> responseList = cardService.getAllCreditCards();
        assertEquals(responseList, responseDtos);
        verify(creditCardDao, times(1)).getAllCreditCards();
    }

    @Test
    public void testGetAllCreditCards_throwsException() {
        doThrow(new ApplicationException()).when(creditCardDao).getAllCreditCards();
        assertThrows(ApplicationException.class, () -> cardService.getAllCreditCards());
    }

}
