package com.publicis.card.service.impl;

import com.publicis.card.dao.CreditCardDao;
import com.publicis.card.dto.CreditCardRequestDto;
import com.publicis.card.dto.CreditCardResponseDto;
import com.publicis.card.exception.ApplicationException;
import com.publicis.card.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {

    public static final Logger LOGGER = LoggerFactory.getLogger(CardServiceImpl.class);

    @Autowired
    private CreditCardDao creditCardDao;

    @Override
    public void addCreditCard(final CreditCardRequestDto creditCardRequestDto) throws ApplicationException {
        LOGGER.info("Add card in progress");
        creditCardDao.addCreditCard(creditCardRequestDto);
    }

    @Override
    public CreditCardResponseDto getCreditCardByNumber(final String cardNumber) throws ApplicationException {
        LOGGER.info("Fetching credit card for" + cardNumber);
        CreditCardResponseDto creditCardResponseDto = Optional.ofNullable(creditCardDao.getCreditCardByNumber(cardNumber))
                .orElseThrow(() -> new ApplicationException("Card not found"));
        return creditCardResponseDto;
    }

    @Override
    public List<CreditCardResponseDto> getAllCreditCards() throws ApplicationException {
        LOGGER.info("Fetching all credit cards");
        return Optional.of(creditCardDao.getAllCreditCards())
                .orElse(Collections.emptyList())
                .stream()
                .collect(Collectors.toList());
    }

}
