package com.publicis.card.validator;

public class LuhnCheckUtil {

    public static boolean checkLuhn(final Long cardNumber) {

            String cardNumberStr = cardNumber.toString();
            int sumOfDigits = 0;
            boolean isAlternate = false;

            for (int index = 0; index <= cardNumberStr.length() - 1; index++) {
                int num = Integer.parseInt(cardNumberStr.substring(index, index + 1));
                if (isAlternate) {
                    num = num * 2;// double alternate digits
                }

                // convert num to single digit
                sumOfDigits += num / 10;
                sumOfDigits += num % 10;

                isAlternate = !isAlternate;
            }

            // Eventually time to know if cardNumber was Luhn or not.
            return sumOfDigits % 10 == 0;

    }



}
