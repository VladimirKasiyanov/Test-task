package com.kasiyanov.consoleReader;

import com.kasiyanov.consoleValidation.Validator;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;

@Data
@AllArgsConstructor
public class SurnameConsoleReader implements ParameterConsoleReader {

    private final String ENTER_SURNAME = "Введите фамилию пользователя";

    private Validator validator;

    @Override
    public String getTrimmedLineWithParameters(BufferedReader reader) throws IOException {
        System.out.println(ENTER_SURNAME);
        String userSurname;
        do {
            userSurname = reader.readLine().trim();
        } while (!(validator.isValid(userSurname)));
        return userSurname;
    }
}