package com.kasiyanov.consoleReader;

import com.kasiyanov.consoleValidation.Validator;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;

@Data
@AllArgsConstructor
public class NameConsoleReader implements ParameterConsoleReader {

    private final String ENTER_NAME = "Введите имя пользователя";

    private Validator validator;

    @Override
    public String getTrimmedLineWithParameters(BufferedReader reader) throws IOException {
        System.out.println(ENTER_NAME);
        String userName;
        do {
            userName = reader.readLine().trim();
        } while (!(validator.isValid(userName)));
        return userName;
    }
}