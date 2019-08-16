package com.kasiyanov.processor;

import com.kasiyanov.dto.UserDto;
import com.kasiyanov.parser.ParameterCreatorImpl;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Saver {

    private static final Saver INSTANCE = new Saver();

    public UserDto saveUser(BufferedReader reader, Path path) throws IOException {
        UserDto newUser = Creator.getInstance().createUserDto(reader, ParameterCreatorImpl.getInstance());
        Writer.getInstance().writeUserToFile(newUser, path);
        return newUser;
    }

    public static Saver getInstance() {
        return INSTANCE;
    }
}
