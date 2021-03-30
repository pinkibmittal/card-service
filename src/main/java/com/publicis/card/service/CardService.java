package com.publicis.card.service;

import com.publicis.card.dto.CreditCardRequestDto;
import com.publicis.card.dto.CreditCardResponseDto;
import com.publicis.card.exception.ApplicationException;

import java.util.List;

public interface CardService {

    void addCreditCard(final CreditCardRequestDto creditCardRequestDto) throws ApplicationException;

    CreditCardResponseDto getCreditCardByNumber(final String cardNumber) throws ApplicationException;

    List<CreditCardResponseDto> getAllCreditCards() throws ApplicationException;

}
