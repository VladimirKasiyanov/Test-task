package com.kasiyanov.processor;

import com.kasiyanov.dto.UserDto;
import com.kasiyanov.parser.ParameterCreator;
import com.kasiyanov.parser.ParameterCreatorImpl;
import com.kasiyanov.util.LineParser;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Reader {

    private static final Reader INSTANCE = new Reader();

    private final String ENTER_EMAIL_USER_FOR_SEARCH = "Чтобы найти пользователя - ";

    public UserDto findUserByEmail(BufferedReader reader, Path path) throws IOException {
        ParameterCreator parameterCreator = ParameterCreatorImpl.getInstance();
        Map<String, UserDto> allUsersFromFile = getAllUsersFromFile(path);
        System.out.print(ENTER_EMAIL_USER_FOR_SEARCH);
        String email = parameterCreator.createUserEmail(reader);
        return allUsersFromFile.get(email);
    }

    public Map<String, UserDto> getAllUsersFromFile(Path path) {
        Map<String, UserDto> usersMap = new HashMap<>();
        try {
            usersMap = Files.lines(path)
                    .filter(it -> !(it.isEmpty()))
                    .collect(Collectors.toMap(
                            it -> LineParser.getInstance().getParameterValue(it, "email"),
                            this::convertToUserDto)
                    );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usersMap;
    }

    private UserDto convertToUserDto(String line) {
        LineParser lineParser = LineParser.getInstance();
        return UserDto.builder()
                .name(lineParser.getParameterValue(line, "name"))
                .surname(lineParser.getParameterValue(line, "surname"))
                .email(lineParser.getParameterValue(line, "email"))
                .roles(lineParser.getParameterValuesList(lineParser.getParameterValue(line, "roles")))
                .phones(lineParser.getParameterValuesList(lineParser.getParameterValue(line, "phones")))
                .build();
    }

    public static Reader getInstance() {
        return INSTANCE;
    }
}
