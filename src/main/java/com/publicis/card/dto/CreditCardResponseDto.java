package com.publicis.card.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardResponseDto {

    private String cardNumber;

    private String cardHolderName;

    private BigDecimal cardLimit;

    private BigDecimal cardBalance;
}
