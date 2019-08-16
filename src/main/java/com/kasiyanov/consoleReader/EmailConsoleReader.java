package com.kasiyanov.consoleReader;

import com.kasiyanov.consoleValidation.Validator;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;

@Data
@AllArgsConstructor
public class EmailConsoleReader implements ParameterConsoleReader {

    private final String ENTER_EMAIL = "Введите email пользователя";

    private Validator validator;

    @Override
    public String getTrimmedLineWithParameters(BufferedReader reader) throws IOException {
        System.out.println(ENTER_EMAIL);
        String userEmail;
        do {
            userEmail = reader.readLine().trim();
        } while (!(validator.isValid(userEmail)));
        return userEmail;
    }
}