package com.kasiyanov.consoleReader;

import com.kasiyanov.consoleValidation.Validator;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;

@Data
@AllArgsConstructor
public class RolesLineConsoleReader implements ParameterConsoleReader {

    private final String ENTER_ROLES = "Введите от одной до трёх ролей пользователя через запятую";

    private Validator validator;

    @Override
    public String getTrimmedLineWithParameters(BufferedReader reader) throws IOException {
        System.out.println(ENTER_ROLES);
        String lineWithUserRoles;
        do {
            lineWithUserRoles = reader.readLine().trim();
        } while (!(validator.isValid(lineWithUserRoles)));
        return lineWithUserRoles;
    }
}
