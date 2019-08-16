package com.kasiyanov.processor;

import com.kasiyanov.dto.UserDto;

import com.kasiyanov.parser.ParameterCreator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Creator {

    private static final Creator INSTANCE = new Creator();

    public UserDto createUserDto(BufferedReader reader, ParameterCreator parameterCreator) throws IOException {
        return UserDto.builder()
                .name(parameterCreator.createUserName(reader))
                .surname(parameterCreator.createUserSurname(reader))
                .email(parameterCreator.createUserEmail(reader))
                .roles(parameterCreator.createUserRoles(reader))
                .phones(parameterCreator.createUserPhones(reader))
                .build();
    }

    public static Creator getInstance() {
        return INSTANCE;
    }
}
