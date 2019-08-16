package com.kasiyanov.consoleValidation;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.kasiyanov.consoleValidation.massage.ValidationMassage.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RoleListValidator implements Validator {

    private static final RoleListValidator INSTANCE = new RoleListValidator();

    private final int MIN_LIST_SIZE = 1;
    private final int MAX_LIST_SIZE = 3;

    @Override
    public boolean isValid(String line) {
        boolean userRolesListIsValid = false;

        List<String> userRoles = Arrays.stream(line.split(","))
                .map(String::trim)
                .filter(it -> !(it.isEmpty()))
                .collect(Collectors.toList());

        if (userRoles.size() < MIN_LIST_SIZE) {
            System.out.println(ROLES_LESS_THEN_MIN_LIST_SIZE.getContent());
        } else if (userRoles.size() > MAX_LIST_SIZE) {
            System.out.println(ROLES_MORE_THEN_MAX_LIST_SIZE.getContent());
        } else {
            userRolesListIsValid = true;
        }

        return userRolesListIsValid;
    }

    public static RoleListValidator getInstance() {
        return INSTANCE;
    }
}
