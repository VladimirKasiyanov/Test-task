package com.kasiyanov.consoleValidation;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.kasiyanov.consoleValidation.massage.ValidationMassage.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PhoneListValidator implements Validator {

    private static final PhoneListValidator INSTANCE = new PhoneListValidator();

    private final String PHONE_PATTERN = "375\\d{2}\\s\\d{7}";
    private final int MIN_LIST_SIZE = 1;
    private final int MAX_LIST_SIZE = 3;

    @Override
    public boolean isValid(String line) {
        boolean userPhonesListIsValid = false;

        List<String> userPhones = Arrays.stream(line.split(","))
                .map(String::trim)
                .filter(it -> !(it.isEmpty()))
                .collect(Collectors.toList());

        List<String> validUserPhones = userPhones.stream()
                .filter(it -> Pattern.matches(PHONE_PATTERN, it))
                .collect(Collectors.toList());

        if (userPhones.size() != validUserPhones.size()) {
            System.out.println(LIST_CONTAINS_INCORRECT_PHONE.getContent());
        } else if (userPhones.size() < MIN_LIST_SIZE) {
            System.out.println(PHONES_LESS_THEN_MIN_LIST_SIZE.getContent());
        } else if (userPhones.size() > MAX_LIST_SIZE) {
            System.out.println(PHONES_MORE_THEN_MAX_LIST_SIZE.getContent());
        } else {
            userPhonesListIsValid = true;
        }

        return userPhonesListIsValid;
    }

    public static PhoneListValidator getInstance() {
        return INSTANCE;
    }
}
