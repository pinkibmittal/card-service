package com.publicis.card.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.publicis.card.validator.CardNumberValidator;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@Builder
public class CreditCardRequestDto {
    @NotNull(message = "EMPTY_CARD_NUMBER_ERROR")
    @CardNumberValidator(message = "INVALID_CARD_NUMBER_ERROR")
    private String cardNumber;

    @NotNull(message = "EMPTY_CARD_HOLDER_NAME_ERROR")
    private String cardHolderName;

    @NotNull(message = "EMPTY_CARD_LIMIT_ERROR")
    @Min(value = 0, message = "NEGATIVE_LIMIT_THRESHOLD_ERROR")
    private BigDecimal cardLimit;

}
