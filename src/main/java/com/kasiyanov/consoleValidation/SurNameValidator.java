package com.kasiyanov.consoleValidation;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SurNameValidator implements Validator {

    private static final SurNameValidator INSTANCE = new SurNameValidator();

    @Override
    public boolean isValid(String line) {
        return true; //Stub //TODO add some validation?
    }

    public static SurNameValidator getInstance() {
        return INSTANCE;
    }
}
