package com.kasiyanov.consoleValidation;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class NameValidator implements Validator {

    private static final NameValidator INSTANCE = new NameValidator();

    @Override
    public boolean isValid(String line) {
        return true; //Stub //TODO add some validation?
    }

    public static NameValidator getInstance() {
        return INSTANCE;
    }
}
