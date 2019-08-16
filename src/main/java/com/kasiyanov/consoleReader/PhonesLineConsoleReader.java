package com.kasiyanov.consoleReader;

import com.kasiyanov.consoleValidation.Validator;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;

@Data
@AllArgsConstructor
public class PhonesLineConsoleReader implements ParameterConsoleReader {

    private final String ENTER_PHONES = "Введите от одного до трёх номеров телефонов пользователя в виде 375хх ххххххх через запятую";

    private Validator validator;

    @Override
    public String getTrimmedLineWithParameters(BufferedReader reader) throws IOException {
        System.out.println(ENTER_PHONES);
        String lineWithUserPhones;
        do {
            lineWithUserPhones = reader.readLine().trim();
        } while (!(validator.isValid(lineWithUserPhones)));
        return lineWithUserPhones;
    }
}
