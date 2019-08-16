package com.kasiyanov.consoleValidation;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

import static com.kasiyanov.consoleValidation.massage.ValidationMassage.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EmailValidator implements Validator {

    private static final EmailValidator INSTANCE = new EmailValidator();

    private final String EMAIL_PATTERN = ".+@.+\\.[a-z]+";

    @Override
    public boolean isValid(String line) {
        boolean emailIsValid = false;

        if (Pattern.matches(EMAIL_PATTERN, line)) {
            emailIsValid = true;
        } else {
            System.out.println(INCORRECT_EMAIL_MASSAGE.getContent());
        }

        return emailIsValid;
    }

    public static EmailValidator getInstance() {
        return INSTANCE;
    }
}
