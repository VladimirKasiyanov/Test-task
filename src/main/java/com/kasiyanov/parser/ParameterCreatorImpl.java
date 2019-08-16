package com.kasiyanov.parser;

import com.kasiyanov.consoleReader.EmailConsoleReader;
import com.kasiyanov.consoleReader.NameConsoleReader;
import com.kasiyanov.consoleReader.PhonesLineConsoleReader;
import com.kasiyanov.consoleReader.RolesLineConsoleReader;
import com.kasiyanov.consoleReader.SurnameConsoleReader;
import com.kasiyanov.util.LineParser;
import com.kasiyanov.consoleValidation.EmailValidator;
import com.kasiyanov.consoleValidation.NameValidator;
import com.kasiyanov.consoleValidation.PhoneListValidator;
import com.kasiyanov.consoleValidation.RoleListValidator;
import com.kasiyanov.consoleValidation.SurNameValidator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ParameterCreatorImpl implements ParameterCreator {

    private static final ParameterCreatorImpl INSTANCE = new ParameterCreatorImpl();

    @Override
    public String createUserName(BufferedReader reader) throws IOException {
        return LineParser.getInstance().getTrimmedLine(reader, new NameConsoleReader(NameValidator.getInstance()));
    }

    @Override
    public String createUserSurname(BufferedReader reader) throws IOException {
        return LineParser.getInstance().getTrimmedLine(reader, new SurnameConsoleReader(SurNameValidator.getInstance()));
    }

    @Override
    public String createUserEmail(BufferedReader reader) throws IOException {
        return LineParser.getInstance().getTrimmedLine(reader, new EmailConsoleReader(EmailValidator.getInstance()));
    }

    @Override
    public List<String> createUserRoles(BufferedReader reader) throws IOException {
        String trimmedLine = LineParser.getInstance().getTrimmedLine(reader, new RolesLineConsoleReader(RoleListValidator.getInstance()));
        return LineParser.getInstance().getParameterValuesList(trimmedLine);
    }

    @Override
    public List<String> createUserPhones(BufferedReader reader) throws IOException {
        String trimmedLine = LineParser.getInstance().getTrimmedLine(reader, new PhonesLineConsoleReader(PhoneListValidator.getInstance()));
        return LineParser.getInstance().getParameterValuesList(trimmedLine);
    }

    public static ParameterCreatorImpl getInstance() {
        return INSTANCE;
    }
}
