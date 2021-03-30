package com.publicis.card.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CardNumberValidatorImpl implements
        ConstraintValidator<CardNumberValidator, String> {

    public static final Logger LOGGER = LoggerFactory.getLogger(CardNumberValidatorImpl.class);

    @Override
    public boolean isValid(final String cardNumber, final ConstraintValidatorContext context) {
        boolean isValid = false;
        try {
            isValid = LuhnCheckUtil.checkLuhn(Long.parseLong(cardNumber));
        } catch (NumberFormatException nfe) {
            LOGGER.debug("cardNumber not valid number.");
        }
        return isValid;
    }


}
