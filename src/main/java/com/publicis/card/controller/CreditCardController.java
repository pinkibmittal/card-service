package com.publicis.card.controller;

import com.publicis.card.dto.CreditCardRequestDto;
import com.publicis.card.dto.CreditCardResponseDto;
import com.publicis.card.exception.ApplicationException;
import com.publicis.card.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/credit-card")
public class CreditCardController {

    public static final String SUCCESS_MESSAGE = "Credit Card added successfully.";

    public static final String X_CORRELATION_ID = "X-Correlation-Id";

    @Autowired
    private CardService cardService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addCreditCard(@RequestHeader(value = X_CORRELATION_ID) final String xCorrelationId,
                                                @RequestBody @Valid final CreditCardRequestDto creditCardRequestDto)
            throws ApplicationException {
        cardService.addCreditCard(creditCardRequestDto);
        return ResponseEntity.status(HttpStatus.OK).header(xCorrelationId).body(SUCCESS_MESSAGE);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{cardNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreditCardResponseDto> getCreditCardByNumber(@RequestHeader(value = X_CORRELATION_ID) final String xCorrelationId,
                                                                       @PathVariable("cardNumber") final String cardNumber) throws ApplicationException {
        CreditCardResponseDto creditCardResponseDto = cardService.getCreditCardByNumber(cardNumber);
        return ResponseEntity.status(HttpStatus.OK).header(xCorrelationId).body(creditCardResponseDto);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CreditCardResponseDto>> getAllCreditCards(final String xCorrelationId) throws ApplicationException {
        List<CreditCardResponseDto> allCreditCards = cardService.getAllCreditCards();
        return ResponseEntity.status(HttpStatus.OK).header(X_CORRELATION_ID, xCorrelationId).body(allCreditCards);
    }

}
