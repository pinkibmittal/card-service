package com.publicis.card.dao.impl;

import com.publicis.card.dao.CreditCardDao;
import com.publicis.card.dao.domain.CreditCard;
import com.publicis.card.dto.CreditCardRequestDto;
import com.publicis.card.dto.CreditCardResponseDto;
import com.publicis.card.exception.ApplicationException;
import com.publicis.card.repository.CardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CreditCardDaoImpl implements CreditCardDao {

    public static BigDecimal DEFAULT_BALANCE = BigDecimal.ZERO;
    public static final Logger LOGGER = LoggerFactory.getLogger(CreditCardDaoImpl.class);

    @Autowired
    private CardRepository cardRepository;

    @Override
    public void addCreditCard(final CreditCardRequestDto creditCardRequestDto) throws ApplicationException {
        try {
            LOGGER.info("Adding credit card");
            CreditCard creditCard = CreditCard.builder()
                    .cardHolderName(creditCardRequestDto.getCardHolderName())
                    .cardNumber(creditCardRequestDto.getCardNumber())
                    .cardlimit(creditCardRequestDto.getCardLimit())
                    .cardBalance(DEFAULT_BALANCE)
                    .build();
            cardRepository.save(creditCard);
        } catch (Exception e) {
            System.out.println(e);
            throw new ApplicationException("ERROR! while inserting");
        }
    }

    @Override
    public CreditCardResponseDto getCreditCardByNumber(final String cardNumber) throws ApplicationException {
        LOGGER.info("Fetching credit cards from repo");
        Optional<CreditCard> creditCard = cardRepository.findByCardNumber(cardNumber);
        return creditCard.isPresent() ? convertToCreditCardResponseDto(creditCard.get()) : null;
    }

    @Override
    public List<CreditCardResponseDto> getAllCreditCards() throws ApplicationException {

        List<CreditCard> creditCards = new ArrayList<>();
        Optional.ofNullable(cardRepository.findAll())
                .orElse(Collections.emptyList())
                .forEach(creditCards::add);
        return Optional.of(creditCards)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::convertToCreditCardResponseDto)
                .collect(Collectors.toList());
    }


    private CreditCardResponseDto convertToCreditCardResponseDto(final CreditCard creditCard) {
        // This converter can be moved to a mapper/converter class.
        return CreditCardResponseDto.builder()
                .cardHolderName(creditCard.getCardHolderName())
                .cardNumber(creditCard.getCardNumber())
                .cardLimit(creditCard.getCardlimit())
                .cardBalance(creditCard.getCardBalance())
                .build();
    }
}
