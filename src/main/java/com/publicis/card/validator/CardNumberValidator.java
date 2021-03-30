package com.publicis.card.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = CardNumberValidatorImpl.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@NotNull(message = "Value cannot be null")
@ReportAsSingleViolation
public @interface CardNumberValidator {
    String message() default "Invalid Card Number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
